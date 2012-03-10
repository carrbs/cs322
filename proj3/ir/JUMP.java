package ir;

public class JUMP extends STMT {
  public NAME target;

  public JUMP(NAME e) { target=e; }

  public void dump() { DUMP(" [JUMP"); DUMP(target); DUMP("]\n"); }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
}

