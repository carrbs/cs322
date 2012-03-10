package ir;
import codegen.*;

public class CJUMP extends STMT {
  public final static int EQ=0, NE=1, LT=2, LE=3, GT=4, GE=5;
  public int op;
  public EXP left, right;
  public NAME target;

  public CJUMP(int o, EXP l, EXP r, NAME t) {
     op=o; left=l; right=r; target=t;
  }

  private void dumpOp(int op) {
    switch (op) {
    case EQ: DUMP("=="); break;
    case NE: DUMP("!="); break;
    case LT: DUMP("<"); break;
    case LE: DUMP("<="); break;
    case GT: DUMP(">"); break;
    case GE: DUMP(">="); break;
    default: DUMP("??");
    }
  }

  public void dump() { 
    DUMP(" [CJUMP "); dumpOp(op);
    DUMP(left); DUMP(right); DUMP(target); DUMP("]\n");
  }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public void accept(CodeVI v) throws Exception { v.visit(this); }
}

