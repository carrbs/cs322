package ir;
import codegen.*;

public class CONST extends EXP {
  public int val;

  public CONST(int v) { val=v; }

  public void dump() { DUMP(" (CONST " + val + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

