// Symbol table module for MINI v1.6. (Jingke Li)
//
// class ClassRec -- class record
//
package symbol;
import ast.*;
import java.util.*;

public class ClassRec {
  private Id id;
  private ClassRec parent;
  private Vector<VarRec> class_vars;
  private Hashtable<String,MethodRec> methods;
    
  public ClassRec(Id cid) {
    id = cid;
    parent = null;
    class_vars = new Vector<VarRec>();
    methods = new Hashtable<String,MethodRec>();
  }
    
  public Id id()           { return id; }
  public ClassRec parent() { return parent; }
  
  public int varCnt() { 
    return class_vars.size(); 
  }

  public VarRec getClassVar(Id vid) {
    for (int i=0; i<class_vars.size(); i++) {
      VarRec v = class_vars.elementAt(i);
      if (v.id().s.equals(vid.s)) return v;
    }
    return null;
  }
    
  public VarRec getClassVarAt(int i) {
    if (i<class_vars.size()) 
      return class_vars.elementAt(i);
    return null;
  }

  public void addClassVar(Id vid, Type type, Exp e) throws SymbolException {
    if(getClassVar(vid) != null) 
      throw new SymbolException("ClassVar " + vid.s + " already defined");
    class_vars.addElement(new VarRec(vid, type, VarRec.CLASS, 
				     class_vars.size()+1, e));
  }
  
  private int ancestorVarCnt() {
    if (parent != null) 
      return parent.ancestorVarCnt() + parent.varCnt();
    return 0;
  }      

  public void linkParent(ClassRec p) {
    parent = p;
    int start_idx = ancestorVarCnt() + 1;
    for (int i = 0; i < class_vars.size(); i++) 
      class_vars.elementAt(i).setIdx(start_idx + i);
  }

  public MethodRec getMethod(Id mid) {
    return methods.get(mid.s); // may return null
  }
  
  public void addMethod(Id mid, Type rtype) throws SymbolException {
    MethodRec m = methods.put(mid.s, new MethodRec(mid, rtype));
    if (m != null)
      throw new SymbolException("Method " + mid.s + " already defined");
  }
  
  public void show() {
    System.out.println("Class " + id.s + " (pid=" 
		       + (parent==null ? "null" : parent.id().s) + "):");
    for (int i = 0; i < class_vars.size(); i++) {
      System.out.print("  [cl var] ");
      class_vars.elementAt(i).show();
    }
    for (Enumeration e = methods.elements(); e.hasMoreElements();) {
      ((MethodRec)e.nextElement()).show();
    }
  }
}

