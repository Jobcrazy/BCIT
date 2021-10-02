//
// Created by Hang Liu on 2021/9/27.
//

#pragma once

#include <string>
#include "Matrix.hpp"

class Connectivity : public Matrix {
public:
    // Constructor
    // PRE: filePath is the path of the connectivity matrix file
    // POST: the matrix data has been loaded into m_matrix
    explicit Connectivity(const std::string &filePath);

private:
    // Load matrix data from the connectivity matrix file
    // PRE: file is the istream of the connectivity matrix file
    // POST: the matrix data has been loaded into m_matrix
    void loadMatrixFromFile(std::ifstream &file);

    // Load double typed vector from the given string
    // PRE: line is a string containing double typed numbers
    // PRE: row is a vector that the number will be loaded into
    // POST: the number in line is loaded in to a vector
    static void lineToVector(const std::string &line, std::vector<double> &row);
};