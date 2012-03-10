package ir;
import codegen.*;

public class VAR extends EXP {
  public int idx;

  public VAR(int i) { idx=i; }

  public void dump() { DUMP(" (VAR " + idx + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

