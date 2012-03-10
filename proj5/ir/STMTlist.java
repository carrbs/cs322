package ir;
import codegen.*;
import java.util.Vector;

public class STMTlist extends STMT {
  private Vector<STMT> list;

  public STMTlist() { list = new Vector<STMT>(); }

  public STMT elementAt(int i) { return list.elementAt(i); }
  public int size() { return list.size(); }

  public void add(STMT s) { 
    if (s instanceof STMTlist) {
      STMTlist sl = (STMTlist) s;
      for (int i=0; i<sl.size(); i++)
	list.addElement(sl.elementAt(i));
    } else {
      list.addElement(s); 
    }
  }

  public void dump() { 
    for (int i=0; i<size(); i++)
      DUMP(elementAt(i));
  }

  public STMT accept(IrVI v) { return v.visit(this); }
  public int accept(IntVI v) throws Exception { return v.visit(this); }
  public void accept(CodeVI v) throws Exception { v.visit(this); }
}



