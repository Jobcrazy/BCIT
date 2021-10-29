//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include "Dictionary.hpp"
#include "Menu.hpp"

int main() {
#ifndef _NDEBUG
    #include "UnitTests.hpp"
    UnitTests::unitTests();
#endif

    try {
        Dictionary dict{"../dictionary.txt"};
        Menu menu{dict};

        menu.run();
    }
    catch (const std::exception &e) {
        std::cout << e.what() << std::endl;
        exit(1);
    }

    return 0;
}
