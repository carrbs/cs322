package ir;

public class CONST extends EXP {
  public int val;

  public CONST(int v) { val=v; }

  public void dump() { DUMP(" (CONST " + val + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}

