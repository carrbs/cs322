package ast;

public class Formal extends Ast {
  public Type t;
  public Id id;
 
  public Formal(Type at, Id i) { t=at; id=i; }

  public void dump() { DUMP("(Formal "); DUMP(t); DUMP(id); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
}
