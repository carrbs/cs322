package ir;

public abstract class STMT extends IR {
  public abstract STMT accept(IrVI v);
  public abstract int accept(IntVI v) throws Exception;
}
