package ir;

public class ESEQ extends EXP {
  public STMT stmt;
  public EXP exp;

  public ESEQ(STMT s, EXP e) { stmt=s; exp=e; }

  public void dump() { 
    DUMP(" (ESEQ\n"); DUMP(stmt); DUMP(exp); DUMP(")");
  }

  public EXP accept(IrVI v) { return v.visit(this); }
}

