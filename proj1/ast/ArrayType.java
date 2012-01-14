package ast;

public class ArrayType extends Type {
  public Type et;

  public ArrayType(Type t) { et=t; }

  public String toString() { return et.toString() + "[]"; }

  public void dump() { DUMP("(ArrayType "); DUMP(et); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
}
