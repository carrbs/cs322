// class decls
// (should print 1 1)
class body {
  int i;

  public int foo(int i) {
    int y;
    return i;
  }
}  

class test {
  public int foo(int i) {
    int x;
    return i;
  }

  public static void main(String[] a) {
    body b = new body();
    int i = foo(1);
    int j = b.foo(1);
    System.out.println(i);
    System.out.println(j);
  }
}
