// test floating point
// (should print 15.5)
class test {
  public static void main(String[] a) {
    A x = new A();
    System.out.println(x.getfval(5));
  }
}

class A {
  public float getfval(int i) {
    float x = 3.1f;
    return x * i;
  }
}
