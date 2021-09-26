//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include <vector>
#include "Matrix.hpp"

int main() {
    std::cout << "Assignment 1, Hello, World!" << std::endl;
    Matrix a;
    Matrix b(8);
    Matrix c(6, 8);

    std::vector<double> v{1, 2, 3, 4};
    Matrix d(v);
    d.setValue(0, 0, 7);
    std::cout << d.getValue(0, 0) << std::endl;

    Matrix e;
    std::cout << std::boolalpha << (a == b) << std::endl;
    std::cout << std::boolalpha << (a == e) << std::endl;
    std::cout << std::boolalpha << (a != e) << std::endl;

    std::cout << d << std::endl;

    return 0;
}
