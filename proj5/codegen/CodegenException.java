package codegen;

public class CodegenException extends Exception {
  public String message;
  public CodegenException(String msg) { message = msg; }
}
