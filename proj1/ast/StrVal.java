package ast;
import ir.*;

public class StrVal extends Exp {
  public String s;

  public StrVal(String as) { s=as; }

  public void dump() { DUMP("(StrVal \"" + s + "\") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) { return v.visit(this); }
  public EXP accept(TransVI v) { return v.visit(this); }
}
