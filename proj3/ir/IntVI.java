package ir;

public interface IntVI {
  // Program and functions
  public void visit(PROG t) throws Exception;
  public void visit(FUNC t) throws Exception;
  public void visit(FUNClist t) throws Exception;
  // Statements
  public int visit(STMTlist t) throws Exception;
  public int visit(MOVE t) throws Exception;
  public int visit(JUMP t) throws Exception;
  public int visit(CJUMP t) throws Exception;
  public int visit(LABEL t) throws Exception;
  public int visit(CALLST t) throws Exception;
  public int visit(RETURN t) throws Exception;
  // Expressions
  public int visit(EXPlist t) throws Exception;
  public int visit(ESEQ t) throws Exception;
  public int visit(MEM t) throws Exception;
  public int visit(CALL t) throws Exception;
  public int visit(BINOP t) throws Exception;
  public int visit(NAME t) throws Exception;
  public int visit(TEMP t) throws Exception;
  public int visit(FIELD t) throws Exception;
  public int visit(PARAM t) throws Exception;
  public int visit(VAR t) throws Exception;
  public int visit(CONST t) throws Exception;
  public int visit(FLOAT t) throws Exception;
  public int visit(STRING t) throws Exception;
}
