package ir;
import codegen.*;

public abstract class STMT extends IR {
  public abstract STMT accept(IrVI v);
  public abstract int accept(IntVI v) throws Exception;
  public abstract void accept(CodeVI v) throws Exception;
}
