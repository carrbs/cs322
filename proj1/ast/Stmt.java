package ast;
import ir.*;

public abstract class Stmt extends Ast {
  public abstract void accept(TypeVI v) throws Exception;
  public abstract STMT accept(TransVI v) throws Exception;
}
