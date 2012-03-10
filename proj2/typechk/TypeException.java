package typechk;

public class TypeException extends Exception {
  public String message;
  public TypeException(String msg) { message = msg; }
}
