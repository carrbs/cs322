package ir;

public class STRING extends EXP {
  public String s;

  public STRING(String v) { s=v; }

  public void dump() { DUMP(" (STRING \"" + s + "\")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}

