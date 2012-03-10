// SPARC codegen for MINI v1.6 (Jingke Li)
//
// Immed.java --- immediate value
//

package codegen;

public class Immed extends Operand {
  int i;
  Boolean is13b;  	// flag for 13-bit value  

  public Immed(int ai) {
    i = ai; 
    is13b = ((ai >= -4096) && (ai < 4096)) ? true : false;
  }

  public void emit() { System.out.print(i); }
}
