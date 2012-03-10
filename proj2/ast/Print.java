package ast;
import ir.*;

public class Print extends Stmt {
  public Exp e;

  public Print(Exp ae) { e=ae; }

  public void dump() { DUMP("\n (Print "); DUMP(e); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}
