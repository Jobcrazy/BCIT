//
// Created by Win7x64 on 2021/10/26.
//

#include <iostream>
#include <cstring>
#include <cassert>
#include "Dictionary.hpp"
#include "UnitTests.hpp"

void UnitTests::unitTests() {
    // Open a file that doesn't exist
    try {
        Dictionary dict{"file_not_exist.txt"};
    }
    catch (const std::exception &e) {
        assert(!strcmp(e.what(), "Cannot open file!"));
    }

    try {
        // Open the dictionary
        Dictionary dict{"../dictionary.txt"};

        // Insert a word
        dict.addWord("B.C.", "A beautiful province in Canada");

        // Search a word
        std::string res = dict.findWord("B.C.");
        assert(res == "A beautiful province in Canada");

        // Search a word that doesn't exists
        std::string dummyRes = dict.findWord("dummy");
        assert(dummyRes.empty());

        // Insert a same word again
        assert(false == dict.addWord("B.C.", "A beautiful province"));

        // Insert a new word
        assert(true == dict.addWord("Hang", "A student name"));

        // Remove testing words
        dict.removeWord("Hang");
        dict.removeWord("B.C.");
    }
    catch (const std::exception &e) {
        std::cout << e.what() << std::endl;
        exit(1);
    }

    std::cout << "All tests passed" << std::endl;
}
