// CS322 (Jingke Li)
//
// TestInterp.java --- A driver program for the interpreter.
//
package interp;
import ir.*;
import irpsr.*;
import java.io.*;

public class TestInterp {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	PROG p = new irParser(stream).Program();
	stream.close();
	InterpVisitor iv = new InterpVisitor();
	iv.visit(p);
      } else {
	System.out.println("You must provide an input file name.");
      }
    }
    catch (InterpException e) {
      System.err.println(e.toString() + ": " + e.message);
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
