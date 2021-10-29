//
// Created by Win7x64 on 2021/10/26.
//

#include <vector>

class Dictionary;

class Menu {
private:
    enum {PRINT = 1, FIND, ADD, EXIT};
    Dictionary &dict;
    std::vector<std::string> menuItems;

public:
    // The constructor
    // PRE: dictionary is the Dictionary instance holding the data
    explicit Menu(Dictionary &dictionary);

    // Run a infinite loop until the user choose to exit
    void run();

    // Print a menu prompt for user choice
    void printMenu() const;

    // Process the user choice
    // PRE: choice is the user's choice
    // POST: the user's choice has been processed
    void processChoice(int &choice);

    // Print all words in the dictionary
    // POST: all words in the dictionary has been printed
    void printWords() const;

    // Prompt the user to input and search the input word in the dictionary
    // POST: show the result if the word is found, otherwise show an error msg
    void findWord() const;

    // Prompt the user to add a new word into the dictionary
    // POST: the new word and its definition had been added into the dictionary
    void addWord();

private:
    // Set std::cin state to good and clear the input buffer
    static void resetCin();
};