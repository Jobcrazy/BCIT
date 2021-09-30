//
// Created by Hang Liu on 2021/9/27.
//

#pragma once

#include <string>
#include "Matrix.hpp"

class Connectivity : public Matrix {
public:
    Connectivity(const std::string &filePath);
};