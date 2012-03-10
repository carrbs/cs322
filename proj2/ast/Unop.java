package ast;
import ir.*;

public class Unop extends Exp {
  public static final int NEG=0, NOT=1;
  public int op;
  public Exp e;
  
  public Unop(int o, Exp ae) { op=o; e=ae; }

  public String opName(int op) {
    switch (op) {
    case NEG: return "-";
    case NOT: return "!";
    default:  return "?";
    }
  }

  public void dump() { DUMP("(Unop " + opName(op) + " "); DUMP(e); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
