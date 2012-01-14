// Driver program for testing MINI IR generator v1.6. (Jingke Li)
//
package irgen0;
import ir.*;
import ast.*;
import astpsr.*;
import java.io.*;

public class TestIrgen0 {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	Program p = new astParser(stream).Program();
	stream.close();
	IrgenVisitor0 iv = new IrgenVisitor0();
	PROG ir = iv.visit(p);
	ir.dump();
      } else {
	System.out.println("You must provide an input file name.");
      }
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
