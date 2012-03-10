// Symbol table module for MINI v1.6. (Jingke Li)
//
// class VarRec -- variable and parameter record
//
package symbol;
import ast.*;

public class VarRec {
  public static final int CLASS=0, LOCAL=1, PARAM=2;
  private Id id;
  private Type type;
  private int kind;  // one of the three categories
  private int idx;   // position in its scope
  private Exp init;  // initial value
  
  public VarRec(Id vid, Type vtype, int vkind, int vidx) {
    id = vid;
    type = vtype;
    kind = vkind;
    idx = vidx;
    init = null;
  }

  public VarRec(Id vid, Type vtype, int vkind, int vidx, Exp e) {
    id = vid;
    type = vtype;
    kind = vkind;
    idx = vidx;
    init = e;
  }

  public Id id()     { return id; }
  public Type type() { return type; }
  public int kind()  { return kind; }
  public int idx()   { return idx; }
  public Exp init()  { return init; }

  public void setIdx(int vidx) { idx = vidx; }

  public void show() {
    System.out.print("(" + idx + ") " + id.s + " " 
		     + type.toString());
    if (init != null) {
      System.out.print(" ");
      init.dump();
    }
    System.out.println();
  }	
}

