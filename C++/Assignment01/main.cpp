//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include <vector>
#include "Connectivity.hpp"
#include "Matrix.hpp"

int main() {
    /*Connectivity c("connectivity.txt");*/
    Matrix a(2);
    Matrix b(2);
    b.setValue(1, 1, 1);
    std::cout << a + b << std::endl;
    return 0;
}
