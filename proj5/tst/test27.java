// mutually dependent class decls
class A {
  B b;
}  

class B {
  A a;
}  

class test {
  public static void main(String[] a) {
    A x = new A();
    B y = new B();
  }
}
