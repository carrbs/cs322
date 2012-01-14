package ast;
import ir.*;

public class Assign extends Stmt {
  public Exp lhs, rhs;

  public Assign(Exp e1, Exp e2) { lhs=e1; rhs=e2; }

  public void dump() { 
    DUMP("\n (Assign "); DUMP(lhs); DUMP(rhs); DUMP(") ");
  }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}

