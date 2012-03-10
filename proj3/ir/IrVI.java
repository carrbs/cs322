package ir;

public interface IrVI {
  public PROG visit(PROG t);
  public FUNC visit(FUNC t);
  public FUNClist visit(FUNClist t);
  //public STMT visit(STMT t);
  public STMT visit(STMTlist t);
  public STMT visit(MOVE t);
  public STMT visit(JUMP t);
  public STMT visit(CJUMP t);
  public STMT visit(LABEL t);
  public STMT visit(CALLST t);
  public STMT visit(RETURN t);
  //public EXP visit(EXP t);
  public EXP visit(EXPlist t);
  public EXP visit(ESEQ t);
  public EXP visit(MEM t);
  public EXP visit(CALL t);
  public EXP visit(BINOP t);
  public EXP visit(NAME t);
  public EXP visit(TEMP t);
  public EXP visit(FIELD t);
  public EXP visit(PARAM t);
  public EXP visit(VAR t);
  public EXP visit(CONST t);
  public EXP visit(FLOAT t);
  public EXP visit(STRING t);
}
