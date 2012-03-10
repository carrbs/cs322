package ir;
import codegen.*;

public class CALL extends EXP {
  public NAME func;
  public EXPlist args;

  public CALL(NAME f, EXPlist a) { func=f; args=a; }

  public void dump() { 
    DUMP(" (CALL"); DUMP(func); DUMP(args); DUMP(")");
  }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public Operand accept(CodeVI v) throws Exception { return v.visit(this); }
}

