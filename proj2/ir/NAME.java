package ir;

public class NAME extends EXP {
  public static int count=0;
  public String id;

  public NAME() { id = "L" + count++; }
  public NAME(String n) { id = n; }

  public void dump() { DUMP(" (NAME " + id + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
}
