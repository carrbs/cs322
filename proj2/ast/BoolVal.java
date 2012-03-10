package ast;
import ir.*;

public class BoolVal extends Exp {
  public boolean b;

  public BoolVal(boolean ab) { b=ab; }

  public void dump() { DUMP("(BoolVal " + b + ") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
