// A visitor interface for IR codegen for MINI v1.6. (Jingke Li)
//
package ast;
import ir.*;

public interface TransVI {
  public PROG visit(Program n) throws Exception;

  // Declarations
  public FUNClist visit(ClassDeclList n) throws Exception;
  public FUNClist visit(ClassDecl n) throws Exception;
  public FUNClist visit(MethodDeclList n) throws Exception;
  public FUNC visit(MethodDecl n) throws Exception;
  public STMTlist visit(VarDeclList n) throws Exception;
  public STMT visit(VarDecl n) throws Exception;

  // Types --- no need to process these nodes

  // Statements
  public STMTlist visit(StmtList n) throws Exception;
  public STMT visit(Block n) throws Exception;
  public STMT visit(Assign n) throws Exception;
  public STMT visit(CallStmt n) throws Exception;
  public STMT visit(If n) throws Exception;
  public STMT visit(While n) throws Exception;
  public STMT visit(Print n) throws Exception;
  public STMT visit(Return n) throws Exception;

  // Expressions
  public EXPlist visit(ExpList n) throws Exception;
  public EXP visit(Binop n) throws Exception;
  public EXP visit(Relop n) throws Exception;
  public EXP visit(Unop n) throws Exception;
  public EXP visit(ArrayElm n) throws Exception;
  public EXP visit(ArrayLen n) throws Exception;
  public EXP visit(Field n) throws Exception;
  public EXP visit(Call n) throws Exception;
  public EXP visit(NewArray n) throws Exception;
  public EXP visit(NewObj n) throws Exception;
  public EXP visit(Id n) throws Exception;
  public EXP visit(This n);

  // Base values
  public EXP visit(IntVal n);
  public EXP visit(FloatVal n);
  public EXP visit(BoolVal n);
  public EXP visit(StrVal n);
}
