package ast;

public class FloatType extends Type {

  public String toString() { return "float"; }

  public void dump() { DUMP("(FloatType) "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
}
