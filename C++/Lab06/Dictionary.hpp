//
// Created by Win7x64 on 2021/10/26.
//

#pragma once

#include <map>
#include <string>

class Dictionary {
private:
    std::string dataFilePath;
    std::map<std::string, std::string> data;

public:
    // The constructor
    // PRE: filePath is the file holding the dictionary data
    // POST: the dictionary data has been read into member variable data
    explicit Dictionary(const std::string &filePath);

    // The default destructor
    // POST: the dictionary data has been written back into the data file
    ~Dictionary();

    // Return a copy of all words in the data
    // POST: nothing has been changed
    // RETURN: a map of all words and their definitions
    std::map<std::string, std::string> getAllWords() const;

    // Search and return the definition for given word
    // PRE: strWord is the word to search
    // POST: nothing has been changed
    // RETURN: the copy of the word definition
    std::string findWord(const std::string &strWord) const;

    // Add a word to the dictionary
    // PRE: strWord is the word
    // PRE: strDefinition is the definition
    // POST: strWord and strDefinition has been added into data as a pair
    // RETURN: true if the word is added, otherwise return false
    bool addWord(const std::string &strWord, const std::string &strDefinition);

    // Remove a word from the dictionary
    // PRE: strWord is the word
    // POST: strWord has been remove from the dictionary
    void removeWord(const std::string &strWord);
};