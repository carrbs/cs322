package ast;
import ir.*;

public class MethodDeclList extends AstList {
  public MethodDeclList() { super(); }
  public void add(MethodDecl n) { super.add(n); }
  public MethodDecl elementAt(int i)  { 
    return (MethodDecl)super.elementAt(i); 
  }
  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public FUNClist accept(TransVI v) throws Exception { return v.visit(this); }
}
