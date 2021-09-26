//
// Created by Hang Liu on 2021/9/25.
//

#include <cmath>
#include <algorithm>
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

Matrix::Matrix(const Matrix &m) {
    operator=(m);
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

void Matrix::add(double value = 1) {
    for (int rowIndex = 0; rowIndex < rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < columnSize; ++columnIndex) {
            m_matrix[rowIndex][columnIndex] += value;
        }
    }
}

Matrix &Matrix::operator++() {
    add();
    return *this;
}

Matrix Matrix::operator++(int) {
    Matrix tmpMatrix(*this);
    ++*this;
    return tmpMatrix;
}


Matrix &Matrix::operator--() {
    add(-1);
    return *this;
}

Matrix Matrix::operator--(int) {
    Matrix tmpMatrix(*this);
    --*this;
    return tmpMatrix;
}

Matrix &Matrix::operator=(const Matrix &m) {
    clear();

    rowSize = m.rowSize;
    columnSize = m.columnSize;

    for (int rowIndex = 0; rowIndex < m.rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < m.columnSize; ++columnIndex) {
            m_matrix[rowIndex][columnIndex] = m.m_matrix[rowIndex][columnIndex];
        }
    }

    return *this;
}

std::ostream &operator<<(std::ostream &cout, const Matrix &m) {
    for (int rowIndex = 0; rowIndex < m.rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < m.columnSize; ++columnIndex) {
            cout << m.m_matrix[rowIndex][columnIndex] << "\t";
        }
        cout << std::endl;
    }
    return cout;
}

void Matrix::matrixAdd(const Matrix &m, bool bAdd) {
    // Check the size
    if (rowSize != m.rowSize || columnSize != m.columnSize) {
        throw std::runtime_error("Different matrix size");
    }

    // Perform addition
    for (int rowIndex = 0; rowIndex < m.rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < m.columnSize; ++columnIndex) {
            m_matrix[rowIndex][columnIndex] +=
                    bAdd ? m.m_matrix[rowIndex][columnIndex] :
                    0 - m.m_matrix[rowIndex][columnIndex];
        }
    }
}

Matrix Matrix::operator+(const Matrix &m) {
    Matrix tmp(*this);
    tmp.matrixAdd(m, true);
    return tmp;
}

Matrix &Matrix::operator+=(const Matrix &m) {
    matrixAdd(m, true);
    return *this;
}

Matrix Matrix::operator-(const Matrix &m) {
    Matrix tmp(*this);
    tmp.matrixAdd(m, false);
    return tmp;
}

Matrix &Matrix::operator-=(const Matrix &m) {
    matrixAdd(m, false);
    return *this;
}

Matrix Matrix::multiply(const Matrix &m) const {
    // Check the size
    if (columnSize != m.rowSize) {
        throw std::runtime_error("size mismatched");
    }

    // Perform matrix multiplication
    Matrix result(rowSize, m.columnSize);
    int rowIndex{0}, colIndex{0}, mColIndex{0};
    double sum{0};
    while (rowIndex < rowSize) {
        sum += m_matrix[rowIndex][colIndex] * m.m_matrix[colIndex][mColIndex];

        ++colIndex;
        if (colIndex >= columnSize) {
            result.setValue(rowIndex, mColIndex, sum);
            ++mColIndex;
            sum = 0;
            colIndex = 0;
            if (mColIndex >= m.columnSize) {
                ++rowIndex;
                mColIndex = 0;
            }
        }
    }

    return result;
}

Matrix Matrix::operator*(const Matrix &m) {
    return multiply(m);
}

Matrix &Matrix::operator*=(const Matrix &m) {
    *this = multiply(m);
    return *this;
}


