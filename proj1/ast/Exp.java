package ast;
import ir.*;

public abstract class Exp extends Ast {
  public abstract Type accept(TypeVI v) throws Exception;
  public abstract EXP accept(TransVI v) throws Exception;
}
