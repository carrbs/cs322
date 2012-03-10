package ast;
import ir.*;

public class Call extends Exp {
  public Exp obj;
  public Id mid;
  public ExpList args;
  
  public Call(Exp e, Id mi, ExpList el) { obj=e; mid=mi; args=el; }

  public void dump() { 
    DUMP("(Call "); DUMP(obj); DUMP(mid); 
    DUMP("ExpList", args); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
