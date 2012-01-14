package ir;
import java.util.Vector;

public class FUNClist extends Vector<FUNC> {
  public void dump() { 
    for (int i=0; i<size(); i++)
      elementAt(i).dump();
  }

  public FUNClist accept(IrVI v) { return v.visit(this); }
}



