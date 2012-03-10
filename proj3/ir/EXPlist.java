package ir;
import java.util.Vector;

public class EXPlist extends EXP {
  private Vector<EXP> list;

  public EXPlist() { list = new Vector<EXP>(); }
  public EXPlist(EXP e) { list = new Vector<EXP>(); list.add(e); }

  public void add(EXP e) { list.addElement(e); }
  public void addAll(EXPlist el) { list.addAll(el.list); }
  public EXP elementAt(int i) { return list.elementAt(i); }
  public int size() { return list.size(); }

  public void dump() {
    System.out.print(" ("); 
    for (int i=0; i<size(); i++)
      DUMP(elementAt(i));
    System.out.print(")"); 
  }

  public EXP accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
}
