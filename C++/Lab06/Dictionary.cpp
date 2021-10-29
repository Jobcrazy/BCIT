//
// Created by Win7x64 on 2021/10/26.
//

#include "Dictionary.hpp"
#include <iostream>
#include <fstream>

Dictionary::Dictionary(const std::string &filePath) : dataFilePath(filePath) {
    std::ifstream fDict{filePath};
    if (!fDict) {
        throw std::runtime_error("Cannot open file!");
    }

    std::string strWord, strDefinition;
    while (std::getline(fDict, strWord)) {
        if (getline(fDict, strDefinition)) {
            data.insert(std::make_pair(strWord, strDefinition));
        }
    }

    fDict.close();
}

Dictionary::~Dictionary() {
    std::ofstream fData{dataFilePath, std::ios_base::trunc};
    if (!fData) {
        std::cout << "Cannot open file to save!" << std::endl;
        return;
    }

    for (const auto &it: data) {
        fData << it.first << std::endl;
        fData << it.second << std::endl;
    }

    data.clear();
}

std::map<std::string, std::string> Dictionary::getAllWords() const {
    return data;
}

std::string Dictionary::findWord(const std::string &strWord) const {
    const auto &it = data.find(strWord);
    if (data.end() == it) {
        return "";
    }

    return it->second;
}

bool Dictionary::addWord(const std::string &strWord,
                         const std::string &strDefinition) {
    const auto &it = data.find(strWord);
    if (data.end() != it) {
        return false;
    }

    data.insert(std::make_pair(strWord, strDefinition));
    return true;
}

void Dictionary::removeWord(const std::string &strWord) {
    auto it = data.find(strWord);
    if (it != data.end()){
        data.erase(it);
    }
}