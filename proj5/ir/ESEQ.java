package ir;
import codegen.*;

public class ESEQ extends EXP {
  public STMT stmt;
  public EXP exp;

  public ESEQ(STMT s, EXP e) { stmt=s; exp=e; }

  public void dump() { 
    DUMP(" (ESEQ\n"); DUMP(stmt); DUMP(exp); DUMP(")");
  }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

