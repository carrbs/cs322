package ast;
import ir.*;

public class ArrayLen extends Exp {
  public Exp array;
  
  public ArrayLen(Exp e) { array=e; }

  public void dump() { DUMP("(ArrayLen "); DUMP(array); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
