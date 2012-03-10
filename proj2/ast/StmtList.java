package ast;
import ir.*;

public class StmtList extends AstList {
  public StmtList() { super(); }
  public void add(Stmt n) { super.add(n); }
  public Stmt elementAt(int i)  { return (Stmt)super.elementAt(i); }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMTlist accept(TransVI v) throws Exception { return v.visit(this); }
}
