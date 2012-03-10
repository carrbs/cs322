package ast;
import ir.*;

public class VarDecl extends Ast {
  public Type t;
  public Id var;
  public Exp e;
  
  public VarDecl(Type at, Id i, Exp ae) { t=at; var=i; e=ae; }

  public void dump() { 
    DUMP("(VarDecl "); DUMP(t); DUMP(var); DUMP(e); DUMP(") "); 
  }

  public void accept(VoidVI v) { v.visit(this); }
  public void accept(TypeVI v) throws Exception { v.visit(this); }
  public STMT accept(TransVI v) throws Exception { return v.visit(this); }
}
