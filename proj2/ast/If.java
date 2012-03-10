package ast;
import ir.*;

public class If extends Stmt {
  public Exp e;
  public Stmt s1, s2;

  public If(Exp ae, Stmt as1, Stmt as2) { e=ae; s1=as1; s2=as2; }

  public void dump() { 
    DUMP("\n (If "); DUMP(e); DUMP(s1); DUMP(s2); DUMP(") "); 
  }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}

