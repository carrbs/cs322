// Driver program for testing SPARC codegen (MINI v1.6). (Jingke Li)
//
package codegen;
import ir.*;
import irpsr.*;
import java.io.*;

public class TestCodegen {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	PROG p = new irParser(stream).Program();
	stream.close();
	CodegenVisitor cv = new CodegenVisitor();
	cv.visit(p);
      } else {
	throw new Exception("You must provide a parameter of one file name.");
      }
    }
    catch (CodegenException e) {
      System.err.println(e.toString() + ": " + e.message);
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
