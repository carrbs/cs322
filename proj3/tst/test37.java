// test method calls
//
class body {
  int i;
  public void print() {
    System.out.println(i);
  }
}

class test {
  public static void main(String[] a) {
    body b;
    b = new body();
    b.i = 2;
    b.print();
  }
}
