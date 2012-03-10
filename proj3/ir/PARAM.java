package ir;

public class PARAM extends EXP {
  public int idx;

  public PARAM(int i) { idx=i; }

  public void dump() { DUMP(" (PARAM " + idx + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
}

