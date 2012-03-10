#include <iostream>
using namespace std;


class pbr {
    public:
        int a;
        pbr(int a);
};

pbr::pbr(int a){
    this->a = a;
}


pbr & a = *(new pbr(1));
pbr & b = *(new pbr(2));

void p(pbr &x, pbr &y) {
    x.a = a.a + 3;
    a.a = a.a+y.a;
    y.a = x.a+1;
}

int main() {

    p(a,b);
    cout << a.a << endl << b.a << endl;
    return 0;
}


