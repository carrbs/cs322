package ast;
import ir.*;

public class NewObj extends Exp {
  public Id cid;
  public ExpList args;
  
  public NewObj(Id id, ExpList el) { cid=id; args=el; }

  public void dump() { 
    DUMP("(NewObj "); DUMP(cid);
    DUMP("ExpList", args); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public Type accept(TypeVI v) throws Exception { return v.visit(this); }
  public EXP accept(TransVI v) throws Exception { return v.visit(this); }
}
