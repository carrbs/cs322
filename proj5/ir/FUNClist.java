package ir;
import codegen.*;
import java.util.Vector;

public class FUNClist extends Vector<FUNC> {
  public void dump() { 
    for (int i=0; i<size(); i++)
      elementAt(i).dump();
  }

  public FUNClist accept(IrVI v) { return v.visit(this); }
  public void accept(IntVI v) throws Exception { v.visit(this); }
  public void accept(CodeVI v) throws Exception { v.visit(this); }
}



