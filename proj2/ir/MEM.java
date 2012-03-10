package ir;

public class MEM extends EXP {
  public EXP exp;

  public MEM(EXP e) { exp=e; }

  public void dump() { DUMP(" (MEM"); DUMP(exp); DUMP(")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}

