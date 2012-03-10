// Driver program for testing MINI type-checker v1.6. (Jingke Li)
//
package typechk;
import ast.*;
import astpsr.*;
import symbol.*;
import java.io.*;

public class TestType {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	Program p = new astParser(stream).Program();
	stream.close();
	SymbolVisitor sv = new SymbolVisitor();
	sv.visit(p);
	TypeVisitor tv = new TypeVisitor(sv.symTable);
	tv.visit(p);
      } else {
	System.out.println("You must provide an input file name.");
      }
    }
    catch (TypeException e) {
      System.err.println(e.toString() + ": " + e.message);
    }
    catch (SymbolException e) {
      System.err.println(e.toString() + ": " + e.message);
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
