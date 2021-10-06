//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include "PageRank.hpp"

int main() {
    try {
        PageRank pageRank("../connectivity.txt");
        std::cout << pageRank << std::endl;
    }catch (const std::exception & e){
        std::cout << e.what() << std::endl;
    }

    return 0;
}
