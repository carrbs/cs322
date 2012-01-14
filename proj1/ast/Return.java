package ast;
import ir.*;

public class Return extends Stmt {
  public Exp e;

  public Return(Exp ae) { e=ae; }

  public void dump() { DUMP("\n (Return "); DUMP(e); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}
