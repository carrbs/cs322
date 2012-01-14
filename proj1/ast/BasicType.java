package ast;

public class BasicType extends Type {
  public static final int Int=0, Float=1, Bool=2;
  public int typ;

  public BasicType(int t) { typ=t; }

  public String typName(int typ) {
    switch (typ) {
    case Int:   return "int";
    case Float: return "float";
    case Bool:  return "boolean";
    default: return "?";
    }
  }

  public String toString() { return typName(typ); }

  public void dump() { DUMP("(BasicType " + typName(typ) + ") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
}
