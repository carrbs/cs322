// cs322
// Benjamin Carr
// proj2

package irgen;
import ir.*;
import symbol.*;
import typechk.*;
import ast.*;

public class IrgenVisitor implements TransVI {
    private NAME cWordSize; // a symbolic name
    private ClassRec currclass;
    private MethodRec currMethod;
    private Table symtable;
    private int maxArgCnt;
    public IrgenVisitor(Table table, TypeVisitor tv) { 
        symtable = table;
        currclass = null;
        currMethod = null;
        cWordSize = new NAME("wSZ"); 
        maxArgCnt = 0;
    }

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
        currclass = symtable.getClass(n.cid);
        FUNClist funcs = n.ml.accept(this);
        currclass = null;
        return funcs;
    }
    public FUNClist visit(MethodDeclList n) throws Exception { 
        FUNClist funcs = new FUNClist();
        for (int i = 0; i < n.size(); i++)
            funcs.add(n.elementAt(i).accept(this)); 
        return funcs;
    }
    public FUNC visit(MethodDecl n) throws Exception { 
        currMethod = currclass.getMethod(n.mid);
        if (currMethod == null)
           System.out.println("Error, currMethod is null");
        String label = n.mid.s;
        if (!label.equals("main"))
            label = symtable.uniqueMethodName(currclass, n.mid);
        int local_vars = currMethod.localCnt();
        STMTlist vars = n.vl.accept(this);
        STMTlist stmts = n.sl.accept(this);
        vars.add(stmts);
        currMethod = null;
        int maxArg = maxArgCnt; 
        maxArgCnt = 0;
        return new FUNC(label, local_vars, maxArg, vars);
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
        int argCnt = n.args.size();
        ClassRec obj_class = null;
        if (n.obj instanceof Id) {
            VarRec obj_var = currMethod.getLocal((Id) n.obj);
            ObjType obj_type = (ObjType) obj_var.type();
            Id obj_id = obj_type.cid;
            obj_class = symtable.getClass(obj_id);
        } 
        else if (n.obj instanceof This)
            obj_class = currclass;
        NAME label = new NAME(symtable.uniqueMethodName(obj_class, n.mid));
        if (maxArgCnt <= argCnt)
            maxArgCnt = argCnt + 1;
        EXPlist el = new EXPlist(); 
        el.add(n.obj.accept(this));
        el.addAll(n.args.accept(this));
        return new CALLST(label, el);
    }
    public STMT visit(If n) throws Exception { 
        STMTlist if_node = new STMTlist();
        NAME f_name = new NAME();
        if (n.e instanceof Relop) {
            EXP e1 = ((Relop) n.e).e1.accept(this);
            EXP e2 = ((Relop) n.e).e2.accept(this);
            int op = ((Relop) n.e).op;
            if_node.add(new CJUMP(opOp(op), e1, e2, f_name));
        } else {
            EXP exp = n.e.accept(this);
            if_node.add(new CJUMP(CJUMP.EQ, exp, new CONST(0), f_name));
        }
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
    public int opOp (int op) {
        switch (op){
            case CJUMP.EQ: return CJUMP.NE;
            case CJUMP.NE: return CJUMP.EQ;
            case CJUMP.GE: return CJUMP.LT;
            case CJUMP.GT: return CJUMP.LE;
            case CJUMP.LE: return CJUMP.GT;
            case CJUMP.LT: return CJUMP.GE;
            default: return -1;
       }
    }

    public EXP visit(Unop n) throws Exception {
        if (n.e instanceof BoolVal){
            int bool = ((BoolVal) n.e).b ? 1 : 0;
            return new CONST(1-bool);
        }
        else
            return new BINOP(BINOP.SUB, new CONST(1), n.e.accept(this));
    }
    public EXP visit(ArrayElm n) throws Exception {
        EXP offset = null;
        if (n.idx instanceof IntVal) {
            int offset_num = ((IntVal) n.idx).i;
            if (offset_num == 0)
                offset = cWordSize;
            else
                offset = new BINOP(BINOP.MUL, new CONST(offset_num + 1), 
                                    cWordSize);
        }
        else {
            EXP index = n.idx.accept(this);
            BINOP add_one = new BINOP(BINOP.ADD, index, new CONST(1));
            offset = new BINOP(BINOP.MUL, add_one,
                               cWordSize);
        }
        Id array_id = (Id) n.array;
        //NAME array_name = new NAME(array_id.s);
        VarRec temp = currclass.getClassVar(array_id);
        EXP var_exp = new VAR(0);
        if (temp != null)
            var_exp = new FIELD(new PARAM(0),temp.idx()-1);
        else {
            temp = currMethod.getLocal(array_id);
            if (temp != null)
                var_exp = new VAR(temp.idx());
            else {
                temp = currMethod.getParam(array_id);
                if (temp != null)
                    var_exp = new PARAM(temp.idx());
            }
        }
        return new MEM(new BINOP(BINOP.ADD,var_exp, offset));
    }
    public EXP visit(ArrayLen n) throws Exception {
        Id array_id = (Id) n.array;
        VarRec temp = currMethod.getLocal(array_id);
        return new MEM(new VAR(temp.idx()));
    }
    public EXP visit(Field n) throws Exception {
        ClassRec obj_class;
        if (n.obj instanceof Id) {
            VarRec obj_var = currMethod.getLocal((Id) n.obj);
            ObjType obj_type = (ObjType) obj_var.type();
            Id obj_id = obj_type.cid;
            obj_class = symtable.getClass(obj_id);
        }
        else obj_class = currclass;
        VarRec v = obj_class.getClassVar(n.var);
        if (v == null)
           v = obj_class.parent().getClassVar(n.var);
        return new FIELD(n.obj.accept(this), v.idx() -1);
    }
    public EXP visit(Call n) throws Exception {
        int argCnt = n.args.size();
        ClassRec obj_class = null;
        if (n.obj instanceof Id) {
            VarRec obj_var = currMethod.getLocal((Id) n.obj);
            ObjType obj_type = (ObjType) obj_var.type();
            Id obj_id = obj_type.cid;
            obj_class = symtable.getClass(obj_id);
        }
        else if (n.obj instanceof This)
            obj_class = currclass;
        NAME label = new NAME(symtable.uniqueMethodName(obj_class, n.mid));
        EXPlist el = new EXPlist(); 
        el.add(n.obj.accept(this));
        el.addAll(n.args.accept(this));
        if (maxArgCnt <= argCnt)
            maxArgCnt = argCnt + 1;
        return new CALL(label,el);
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
        ClassRec temp = currclass;
        currclass = symtable.getClass(n.cid);
        ExpList inits = new ExpList();
        int classVarCnt = currclass.varCnt();
        ClassRec parent = currclass.parent();
        if (parent != null){
            classVarCnt += parent.varCnt();
            for(int i = 0; i < parent.varCnt(); i++)
                inits.add(parent.getClassVarAt(i).init());
        }
        for (int i = 0; i < currclass.varCnt(); i++)
            inits.add(currclass.getClassVarAt(i).init());
        STMTlist stmts = new STMTlist();
        TEMP obj = new TEMP();
        EXPlist malloc_exprs = new EXPlist();
        if (classVarCnt > 1)
            malloc_exprs.add(new BINOP(BINOP.MUL, new CONST(classVarCnt), cWordSize));
        else 
            malloc_exprs.add(cWordSize);
        stmts.add(new MOVE (obj, (new CALL(new NAME("malloc"), malloc_exprs))));
        for (int i = 0; i < inits.size(); i++) {
            EXP init_exp;
            EXP dest;
            if (inits.elementAt(i) != null) {
                init_exp = inits.elementAt(i).accept(this);
                if (init_exp instanceof BINOP)
                    ((FIELD) ((BINOP) init_exp).left).obj = obj;
                else if (init_exp instanceof FIELD)
                    ((FIELD) init_exp).obj = obj;
            }
            else
                init_exp = new CONST(0);
            if (i == 0)
                dest = new MEM(obj);
            else if (i == 1)
                dest = new MEM(new BINOP(BINOP.ADD,obj,cWordSize));
            else
                dest = new MEM(new BINOP(BINOP.ADD,obj,
                                (new BINOP(BINOP.MUL,new CONST(i),
                                cWordSize))));
            stmts.add(new MOVE(dest, init_exp));
       }
        currclass = temp;
        return new ESEQ(stmts,obj);
    }
    public EXP visit(Id n) throws Exception {
        VarRec temp = currMethod.getLocal(n);
        if (temp != null)
                return new VAR(temp.idx());
        else {
            temp = currMethod.getParam(n);
            if (temp != null)
                return new PARAM(temp.idx());
            else {
                temp = currclass.getClassVar(n);
                if (temp != null)
                    return new FIELD(new PARAM(0), temp.idx() - 1);
                else return new VAR(0);
            }
        }
    }
    public EXP visit(This n) {
        return new PARAM(0);
    }

    // Base values
    public EXP visit(IntVal n) { return new CONST(n.i); }
    public EXP visit(FloatVal n) { return new FLOAT(n.f); }
    public EXP visit(BoolVal n) { return new CONST(n.b ? 1 : 0); }
    public EXP visit(StrVal n) { return new STRING(n.s); }
}
