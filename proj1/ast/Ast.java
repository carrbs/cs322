package ast;

public abstract class Ast {

  public static void DUMP(String s) { System.out.print(s); }
  public static void DUMP(int i) { System.out.print(i); }

  public static void DUMP(String name, AstList l) {
    System.out.print("(" + name + " "); 
    if (l!=null) l.dump();
    System.out.print(") "); 
  }      

  public static void DUMP(Type t) { 
    if (t==null) System.out.print("(NullType) "); 
    else         t.dump(); 
  }

  public static void DUMP(Stmt s) { 
    if (s==null) System.out.print("(NullStmt) "); 
    else         s.dump(); 
  }

  public static void DUMP(Exp e) { 
    if (e==null) System.out.print("(NullExp) "); 
    else         e.dump(); 
  }

  public abstract void dump();
  public abstract void accept(VoidVI v);
}
