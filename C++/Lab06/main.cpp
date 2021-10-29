//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include "Dictionary.hpp"
#include "Application.hpp"

#ifndef _NDEBUG
#include "UnitTests.hpp"
#endif

int main() {
#ifndef _NDEBUG
    UnitTests::unitTests();
#endif

    try {
        Dictionary dict{"../dictionary.txt"};
        Application app{dict};

        app.run();
    }
    catch (const std::exception &e) {
        std::cout << e.what() << std::endl;
        exit(1);
    }

    return 0;
}
