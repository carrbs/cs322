// SymbolVisitor.java --- Processing declarations and creating symbol tables.
// for MINI 1.6 (Jingke Li)
//
package symbol;
import ast.*;
import java.util.Hashtable;
import java.util.Vector;

public class SymbolVisitor implements TypeVI {
  public Table symTable;         // the top-scope symbol table
  private ClassRec currClass;    // the current class scope
  private MethodRec currMethod;  // the current method scope
  private boolean hasMain;       // whether "main" method is defined

  public SymbolVisitor() {
    symTable = new symbol.Table();
    currClass = null;
    currMethod = null;
    hasMain = false;
  }

  private void setupClassHierarchy(ClassDeclList cl) throws Exception {
    Vector<ClassDecl> work = new Vector<ClassDecl>();
    Vector<String> done = new Vector<String>();
    for (int i=0; i<cl.size(); i++)
      work.add(cl.elementAt(i));
    while (work.size() > 0) {
      for (int i=0; i<work.size(); i++) {
	ClassDecl cd = (ClassDecl) work.elementAt(i);
	if (cd.pid != null) {
	  if (!done.contains(cd.pid.s)) {
	    continue;
	  }
	  ClassRec cr = symTable.getClass(cd.cid);
	  ClassRec pr = symTable.getClass(cd.pid);
	  cr.linkParent(pr);
	}
	done.add(cd.cid.s);
	work.remove(cd);
      }
    }
  }

  // Program ---
  // ClassDeclList cl;
  public void visit(Program n) throws Exception {
    n.cl.accept(this);
    if (!hasMain)
      throw new SymbolException("Method main is missing");
    setupClassHierarchy(n.cl); // establish class hierarchy
  }

  // LISTS --- use default traversal
  
  public void visit(AstList n) throws Exception {}

  public void visit(ClassDeclList n) throws Exception {
    for (int i = 0; i < n.size(); i++)
      n.elementAt(i).accept(this);
  }
  
  public void visit(VarDeclList n) throws Exception {
    for (int i = 0; i < n.size(); i++)
      n.elementAt(i).accept(this);
  }

  public void visit(MethodDeclList n) throws Exception {
    for (int i = 0; i < n.size(); i++)
      n.elementAt(i).accept(this);
  }

  public void visit(FormalList n) throws Exception {
    for (int i = 0; i < n.size(); i++)
      n.elementAt(i).accept(this);
  }

  // DECLARATIONS
  
  // ClassDecl ---
  // Id cid, pid;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDecl n) throws Exception {
    symTable.addClass(n.cid);
    currClass = symTable.getClass(n.cid);
    n.vl.accept(this);
    n.ml.accept(this);
    currClass = null;
  }
  
  // MethodDecl ---
  // Type t;
  // Id mid;
  // FormalList fl;
  // VarDeclList vl;
  // StmtList sl;
  public void visit(MethodDecl n) throws Exception {
    currClass.addMethod(n.mid, n.t);
    currMethod = currClass.getMethod(n.mid);
    n.fl.accept(this);
    n.vl.accept(this);
    if (n.mid.s.equals("main")) {
      hasMain = true;
      if (currClass.varCnt() > 0)
	throw new SymbolException(
                    "main class are not allowed to have data fields");
    }
    currMethod = null;
  }
  
  // VarDecl ---
  // Type t;
  // Id var;
  // Exp e;
  public void visit(VarDecl n) throws Exception {
    if (currMethod != null) { 	// a local variable	
      if (currMethod.getParam(n.var) != null)
	throw new SymbolException(
		    n.var.s + " already defined as a param");
      currMethod.addLocal(n.var, n.t);
    } else { 			// a class variable
      currClass.addClassVar(n.var, n.t, n.e);
    }
  }
  
  // Formal ---
  // Type t;
  // Id id;
  public void visit(Formal n) throws Exception {
    if (currMethod == null) 
      throw new SymbolException("currMethod does not exits");
    currMethod.addParam(n.id, n.t);
  }

  // TYPES --- use the nodes themselves 

  public Type visit(BasicType n) { return n; }
  public Type visit(ObjType n) throws Exception { return n; }
  public Type visit(ArrayType n) { return n; }

  // No need to implement statements or expressions nodes.

  public void visit(StmtList n) throws Exception {}
  public void visit(Block n) throws Exception {}
  public void visit(Assign n) throws Exception {}
  public void visit(CallStmt n) throws Exception {}
  public void visit(If n) throws Exception {}
  public void visit(While n) throws Exception {}
  public void visit(Print n) throws Exception {}
  public void visit(Return n) throws Exception {}

  public void visit(ExpList n) throws Exception {}
  public Type visit(Binop n) throws Exception    { return null; }
  public Type visit(Relop n) throws Exception    { return null; }
  public Type visit(Unop n) throws Exception 	 { return null; }
  public Type visit(ArrayElm n) throws Exception { return null; }
  public Type visit(ArrayLen n) throws Exception { return null; }
  public Type visit(Field n) throws Exception    { return null; }
  public Type visit(Call n) throws Exception     { return null; }
  public Type visit(NewArray n) throws Exception { return null; }
  public Type visit(NewObj n) throws Exception   { return null; }
  public Type visit(Id n) throws Exception       { return null; }
  public Type visit(This n) throws Exception     { return null; }

  public Type visit(IntVal n)   { return null; }
  public Type visit(FloatVal n) { return null; }
  public Type visit(BoolVal n)  { return null; }
  public Type visit(StrVal n)   { return null; }

}
