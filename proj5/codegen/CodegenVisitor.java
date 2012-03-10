package codegen;

import ir.*;

public class CodegenVisitor implements CodeVI {
    private final int maxTemps = 512;
    private final int maxStrs = 32;
    private final int wordSize = Sparc.WORDSIZE;
    private Reg[] tempReg = new Reg[maxTemps]; // hold temp’s reg assignment 
    private String[] strBuf = new String[maxStrs]; // hold string literals
    private int tempCnt = 0;
    private int strCnt = 2; // two pre-defined strings

    public CodegenVisitor() {
        strBuf[0] = "L$0:\t.asciz \"Array bounds check error\\n\""; 
        strBuf[1] = "L$1:\t.asciz \"%d\\n\"";
    }
    public void visit(PROG p) throws Exception {
        p.funcs.accept(this);
        for (int i = 1; i < strCnt; i++)
            Sparc.emitString(strBuf[i]);
        Sparc.printStats();
    }
    public void visit(FUNC f) throws Exception {
        int framesize = Sparc.frameSize(f.varCnt, f.argCnt); 
        if (f.label.equals("main"))
            Sparc.emit0(".global main"); 
        Sparc.emit0(".align 4");
        Sparc.emitString(f.label + ":"); 
        Sparc.emitNonInst("!locals=" + f.varCnt +
                          ", max_args=" + f.argCnt + "\n"); 
        Sparc.emit0("save %sp,-" + framesize + ",%sp");
        f.stmts.accept(this);
        Sparc.freeAllRegs();
        //TODO: ...
    }
    public void visit(FUNClist fl) throws Exception {
        for (int i=0; i<fl.size(); i++)
            fl.elementAt(i).accept(this);
    }
    public void visit(STMTlist sl) throws Exception {
        for (int i=0; i<sl.size(); i++) {
            STMT s = sl.elementAt(i); 
            Sparc.emitNonInst("!"); 
            s.dump();
            s.accept(this);
        }
    }
    public void visit(MOVE s) throws Exception {
        // generate code for s.src and bring result to a reg r ...
        Operand src = s.src.accept(this);
        Reg r = Sparc.getReg();
        toReg(src, r);
        if (s.dst instanceof TEMP) {
            Operand dst = s.dst.accept(this); 
            Sparc.emit2("mov", src, dst);
            // freeOperand(src);
        } else if (s.dst instanceof MEM) {
            Operand dst = ((MEM) s.dst).exp.accept(this); 
            Sparc.emitStore(r, dst);
            // TODO:...
        } else { // TODO:...
        }
        Sparc.freeReg(r);
    }

    void toReg(Operand t, Reg r) throws Exception {
        if (t instanceof Immed) { 
            if (((Immed) t).is13b)
                Sparc.emit2("mov", t, r); else
            Sparc.emit2("set", t, r);
        } else if (t instanceof RegOff) {
            Sparc.emitLoad(t, r);
    }else { /* t instanceof Reg*/}
    }
    public void visit(JUMP s) throws Exception {
        if (s.target instanceof NAME) {
            Sparc.emit0("ba " + ((NAME) s.target).id);
            Sparc.emit0("nop");
        } else
            throw new CodegenException("JUMP target not a NAME");
    }
    public void visit(CJUMP s) throws Exception {
        // bring operands to regs r1 and r2
        //TODO:...
        //Sparc.emit2("cmp", r1, r2);
        // Sparc.emit0(relopCode(s.op) + " " + label);
        // Sparc.emit0("nop");
        // TODO: ...
    }
    public void visit(LABEL t) throws Exception { }
    public void visit(CALLST s) throws Exception {
        String fname = s.func.id;
    if (fname.equals("print"))
        genPrint(s.args);
    //else if
     //   (fname.equals("error")) genError();
    else
        genCall(fname, s.args);
    }
    void genCall(String label, EXPlist args) throws Exception {
        for (int i=0; i<args.size(); i++) {
        // load arg_i to a reg, then store it to 
        // the i-th param slot in stack
        // frame
        }
        Sparc.emit0("call " + label); 
        Sparc.emit0("nop");
    }
    void genReturn(RETURN s) throws Exception {
        if (s.exp != null)
            // generate code for s.exp and bring result to RegRV
         Sparc.emit0("ret");
         Sparc.emit0("restore");
    }
    void genPrint(EXPlist args) throws Exception {
        if (args != null && args.size() == 1
                && (args.elementAt(0) instanceof STRING)) {
            // print a string
            String str = ((STRING) args.elementAt(0)).s;
            String lab = "L$" + strCnt;
            strBuf[strCnt++] = lab + ":\t.asciz \"" + str + "\\n\"";
            Sparc.getReg(Sparc.regO0);
            Sparc.emit0("sethi %hi(" + lab + "),%o0");
            Sparc.emit0("or %o0, %lo(" + lab + "),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO0);
        } else if (args != null && args.size() == 1
                && (args.elementAt(0) instanceof CONST)) {
            int num = ((CONST) args.elementAt(0)).val;
            Immed m = new Immed(num);
            Sparc.getReg(Sparc.regO0);
            Sparc.getReg(Sparc.regO1);
            Sparc.emit2("mov",m,Sparc.regO1);
            Sparc.emit0("sethi %hi(L$1),%o0");
            Sparc.emit0("or %o0, %lo(L$1),%o0");
            Sparc.emit0("call printf");
            Sparc.emit0("nop");
            Sparc.freeReg(Sparc.regO1);
            Sparc.freeReg(Sparc.regO0);
            // print an integer: pass two arguments to printf, 
            // one control string
            // at L$1 and one integer
        } else {} 
    }
    public void visit(RETURN t) throws Exception { }
    public Operand visit(EXPlist t) throws Exception {return null;}
    public Operand visit(ESEQ t) throws Exception { return null;}
    public Operand visit(MEM t) throws Exception { return null;}
    public Operand visit(CALL t) throws Exception { return null;}
    public Operand visit(BINOP t) throws Exception {return null; }
    public Operand visit(NAME t) throws Exception { return null;}
    public Operand visit(TEMP t) throws Exception { return null;}
    public Operand visit(FIELD t) throws Exception { return null;}
    public Operand visit(PARAM t) throws Exception { return null;}
    public Operand visit(VAR t) throws Exception {return null; }
    public Operand visit(CONST t) throws Exception { return null;}
    public Operand visit(FLOAT t) throws Exception { return null;}
    public Operand visit(STRING t) throws Exception {
        return null;
    }
}


