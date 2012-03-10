// Driver program for testing MINI symbol table module v1.6. (Jingke Li)
//
package symbol;
import ast.*;
import astpsr.*;
import java.io.*;

public class TestSymbol {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	Program p = new astParser(stream).Program();
	stream.close();
	SymbolVisitor v = new SymbolVisitor();
	p.accept(v);
	v.symTable.show();
      } else {
	System.out.println("You must provide an input file name.");
      }
    }
    catch (SymbolException e) {
      System.err.println(e.toString() + ": " + e.message);
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
