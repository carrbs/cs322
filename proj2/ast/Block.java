package ast;
import ir.*;

public class Block extends Stmt {
  public StmtList sl;

  public Block(StmtList asl) { sl=asl; }

  public void dump() { DUMP("\n (Block "); DUMP("StmtList", sl); DUMP(") "); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}

