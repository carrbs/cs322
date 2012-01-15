// cs322
// proj1

package irgen0;
import ir.*;
public class IrgenVisitor0 implements TransVI {
    private NAME cWordSize; // a symbolic name

    public IrgenVisitor0() { cWordSize = new NAME("wSZ"); }

    public PROG visit(Program n) throws Exception {
        FUNClist funcs = n.cl.accept(this);
        return new PROG(funcs);
    }

    // Declarations
    public FUNClist visit(ClassDeclList n) throws Exception {
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.addAll(n.elementAt(i).accept(this)); 
        return funcs;
    }
    public FUNClist visit(ClassDecl n) throws Exception { 
        FUNClist funcs = n.ml.accept(this);
        return funcs;
    }
    public FUNClist visit(MethodDeclList n) throws Exception { 
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.add(n.elementAt(i).accept(this)); 
        return funcs;
    }
    public FUNC visit(MethodDecl n) throws Exception { 
        String label = n.mid.s;
        STMTlist stmts = n.sl.accept(this);
        STMTlist formals = n.fl.accept(this);
        STMTlist vars = n.vl.accept(this);
        stmts.addAll(formals);
        stmts.addAll(vars);
        return new FUNC(label, 0, 0, stmts);
    }
    public STMTlist visit(VarDeclList n) throws Exception { 
        STMTlist stmts = new STMTlist();
        for (int i = 0; i < n.size(); i++)
            stmts.add(n.elementAt(i).accept(this));
        return stmts;
    }
    public STMT visit(VarDecl n) throws Exception { 
        if (n.e == null)
            return null;
        else {
            EXP dst = n.var.accept(this);
            EXP src = n.e.accept(this);
            return new MOVE(dst,src);
        }
    }

    // Statements
    public STMTlist visit(StmtList n) throws Exception { 
        STMTlist stmts = new STMTlist();
        for (int i = 0; i < n.size(); i++)
            stmts.add(n.elementAt(i).accept(this));
        return stmts;
    }
    public STMT visit(Block n) throws Exception { 
        return n.sl.accept(this);
    }
    public STMT visit(Assign n) throws Exception { 
        EXP lhs = n.lhs.accept(this); 
        EXP rhs = n.rhs.accept(this); 
        return new MOVE(lhs, rhs);
    }
    public STMT visit(CallStmt n) throws Exception {
        Name label = n.mid.s;
        EXPlist args = n.args.accept(this);
        return new CALLST(label, args);
    }
    public STMT visit(If n) throws Exception { 
        STMTlist if_node = new STMTlist();
        EXP e = n.e.accept(this);
        NAME f_name = new NAME();
        CJUMP cj = new CJUMP(0, e, new CONST(0), f_name);
        if_node.add(cj);
        STMT s1 = n.s1.accept(this);
        if_node.add(s1);
        NAME d_name = new NAME();
        JUMP j = new JUMP(d_name);
        if_node.add(j);
        LABEL f_label = new LABEL(f_name);
        if_node.add(f_label);
        STMT s2 = n.s2.accept(this);
        if_node.add(s2);
        LABEL d_label = new LABEL(d_name)
        if_node.add(d_label);
        return if_node;
    }
    public STMT visit(While n) throws Exception { 
        STMTlist = while_node = new STMTlist();
        NAME start_name = new NAME();
        LABEL start_label = new LABEL(start_name);
        while_node.add(start_label);
        EXP e = n.e.accept(this);
        NAME end_name = new NAME();
        CJUMP cj = new CJUMP(0, e, new CONST(0), end_name);
        while_node.add(cj);
        STMT s = n.s.accept(this);
        while_node.add(s);
        JUMP j = new JUMP(start_name);
        while_node.add(j);
        LABEL end_label = new LABEL(end_name);
        while_node.add(end_label);
        return while_node;
    }
    public STMT visit(Print n) throws Exception {
        EXPlist print_expr = new EXPlist();
        print_expr.add(n.e.accept(this));
        return new CALLST(new NAME("print"), print_expr)
    }
    public STMT visit(Return n) throws Exception { 
        return new RETURN(n.e);
    }

    // Expressions
    public EXPlist visit(ExpList n) throws Exception { 
        EXPlist exprs = new EXPlist()
        for (int i = 0; i < n.size(); i++)
            exprs.add(n.elementAt(i).accept(this));
        return exprs;
    }
    public EXP visit(Binop n) throws Exception {
        EXP e1 = n.e1.accept(this);
        EXP e2 = n.e2.accept(this);
        if (n.op == Binop.AND) {
            STMTlist and_node = new STMTlist();
            TEMP result = new TEMP();
            MOVE set_false = new MOVE(result, new CONST(0));
            and_node.add(set_false);
            NAME end_name = new NAME();
            CJUMP cj1 = new CJUMP(0, e1, CONST(0), end_name); 
            and_node.add(cj1);
            CJUMP cj2 = new CJUMP(0, e2, CONST(0), end_name); 
            and_node.add(cj2);
            MOVE set_true = new MOVE(result, new CONST(1));
            and_node.add(set_true);
            LABEL end_label = new LABEL(end_name);
            and_node.add(end_label);
            return new ESEQ(and_node, result);
        }
        else if (n.op == Binop.OR) {
            STMTlist or_node = new STMTlist();
            TEMP result = new TEMP();
            MOVE set_true = new MOVE(result, new CONST(1));
            or_node.add(set_true);
            NAME true_name = new NAME();
            CJUMP cj1 = new CJUMP(0, e1, CONST(1), true_name);
            or_node.add(cj1);
            CJUMP cj2 = new CJUMP(0, e2, CONST(1), true_name);
            or_node.add(cj2);
            MOVE set_false = new MOVE(result, new CONST(0));
            or_node.add(set_false);
            LABEL true_label = new LABEL(true_name);
            or_node.add(true_label);
            return new ESEQ(or_node, result);
        }
        else
            return new BINOP(n.op,e1,e2);
    }
    public EXP visit(Relop n) throws Exception { }
    public EXP visit(Unop n) throws Exception { }
    public EXP visit(ArrayElm n) throws Exception { }
    public EXP visit(ArrayLen n) throws Exception { }
    public EXP visit(Field n) throws Exception { }
    public EXP visit(Call n) throws Exception { }
    public EXP visit(NewArray n) throws Exception { }
    public EXP visit(NewObj n) throws Exception { }
    public EXP visit(Id n) throws Exception { }
    public EXP visit(This n) { }

    // Base values
    public EXP visit(IntVal n) { }
    public EXP visit(FloatVal n) { }
    public EXP visit(BoolVal n) { }
    public EXP visit(StrVal n) { }
}
