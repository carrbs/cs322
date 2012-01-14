package ast;

public class ObjType extends Type {
  public Id cid;

  public ObjType(Id i) { cid=i; }

  public void dump() { DUMP("(ObjType "); DUMP(cid); DUMP(") "); }

  public String toString() { return cid.s; }
  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
}
