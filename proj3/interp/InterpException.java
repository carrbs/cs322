package interp;

public class InterpException extends Exception {
  public String message;
  public InterpException(String msg) { message = msg; }
}
