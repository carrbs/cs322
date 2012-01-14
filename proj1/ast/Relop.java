package ast;
import ir.*;

public class Relop extends Exp {
  public static final int EQ=0, NE=1, LT=2, LE=3, GT=4, GE=5;
  public int op;
  public Exp e1, e2;
  
  public Relop(int o, Exp ae1, Exp ae2) { op=o; e1=ae1; e2=ae2; }

  public String opName(int op) {
    switch (op) {
    case EQ: return "== ";
    case NE: return "!= ";
    case LT: return "< ";
    case LE: return "<= ";
    case GT: return "> ";
    case GE: return ">= ";
    default: return "?? ";
    }
  }

  public void dump() { 
      DUMP("(Relop " + opName(op)); DUMP(e1); DUMP(e2); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
