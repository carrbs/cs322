package ir;

public class RETURN extends STMT {
  public EXP exp;

  public RETURN() { exp=null; }
  public RETURN(EXP e) { exp=e; }

  public void dump() { 
    DUMP(" [RETURN"); if (exp!=null) exp.dump(); DUMP("]\n");
  }

  public STMT accept(IrVI v) { return v.visit(this); }
}
