//
// Created by Hang Liu on 2021/9/25.
//

#include <cmath>
#include "Matrix.hpp"

Matrix::Matrix() {
    initMatrix(1, 1);
}

Matrix::Matrix(int matrixSize) {
    initMatrix(matrixSize, matrixSize);
}

Matrix::Matrix(int rowSize, int columnSize) {
    initMatrix(rowSize, columnSize);
}

Matrix::Matrix(std::vector<double> &vData) {
    size_t vSize = vData.size();

    // Check if the vector size can be squared or not
    auto floatSize = static_cast<float >(vSize);
    auto root = sqrt(floatSize);
    if( root * root != floatSize ){
        throw std::runtime_error("Vector vSize cannot be squared");
    }

    // Init the matrix
    auto matrixSize = static_cast<int>(root);
    initMatrix(matrixSize, matrixSize);

    // TODO: Fill out the data
}

void Matrix::initMatrix(int rowSize, int columnSize) {
    if (0 >= rowSize || 0 >= columnSize) {
        throw std::range_error("Row and column cannot be less than 1");
    }

    // Initialize the m_matrix
    for (int rowIndex = 0; rowIndex < rowSize; ++rowIndex) {
        std::vector<double> row;
        for (int columnIndex = 0; columnIndex < columnSize; ++columnIndex) {
            row.push_back(0);
        }
        m_matrix.push_back(row);
    }
}

void Matrix::clear() {
    for (auto &row: m_matrix) {
        row.clear();
    }
    m_matrix.clear();
}

Matrix::~Matrix() {
    clear();
}
