package ir;
import codegen.*;

public class BINOP extends EXP {
  public static final int ADD=0, SUB=1, MUL=2, DIV=3, AND=4, OR=5;
  public int op;
  public EXP left, right;

  public BINOP(int b, EXP l, EXP r) { op=b; left=l; right=r; }

  private void dumpOp(int op) {
    switch (op) {
    case ADD: DUMP("+"); break;
    case SUB: DUMP("-"); break;
    case MUL: DUMP("*"); break;
    case DIV: DUMP("/"); break;
    case AND: DUMP("&&"); break;
    case OR:  DUMP("||"); break;
    default:  DUMP("?");
    }
  }

  public void dump() { 
    DUMP(" (BINOP "); dumpOp(op); DUMP(left); DUMP(right); DUMP(")");
  }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

