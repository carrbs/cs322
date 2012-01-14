package ir;

public class VAR extends EXP {
  public int idx;

  public VAR(int i) { idx=i; }

  public void dump() { DUMP(" (VAR " + idx + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}

