//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include <vector>
#include "Matrix.hpp"

int main() {
    Matrix a(2, 3);
    a.setValue(0,0,1);
    a.setValue(0,1,2);
    a.setValue(0,2,3);
    a.setValue(1,0,4);
    a.setValue(1,1,5);
    a.setValue(1,2,6);

    Matrix b(3, 2);
    b.setValue(0,0,1);
    b.setValue(0,1,2);
    b.setValue(1,0,3);
    b.setValue(1,1,4);
    b.setValue(2,0,5);
    b.setValue(2,1,6);

    Matrix c = a * b;

    return 0;
}
