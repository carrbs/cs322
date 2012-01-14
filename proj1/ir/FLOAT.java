package ir;

public class FLOAT extends EXP {
  public float val;

  public FLOAT(float v) { val=v; }

  public void dump() { DUMP(" (FLOAT " + val + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}
