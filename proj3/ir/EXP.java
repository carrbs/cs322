package ir;

public abstract class EXP extends IR {
  public abstract EXP accept(IrVI v);
  public abstract int accept(IntVI v) throws Exception;
}


