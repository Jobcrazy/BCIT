//
// Created by Hang Liu on 2021/9/27.
//

#include <fstream>
#include "Connectivity.hpp"

Connectivity::Connectivity(const std::string &filePath) {
    std::ifstream file(filePath);
    if (!file) {
        throw std::runtime_error("Cannot find file:" + filePath);
    }

    
}
