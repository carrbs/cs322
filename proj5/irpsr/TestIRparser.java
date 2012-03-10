// CS321 Winter'06 (Jingke Li)
//
// TestIRparser.java --- The driver class for the type-checker.
//
package irpsr;
import ir.*;

public class TestIRparser {
  public static void main(String [] args) {
    try {
      PROG p = new irParser(System.in).Program();
      p.dump();
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
