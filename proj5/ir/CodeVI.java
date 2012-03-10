package ir;
import codegen.*;

public interface CodeVI {
  public void visit(PROG t) throws Exception;
  public void visit(FUNC t) throws Exception;
  public void visit(FUNClist t) throws Exception;
  //public void visit(STMT t) throws Exception;
  public void visit(STMTlist t) throws Exception;
  public void visit(MOVE t) throws Exception;
  public void visit(JUMP t) throws Exception;
  public void visit(CJUMP t) throws Exception;
  public void visit(LABEL t) throws Exception;
  public void visit(CALLST t) throws Exception;
  public void visit(RETURN t) throws Exception;
  //public Operand visit(EXP t) throws Exception;
  public Operand visit(EXPlist t) throws Exception;
  public Operand visit(ESEQ t) throws Exception;
  public Operand visit(MEM t) throws Exception;
  public Operand visit(CALL t) throws Exception;
  public Operand visit(BINOP t) throws Exception;
  public Operand visit(NAME t) throws Exception;
  public Operand visit(TEMP t) throws Exception;
  public Operand visit(FIELD t) throws Exception;
  public Operand visit(PARAM t) throws Exception;
  public Operand visit(VAR t) throws Exception;
  public Operand visit(CONST t) throws Exception;
  public Operand visit(FLOAT t) throws Exception;
  public Operand visit(STRING t) throws Exception;
}
