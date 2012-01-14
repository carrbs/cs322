package ast;
import ir.*;

public class ClassDeclList extends AstList {
  public ClassDeclList() { super(); }
  public void add(ClassDecl n) { super.add(n); }
  public ClassDecl elementAt(int i)  { 
    return (ClassDecl)super.elementAt(i); 
  }
  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public FUNClist accept(TransVI v) throws Exception { return v.visit(this); }
}
