package ir;
import codegen.*;

public class FUNC extends IR {
  public String label;
  public int varCnt, argCnt;
  public STMTlist stmts;

  public FUNC(String l, int vc, int ac, STMTlist sl) {
    label=l; varCnt=vc; argCnt=ac; stmts=sl; 
  }

  public void dump() { 
    DUMP("" + label + " (locals=" + varCnt 
	 + ", max_args=" + argCnt + ") {\n"); 
    DUMP(stmts);
    DUMP("}\n");
  }

  public FUNC accept(IrVI v) { return v.visit(this); }
  public void accept(IntVI v) throws Exception { v.visit(this); }
  public void accept(CodeVI v) throws Exception { v.visit(this); }
}
