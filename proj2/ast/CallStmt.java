package ast;
import ir.*;

public class CallStmt extends Stmt {
  public Exp obj;
  public Id mid;
  public ExpList args;
  
  public CallStmt(Exp e, Id mi, ExpList el) { obj=e; mid=mi; args=el; }

  public void dump() { 
    DUMP("\n (CallStmt "); DUMP(obj); DUMP(mid); 
    DUMP("ExpList", args); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}
