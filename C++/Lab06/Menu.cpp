//
// Created by Win7x64 on 2021/10/26.
//

#include <iostream>
#include <limits>
#include "Menu.hpp"
#include "Dictionary.hpp"

Menu::Menu(Dictionary &dictionary) : dict(dictionary) {
    const std::string MENU_ITEM_PRINT{"1 - Print dictionary"};
    const std::string MENU_ITEM_FIND{"2 - Find word definition"};
    const std::string MENU_ITEM_ADD{"3 - Enter new word and definition"};
    const std::string MENU_ITEM_EXIT{"4 - Exit"};

    menuItems.push_back(MENU_ITEM_PRINT);
    menuItems.push_back(MENU_ITEM_FIND);
    menuItems.push_back(MENU_ITEM_ADD);
    menuItems.push_back(MENU_ITEM_EXIT);
}

void Menu::run() {
    int choice = 0;
    while (choice < (int) menuItems.size()) {
        printMenu();

        std::cin >> choice;
        resetCin();
        processChoice(choice);
    }
}

void Menu::printMenu() const {
    for (const auto &item: menuItems) {
        std::cout << item << std::endl;
    }

    std::cout << "Please enter you choice:";
}

void Menu::processChoice(int &choice) {
    switch (choice) {
        case PRINT:
            printWords();
            return;
        case ADD:
            addWord();
            return;
        case FIND:
            findWord();
            return;
        case EXIT:
            choice++;
            return;
        default:
            choice = 0;
            std::cout << "Wrong choice, choose again." << std::endl;
            return;
    }
}

void Menu::findWord() const {
    std::string word;

    std::cout << "Please input a word:";
    std::cin >> word;

    std::string result = dict.findWord(word);
    if (result.length()) {
        std::cout << word << ": " << dict.findWord(word) << std::endl;
    } else {
        std::cout << "The word doesn't exist" << std::endl;
    }
}

void Menu::addWord() {
    bool added = false;

    while (!added) {
        std::string word, definition;
        std::cout << "Please input a word:";
        std::cin >> word;
        resetCin();

        if(!dict.findWord(word).empty()){
            std::cout << "Word Exists, Enter a new word please." << std::endl;
            continue;
        }

        std::cout << "Please input the definition:";
        std::getline(std::cin, definition);

        added = dict.addWord(word, definition);
        std::cout << "Word Added." << std::endl;
    }
}

void Menu::resetCin() {
    std::cin.sync();
    std::cin.clear();

    constexpr char END_OF_LINE{'\n'};
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(),
                    END_OF_LINE);
}

void Menu::printWords() const {
    std::map<std::string, std::string> results = dict.getAllWords();

    for (const auto &it: results) {
        std::cout << it.first << ": " << it.second << std::endl;
    }
}
