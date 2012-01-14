package ast;

public abstract class Type extends Ast {

  public abstract String toString();

  public abstract Type accept(TypeVI v) throws Exception;
}
