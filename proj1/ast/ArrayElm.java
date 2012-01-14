package ast;
import ir.*;

public class ArrayElm extends Exp {
  public Exp array, idx;
  
  public ArrayElm(Exp e1, Exp e2) { array=e1; idx=e2; }

  public void dump() { 
    DUMP("(ArrayElm "); DUMP(array); DUMP(idx); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
