// SPARC codegen for MINI v1.6 (Jingke Li)
//
// RegOff.java --- stack locations
//

package codegen;

public class RegOff extends Operand {
  Reg r;
  int o;

  public RegOff(Reg ar, int ao) { r=ar; o=ao; }

  public void emit() {
    r.emit();
    if (o>0) System.out.print("+" + o);
    else if (o<0) System.out.print(o);
  }
}
