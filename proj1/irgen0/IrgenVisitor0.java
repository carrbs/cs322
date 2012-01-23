// cs322
// Benjamin Carr
// proj1

package irgen0;
import ir.*;
import ast.*;
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
        STMTlist vars = n.vl.accept(this);
        STMTlist stmts = n.sl.accept(this);
        vars.add(stmts);
        return new FUNC(label, 0, 0, vars);
    }
    public STMTlist visit(VarDeclList n) throws Exception { 
        STMTlist stmts = new STMTlist();
        for (int i = 0; i < n.size(); i++) {
            STMT s = n.elementAt(i).accept(this);
            if (s != null)
                stmts.add(s);
        }
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
        NAME label = new NAME(n.mid.s);
        EXPlist args = n.args.accept(this);
        return new CALLST(label, args);
    }
    public STMT visit(If n) throws Exception { 
        STMTlist if_node = new STMTlist();
        NAME f_name = new NAME();
        EXP e = n.e.accept(this);
        CJUMP cj = new CJUMP(0, e, new CONST(0), f_name);
        if_node.add(cj);
        STMT s1 = n.s1.accept(this);
        if_node.add(s1);
        if (n.s2 == null)
            if_node.add(new LABEL(f_name));
        else {
            NAME d_name = new NAME();
            JUMP j = new JUMP(d_name);
            if_node.add(j);
            LABEL f_label = new LABEL(f_name);
            if_node.add(f_label);
            STMT s2 = n.s2.accept(this);
            if_node.add(s2);
            LABEL d_label = new LABEL(d_name);
            if_node.add(d_label);
        }
        return if_node;
    }
    public STMT visit(While n) throws Exception { 
        STMTlist while_node = new STMTlist();
        NAME start_name = new NAME();
        LABEL start_label = new LABEL(start_name);
        while_node.add(start_label);
        NAME end_name = new NAME();
        EXP e = n.e.accept(this);
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
        if (n.e != null)
            print_expr.add(n.e.accept(this));
        return new CALLST(new NAME("print"), print_expr);
    }
    public STMT visit(Return n) throws Exception { 
        return new RETURN(n.e.accept(this));
    }

    // Expressions
    public EXPlist visit(ExpList n) throws Exception { 
        EXPlist exprs = new EXPlist();
        for (int i = 0; i < n.size(); i++)
            exprs.add(n.elementAt(i).accept(this));
        return exprs;
    }
    public EXP visit(Binop n) throws Exception {
        EXP e1 = n.e1.accept(this);
        EXP e2 = n.e2.accept(this);
        /*
        if (n.op == Binop.AND) {
            STMTlist and_node = new STMTlist();
            TEMP result = new TEMP();
            MOVE set_false = new MOVE(result, new CONST(0));
            and_node.add(set_false);
            NAME end_name = new NAME();
            CJUMP cj1 = new CJUMP(0, e1, new CONST(0), end_name); 
            and_node.add(cj1);
            CJUMP cj2 = new CJUMP(0, e2, new CONST(0), end_name); 
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
            CJUMP cj1 = new CJUMP(0, e1, new CONST(1), true_name);
            or_node.add(cj1);
            CJUMP cj2 = new CJUMP(0, e2, new CONST(1), true_name);
            or_node.add(cj2);
            MOVE set_false = new MOVE(result, new CONST(0));
            or_node.add(set_false);
            LABEL true_label = new LABEL(true_name);
            or_node.add(true_label);
            return new ESEQ(or_node, result);
        }
        else
        */
        return new BINOP(n.op,e1,e2);
    }
    public EXP visit(Relop n) throws Exception { 
        EXP e1 = n.e1.accept(this);
        EXP e2 = n.e2.accept(this);
        STMTlist relop_node = new STMTlist();
        TEMP result = new TEMP();
        MOVE set_true = new MOVE(result, new CONST(1));
        relop_node.add(set_true);
        NAME true_name = new NAME();
        CJUMP cj1 = new CJUMP(n.op, e1, e2, true_name);
        relop_node.add(cj1);
        MOVE set_false = new MOVE(result, new CONST(0));
        relop_node.add(set_false);
        relop_node.add(new LABEL(true_name));
        return new ESEQ(relop_node, result);
    }
    public EXP visit(Unop n) throws Exception {
        return new BINOP(BINOP.SUB, new CONST(1), n.e.accept(this));
    }
    public EXP visit(ArrayElm n) throws Exception {
        Id array_id = (Id) n.array;
        EXP index = n.idx.accept(this);
        BINOP add_one = new BINOP(BINOP.ADD, index, new CONST(1));
        NAME array_name = new NAME(array_id.s);
        return new MEM(new BINOP(BINOP.ADD,array_name, 
                                 (new BINOP(BINOP.MUL, add_one, cWordSize))));
    }
    public EXP visit(ArrayLen n) throws Exception {
        Id array_id = (Id) n.array;
        NAME array_name = new NAME(array_id.s);
        return new MEM(array_name);
    }
    public EXP visit(Field n) throws Exception {
        return new NAME(n.var.s);
    }
    public EXP visit(Call n) throws Exception {
        return new CALL(new NAME(n.mid.s),n.args.accept(this));
    }
    public EXP visit(NewArray n) throws Exception {
        STMTlist array_list = new STMTlist();
        TEMP malloc_loc = new TEMP();
        NAME malloc = new NAME("malloc");
        BINOP array_size = new BINOP(BINOP.MUL,new CONST(n.size+1), cWordSize);
        EXPlist malloc_list = new EXPlist(array_size);
        CALL malloc_call = new CALL(malloc, malloc_list);
        MOVE malloc_init = new MOVE(malloc_loc,malloc_call);
        array_list.add(malloc_init);
        MOVE array_size_set = new MOVE(new MEM(malloc_loc), new CONST(n.size));
        array_list.add(array_size_set);
        TEMP iterator = new TEMP();
        BINOP array_end = new BINOP(BINOP.MUL, new CONST(n.size), cWordSize);
        BINOP array_end_set = new BINOP(BINOP.ADD,malloc_loc,array_end);
        array_list.add(new MOVE(iterator,array_end_set));
        NAME label_name = new NAME();
        array_list.add(new LABEL(label_name));
        MOVE move1 = new MOVE(new MEM(iterator), new CONST(0));
        array_list.add(move1);
        BINOP next = new BINOP(BINOP.SUB,iterator,cWordSize);
        MOVE move2 = new MOVE(iterator, next);
        array_list.add(move2);
        CJUMP cj = new CJUMP(CJUMP.GT, iterator, malloc_loc,label_name);
        array_list.add(cj);
        return new ESEQ(array_list,malloc_loc);
    }
    public EXP visit(NewObj n) throws Exception {
        EXPlist obj_list = new EXPlist();
        obj_list.add(new NAME(n.cid.s+"_obj_size"));
        return new CALL(new NAME("malloc"), obj_list);
    }
    public EXP visit(Id n) throws Exception {  
        return new NAME(n.s);
    }
    public EXP visit(This n) {
        return new NAME("this");
    }

    // Base values
    public EXP visit(IntVal n) { return new CONST(n.i); }
    public EXP visit(FloatVal n) { return new FLOAT(n.f); }
    public EXP visit(BoolVal n) { return new CONST(n.b ? 1 : 0); }
    public EXP visit(StrVal n) { return new STRING(n.s); }
}
