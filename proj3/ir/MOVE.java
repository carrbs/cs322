package ir;

public class MOVE extends STMT {
  public EXP dst, src;

  public MOVE(EXP d, EXP s) { dst=d; src=s; }

  public void dump() { 
    DUMP(" [MOVE"); DUMP(dst); DUMP(src); DUMP("]\n"); 
  }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
}
