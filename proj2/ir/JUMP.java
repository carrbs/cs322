package ir;

public class JUMP extends STMT {
  public NAME target;

  public JUMP(NAME e) { target=e; }

  public void dump() { DUMP(" [JUMP"); DUMP(target); DUMP("]\n"); }

  public STMT accept(IrVI v) { return v.visit(this); }
}

