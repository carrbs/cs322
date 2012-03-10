// Driver program for testing MINI AST parser v1.3. (Jingke Li)
//
package astpsr;
import ast.*;
import java.io.*;

public class TestASTparser {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	Program p = new astParser(stream).Program();
	stream.close();
	p.dump();
      } else {
	throw new Exception("You must provide a parameter of one file name.");
      }
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
