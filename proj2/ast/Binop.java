package ast;
import ir.*;

public class Binop extends Exp {
  public static final int ADD=0, SUB=1, MUL=2, DIV=3, AND=4, OR=5;
  public int op;
  public Exp e1, e2;
  
  public Binop(int o, Exp ae1, Exp ae2) { op=o; e1=ae1; e2=ae2; }

  public String opName(int op) {
    switch (op) {
    case ADD: return "+";
    case SUB: return "-";
    case MUL: return "*";
    case DIV: return "/";
    case AND: return "&&";
    case OR:  return "||";
    default:  return "?";
    }
  }

  public void dump() { 
      DUMP("(Binop " + opName(op) + " "); DUMP(e1); DUMP(e2); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
