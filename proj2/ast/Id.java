package ast;
import ir.*;

public class Id extends Exp {
  public String s;

  public Id(String as) { s=as; }

  public void dump() { DUMP("(Id " + s + ") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
