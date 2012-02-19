// cs322
// Benjamin Carr
// proj3

package interp;
import ir.*;
// import symbol.*;
// import typechk.*;
// import ast.*;


public class InterpVisitor implements IntVI { 
    private final int maxTemps = 512;
    private final int maxStack = 2048;
    private final int maxHeap = 4096;
    private final int wordSize = 1;
    private final int STATUS_DEFAULT = -1; 
    private final int STATUS_RETURN = -2; 
    private int[] temps = new int[maxTemps]; 
    private int[] stack = new int[maxStack]; 
    private int[] heap = new int[maxHeap];
    private int sp = maxStack - 2; 
    private int fp = maxStack - 2; 
    private int hp = maxHeap - 1; 
    private int retVal = 0; 
    private FUNClist funcs = null; 
    private STMTlist stmts = null;
    public InterpVisitor() {}

    // Program and functions
    public void visit(PROG t) throws Exception {
        funcs = t.funcs;
        FUNC mf = findFunc("main");
        mf.accept(this);
    }
    private FUNC findFunc(String s) throws Exception {
        FUNC result;
        for (int i = 0 ;i < funcs.size() ; i++) {
            result = funcs.elementAt(i);
            if (result.label.equals(s))
                return result;
        }
        throw new Exception ("function: " + s + " not found");
    }

    public void visit(FUNC t) throws Exception {
        STMTlist temp = stmts;
        stmts = t.stmts;
        sp = sp - t.varCnt - t.argCnt - 1;
        t.stmts.accept(this);
        sp = sp + t.varCnt + t.argCnt + 1;
        stmts = temp;
    }
    public void visit(FUNClist t) throws Exception {}
    // Statements
    public int visit(STMTlist t) throws Exception {
        int ret = STATUS_DEFAULT;
        int i = 0;
        while (i < t.size()) {
            int next = ((STMT) t.elementAt(i)).accept(this);
            if (next == STATUS_RETURN) {
                ret = STATUS_RETURN;
                break;
            }
            i = (next >= 0) ? next : i+1;
        }
        return ret;
    }
    public int visit(MOVE s) throws Exception {
        int val = s.src.accept(this);
        if (s.dst instanceof TEMP)
            temps[((TEMP) s.dst).num] = val;
        else if (s.dst instanceof MEM) { 
            int index = ((MEM)s.dst).exp.accept(this);
            heap[index] = val;
        }
        else if (s.dst instanceof FIELD) {
            int obj = ((FIELD)s.dst).obj.accept(this);
            int idx = ((FIELD)s.dst).idx;
            heap[obj + idx] = val;
        }
        else if (s.dst instanceof PARAM) {
            int param = ((PARAM)s.dst).idx;
            stack[fp + param + 1] = val;
        }
        else if (s.dst instanceof VAR) {
            stack[fp - ((VAR)s.dst).idx] = val;
        }
        else {
            throw new Exception("Programming Error: in MOVE.");
        }
        return STATUS_DEFAULT;
    }
    private int findStmtIdx(NAME n) throws Exception {
        for (int i = 0; i < stmts.size(); i++) {
            STMT curr = stmts.elementAt(i);
            if (curr instanceof LABEL) {
                if (((LABEL)curr).lab.equals(n.id))
                    return i;
            }
        }
        throw new Exception ("Label not found: " + n.id);
    }
    public int visit(JUMP t) throws Exception {
        return findStmtIdx(t.target);
    }
    public int visit(CJUMP t) throws Exception {
        int lval = t.left.accept(this);
        int rval = t.right.accept(this);
        boolean result = false;
        switch (t.op) {
            case CJUMP.EQ: result = (lval == rval); break;
            case CJUMP.NE: result = lval != rval; break;
            case CJUMP.LT: result = (lval < rval); break;
            case CJUMP.LE: result = (lval <= rval); break;
            case CJUMP.GT: result = (lval > rval); break;
            case CJUMP.GE: result = (lval >= rval); break;
            default: throw new Exception ("That isn't an op");
        }
        if (result)
            return findStmtIdx(t.target);
        else
            return STATUS_DEFAULT;
    }
    public int visit(LABEL t) throws Exception {

            return STATUS_DEFAULT;
    }
    public int visit(CALLST t) throws Exception {
        String fname = t.func.id;
        if (fname.equals("print")) {
            if (t.args.size() == 1) {
                if (t.args.elementAt(0) instanceof STRING)
                    System.out.println(((STRING)t.args.elementAt(0)).s);
                else
                    System.out.println(t.args.elementAt(0).accept(this));
            }
            else
                System.out.println();
        } else {
            for(int i = 0; i < t.args.size(); i++)
               stack[sp + i + 1] = t.args.elementAt(i).accept(this);
            stack[sp] = fp;
            fp = sp;
            FUNC f = findFunc(t.func.id);
            f.accept(this);
            sp = fp;
            fp = stack[sp];
        }
        return STATUS_DEFAULT;
    }
    public int visit(RETURN t) throws Exception {
        if (t.exp != null)
            retVal = t.exp.accept(this);
        return STATUS_RETURN;
    }
    // Expressions
    public int visit(EXPlist t) throws Exception {
            return STATUS_DEFAULT;
    }
    public int visit(ESEQ t) throws Exception {
            return STATUS_DEFAULT;
    }
    public int visit(MEM t) throws Exception {
        int index = t.exp.accept(this);
        return heap[index];
    }
    public int visit(CALL t) throws Exception {
        String fname = t.func.id;
        if (fname.equals("malloc")) {
            hp = hp - t.args.elementAt(0).accept(this);
            retVal = hp;
        } else {
            for(int i = 0; i < t.args.size(); i++)
               stack[sp + i + 1] = t.args.elementAt(i).accept(this);
            stack[sp] = fp;
            fp = sp;
            FUNC f = findFunc(t.func.id);
            f.accept(this);
            sp = fp;
            fp = stack[sp];
        }
        return retVal;
    }
    public int visit(BINOP t) throws Exception {
        int lval = t.left.accept(this);
        int rval = t.right.accept(this);
        switch (t.op) {
            case BINOP.ADD: 
                return lval + rval;
            case BINOP.SUB:
                return lval - rval;
            case BINOP.MUL:
                return lval * rval;
            case BINOP.DIV:
                return lval / rval;
            case BINOP.AND:
                return lval * rval;
            case BINOP.OR:
                return (lval == 1) ? lval : rval;
            default:
               throw new Exception("Error: Not a valid BINOP.");
        }
    }
    public int visit(NAME t) throws Exception {
        return 1;
    }
    public int visit(TEMP t) throws Exception {
        return temps[t.num];
    }
    public int visit(FIELD t) throws Exception {
        int obj = t.obj.accept(this);
        int idx = t.idx;
        return heap[obj + idx];

    }
    public int visit(PARAM t) throws Exception {
        int param = t.idx;
        return stack[fp + param + 1];

    }
    public int visit(VAR t) throws Exception {
        return stack[fp - t.idx];
    }
    public int visit(CONST t) throws Exception {
        return t.val;
    }
    public int visit(FLOAT t) throws Exception {
        return (int)t.val;
    }
    public int visit(STRING t) throws Exception {
        return Integer.parseInt(t.s);
    }
}
