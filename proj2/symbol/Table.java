// Symbol table module for MINI v1.6. (Jingke Li)
//
// class Table -- top-level symbol table
//
package symbol;
import ast.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class Table {
  private Hashtable<String,ClassRec> classes;	// a table of ClassRecs
  
  public Table() { classes = new Hashtable<String,ClassRec>(); }
  
  public void addClass(Id cid) throws SymbolException {
    Object old_entry = classes.put(cid.s, new ClassRec(cid));
    if (old_entry != null) 
      throw new SymbolException("Class " + cid.s + " already defined");
  }
  
  public ClassRec getClass(Id cid) throws SymbolException {
    ClassRec c = classes.get(cid.s); 
    if (c == null)
      throw new SymbolException("Class " + cid.s + " not defined");
    return c;
  }
  
  public MethodRec getMethod(ClassRec c, Id mid) throws SymbolException {
    MethodRec m;
    while (c != null) {
      if ((m=c.getMethod(mid)) != null) return m;
      c = c.parent();
    }
    throw new SymbolException("Method " + mid.s + " not defined");
  }
  
  // for future use (irgen)
  public String uniqueMethodName(ClassRec c, Id mid) throws SymbolException {
    MethodRec m;
    while (c != null) {
      if ((m=c.getMethod(mid)) != null) return c.id().s + "_" + mid.s;
      c = c.parent();
    }
    throw new SymbolException("Method " + mid.s + " not defined");
  }
  
  public VarRec getVar(ClassRec c, MethodRec m, Id vid) 
                          throws SymbolException {
    VarRec v;
    if (m != null) {
      if ((v=m.getLocal(vid)) != null) return v;
      if ((v=m.getParam(vid)) != null) return v;
    }
    return getVar(c, vid);
  }
  
  public VarRec getVar(ClassRec c, Id vid) throws SymbolException {
    VarRec v;
    while (c != null) {
      if ((v = c.getClassVar(vid)) != null) return v;
      c = c.parent();
    }
    throw new SymbolException("Var " + vid.s + " not defined");
  }
  
  public void show() {
    System.out.println("Symbol Table:");
    if (classes != null) {
      for (Enumeration e = classes.elements(); e.hasMoreElements();) {
	((ClassRec) e.nextElement()).show();
      }
      System.out.println();
    }
  }
}
