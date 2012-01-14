package ast;
import ir.*;

public class IntVal extends Exp {
  public int i;

  public IntVal(int ai) { i=ai; }

  public String toString() { return "int"; }

  public void dump() { DUMP("(IntVal " + i + ") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
  public EXP accept(TransVI v) { return v.visit(this); }
}
