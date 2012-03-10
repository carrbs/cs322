package ast;
import ir.*;

public class Field extends Exp {
  public Exp obj;
  public Id  var;
  
  public Field(Exp e, Id v) { obj=e; var=v; }

  public void dump() { 
    DUMP("(Field "); DUMP(obj); DUMP(var); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
