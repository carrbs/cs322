// A visitor interface for type checking MINI v1.6. (Jingke Li)
//
package ast;

public interface TypeVI {
  public void visit(Program n) throws Exception;

  // Lists
  public void visit(AstList n) throws Exception;
  public void visit(ClassDeclList n) throws Exception;
  public void visit(MethodDeclList n) throws Exception;
  public void visit(VarDeclList n) throws Exception;
  public void visit(FormalList n) throws Exception;
  public void visit(StmtList n) throws Exception;
  public void visit(ExpList n) throws Exception;

  // Declarations
  public void visit(ClassDecl n) throws Exception;
  public void visit(MethodDecl n) throws Exception;
  public void visit(VarDecl n) throws Exception;
  public void visit(Formal n) throws Exception;

  // Types
  public Type visit(BasicType n);
  public Type visit(ObjType n) throws Exception;
  public Type visit(ArrayType n);

  // Statements
  public void visit(Block n) throws Exception;
  public void visit(Assign n) throws Exception;
  public void visit(CallStmt n) throws Exception;
  public void visit(If n) throws Exception;
  public void visit(While n) throws Exception;
  public void visit(Print n) throws Exception;
  public void visit(Return n) throws Exception;

  // Expressions
  public Type visit(Binop n) throws Exception;
  public Type visit(Relop n) throws Exception;
  public Type visit(Unop n) throws Exception;
  public Type visit(ArrayElm n) throws Exception;
  public Type visit(ArrayLen n) throws Exception;
  public Type visit(Field n) throws Exception;
  public Type visit(Call n) throws Exception;
  public Type visit(NewArray n) throws Exception;
  public Type visit(NewObj n) throws Exception;
  public Type visit(Id n) throws Exception;
  public Type visit(This n) throws Exception;

  // Base values
  public Type visit(IntVal n);
  public Type visit(FloatVal n);
  public Type visit(BoolVal n);
  public Type visit(StrVal n);
}
