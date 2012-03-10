package ir;
import codegen.*;

public abstract class EXP extends IR {
  public abstract EXP accept(IrVI v);
  public abstract int accept(IntVI v) throws Exception;
  public abstract Operand accept(CodeVI v) throws Exception;
}


