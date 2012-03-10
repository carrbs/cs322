// Symbol table module for MINI v1.6. (Jingke Li)
//
// class MethodRec -- method record
//
package symbol;
import ast.*;
import java.util.Vector;

public class MethodRec {
  private Id id;
  private Type rtype;
  private Vector<VarRec> params;
  private Vector<VarRec> locals;

  public MethodRec(Id id, Type type) {
    this.id = id;
    rtype = type;
    params = new Vector<VarRec>();
    locals = new Vector<VarRec>();
  }

  public Id id()   	   { return id; }
  public Type rtype()      { return rtype; }
  public int paramCnt()    { return params.size(); }
  public int localCnt()    { return locals.size(); }

  public VarRec getParam(Id vid) {
    for (int i=0; i<params.size(); i++) {
      VarRec v = params.elementAt(i);
      if (v.id().s.equals(vid.s)) return v;
    }
    return null;
  }
    
  public VarRec getParamAt(int i) {
    if (i<params.size()) 
      return params.elementAt(i);
    return null;
  }

  public VarRec getLocal(Id vid) {
    for (int i=0; i<locals.size(); i++) {
      VarRec v = locals.elementAt(i);
      if (v.id().s.equals(vid.s)) return v;
    }
    return null;
  }
    
  public VarRec getLocalAt(int i) {
    if (i<locals.size())
      return locals.elementAt(i);
    return null;
  }

  public void addParam(Id vid, Type type) throws SymbolException {
    if(getParam(vid) != null) 
      throw new SymbolException("Param " + vid.s + " already defined");
    params.addElement(new VarRec(vid, type, VarRec.PARAM, params.size()+1));
  }
  
  public void addLocal(Id vid, Type type) throws SymbolException {
    if(getLocal(vid) != null) 
      throw new SymbolException("Var " + vid.s + " already defined");
    locals.addElement(new VarRec(vid, type, VarRec.LOCAL, 
				 locals.size()+1));
  }
    
  public void show() {
    String rt = (rtype == null) ? "void" : rtype.toString();
    System.out.println(" <method> " + id.s + " (rtype=" + rt +
		       ", paramCnt=" + paramCnt() + 
		       ", localCnt=" + localCnt() + "):");
    for (int i = 0; i < params.size(); i++) {
      System.out.print("  [param] ");
      params.elementAt(i).show();
    }
    for (int i = 0; i < locals.size(); i++) {
      System.out.print("  [local] ");
      locals.elementAt(i).show();
    }
  }
}



