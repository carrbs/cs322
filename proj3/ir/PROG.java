package ir;

public class PROG extends IR {
  public FUNClist funcs;

  public PROG(FUNClist fl) { funcs = fl; }

  public void dump() { DUMP("IR_PROGRAM\n"); funcs.dump(); }
  public PROG accept(IrVI v) { return v.visit(this); }
  public void accept(IntVI v) throws Exception { v.visit(this); }
}
