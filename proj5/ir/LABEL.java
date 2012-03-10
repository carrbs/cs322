package ir;
import codegen.*;

public class LABEL extends STMT { 
  public String lab;

  public LABEL(String s) { lab=s; }
  public LABEL(NAME n)   { lab=n.id; }

  public void dump() { DUMP(" [LABEL " + lab +"]\n"); }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public void accept(CodeVI v) throws Exception { v.visit(this); }
}

