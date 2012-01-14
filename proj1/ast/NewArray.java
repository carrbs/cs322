package ast;
import ir.*;

public class NewArray extends Exp {
  public Type et;
  public int size;
  
  public NewArray(Type t, int i) { et=t; size=i; }

  public void dump() { 
    DUMP("(NewArray "); DUMP(et); DUMP(size); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
