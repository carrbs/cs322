package ir;
import codegen.*;

public class TEMP extends EXP {
  private static int count=0;
  public int num;
  
  public TEMP() { num = ++count; }
  public TEMP(int n) { num=n; }

  public void dump() { DUMP(" (TEMP " + num + ")"); }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

