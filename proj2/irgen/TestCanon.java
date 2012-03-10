// Driver program for testing MINI IR canonicalizer v1.6. (Jingke Li)
//
package irgen;
import ir.*;
import irpsr.*;
import java.util.*;

public class TestCanon {
  public static void main(String [] args) {
    try {
      PROG p = new irParser(System.in).Program();
      Canon cv = new Canon();
      PROG p2 = cv.visit(p);
      p2.dump();
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
