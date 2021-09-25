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
    if (root * root != floatSize) {
        throw std::runtime_error("Vector vSize cannot be squared");
    }

    // Init the matrix
    auto matrixSize = static_cast<int>(root);
    initMatrix(matrixSize, matrixSize);

    // Fill the matrix with data
    size_t index = 0;
    while (index < vSize) {
        for (int rowIndex = 0; rowIndex < matrixSize; ++rowIndex) {
            for (int columnIndex = 0; columnIndex < matrixSize; ++columnIndex) {
                m_matrix[rowIndex][columnIndex] = vData[index];
                ++index;
            }
        }
    }
}

void Matrix::initMatrix(int r, int c) {
    if (0 >= r || 0 >= c) {
        throw std::range_error("Parameters out of range");
    }

    // Initialize the m_matrix
    this->rowSize = r;
    this->columnSize = c;
    for (int rowIndex = 0; rowIndex < r; ++rowIndex) {
        std::vector<double> row;
        for (int columnIndex = 0; columnIndex < c; ++columnIndex) {
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

void Matrix::setValue(int rowIndex, int columnIndex, double value) {
    // Check the range
    if (0 > rowIndex || 0 > columnIndex ||
        rowIndex >= rowSize || columnIndex >= columnSize) {
        throw std::range_error("Parameters out of range");
    }

    // Set the value
    m_matrix[rowIndex][columnIndex] = value;
}

double Matrix::getValue(int rowIndex, int columnIndex) const {
    // Check the range
    if (0 > rowIndex || 0 > columnIndex ||
        rowIndex >= rowSize || columnIndex >= columnSize) {
        throw std::range_error("Parameters out of range");
    }

    // Get the value
    return m_matrix[rowIndex][columnIndex];
}

bool Matrix::operator==(const Matrix &m) const {
    // Check the size
    if (rowSize != m.rowSize || columnSize != m.columnSize) {
        return false;
    }

    // Compare the elements one by one
    for (int rowIndex = 0; rowIndex < rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < columnSize; ++columnIndex) {
            if (m_matrix[rowIndex][columnIndex] !=
                m.m_matrix[rowIndex][columnIndex]) {
                return false;
            }
        }
    }

    return true;
}

bool Matrix::operator!=(const Matrix &m) const {
    return !(*this == m);
}


