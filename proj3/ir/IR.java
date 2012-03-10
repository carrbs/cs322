package ir;

public abstract class IR {
  public static void DUMP(String s) { 
    System.out.print(s); 
  }
  public static void DUMP(STMT s) { 
    if (s!=null) s.dump(); else DUMP(" [null]\n"); 
  }
  public static void DUMP(EXP e) { 
    if (e!=null) e.dump(); else DUMP(" (null)"); 
  }
  public abstract void dump();
}
