// SPARC codegen for MINI v1.6 (Jingke Li)
//
// Reg.java --- registers
//

package codegen;

public class Reg extends Operand {
  static final int FREE=0, USE=1, TEMP=2, RSRV=3;
  String name;
  int status;

  public Reg(String s)         { name=s; status=FREE; }
  public Reg(String s, int st) { name=s; status=st; }

  public void emit() { System.out.print(name); }
}
