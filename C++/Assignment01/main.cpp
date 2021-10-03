//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include "PageRank.hpp"

int main() {
    PageRank pageRank("../connectivity.txt");
    std::cout << pageRank << std::endl;
    return 0;
}
