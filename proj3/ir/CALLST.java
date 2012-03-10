package ir;

public class CALLST extends STMT {
  public NAME func;
  public EXPlist args;

  public CALLST(NAME f, EXPlist a) { func=f; args=a; }

  public void dump() { 
    DUMP(" [CALLST"); DUMP(func); DUMP(args); DUMP("]\n");
  }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
}

