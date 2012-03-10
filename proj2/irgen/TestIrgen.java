// Driver program for testing MINI IR generator v1.6. (Jingke Li)
//
package irgen;
import ir.*;
import ast.*;
import astpsr.*;
import symbol.*;
import typechk.*;
import java.io.*;

public class TestIrgen {
  public static void main(String [] args) {
    try {
      if (args.length == 1) {
	FileInputStream stream = new FileInputStream(args[0]);
	Program p = new astParser(stream).Program();
	stream.close();
	SymbolVisitor sv = new SymbolVisitor();
	sv.visit(p);
	TypeVisitor tv = new TypeVisitor(sv.symTable);
	//tv.visit(p);
	IrgenVisitor iv = new IrgenVisitor(sv.symTable, tv);
	PROG ir0 = iv.visit(p);
	Canon cv = new Canon();
	PROG ir = cv.visit(ir0);
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
