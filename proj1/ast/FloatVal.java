package ast;
import ir.*;

public class FloatVal extends Exp {
  public float f;

  public FloatVal(float af) { f=af; }

  public String toString() { return "float"; }

  public void dump() { DUMP("(FloatVal " + f + ") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
  public EXP accept(TransVI v) { return v.visit(this); }
}
