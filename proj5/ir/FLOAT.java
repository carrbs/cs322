package ir;
import codegen.*;

public class FLOAT extends EXP {
  public float val;

  public FLOAT(float v) { val=v; }

  public void dump() { DUMP(" (FLOAT " + val + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}
