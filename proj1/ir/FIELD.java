package ir;

public class FIELD extends EXP {
  public EXP obj;
  public int idx;

  public FIELD(EXP o, int i) { obj=o; idx=i; }

  public void dump() { DUMP(" (FIELD"); DUMP(obj); DUMP(" " + idx + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}

