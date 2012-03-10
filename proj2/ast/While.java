package ast;
import ir.*;

public class While extends Stmt {
  public Exp e;
  public Stmt s;

  public While(Exp ae, Stmt as) { e=ae; s=as; }

  public void dump() { DUMP("\n (While "); DUMP(e); DUMP(s); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}

