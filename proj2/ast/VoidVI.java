// A simple visitor interface for MINI v1.6. (Jingke Li)
//
package ast;

public interface VoidVI {
  public void visit(Program n);

  // Lists
  public void visit(AstList n);
  public void visit(ClassDeclList n);
  public void visit(MethodDeclList n);
  public void visit(VarDeclList n);
  public void visit(FormalList n);
  public void visit(StmtList n);
  public void visit(ExpList n);

  // Declarations
  public void visit(ClassDecl n);
  public void visit(MethodDecl n);
  public void visit(VarDecl n);
  public void visit(Formal n);

  // Types
  public void visit(BasicType n);
  public void visit(ObjType n);
  public void visit(ArrayType n);

  // Statements
  public void visit(Block n);
  public void visit(Assign n);
  public void visit(CallStmt n);
  public void visit(If n);
  public void visit(While n);
  public void visit(Print n);
  public void visit(Return n);

  // Expressions
  public void visit(Binop n);
  public void visit(Relop n);
  public void visit(Unop n);
  public void visit(ArrayElm n);
  public void visit(ArrayLen n);
  public void visit(Field n);
  public void visit(Call n);
  public void visit(NewArray n);
  public void visit(NewObj n);

  // Base values
  public void visit(Id n);
  public void visit(This n);
  public void visit(IntVal n);
  public void visit(FloatVal n);
  public void visit(BoolVal n);
  public void visit(StrVal n);
}
