package ast;
import java.util.Vector;

public class AstList extends Ast {
  private Vector<Ast> list;

  public AstList() { list = new Vector<Ast>(); }
  public void add(Ast n) { list.addElement(n); }
  public void addAll(AstList l) { list.addAll(l.list); }
  public Ast elementAt(int i) { return list.elementAt(i); }
  public int size() { return list.size(); }

  public void dump() {
    for (int i=0; i<size(); i++) 
      elementAt(i).dump();
  }
  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
}
