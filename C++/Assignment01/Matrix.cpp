//
// Created by Hang Liu on 2021/9/25.
//

#include <cmath>
#include <algorithm>
#include "Matrix.hpp"
#include "PageRank.hpp"


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

Matrix::Matrix(const Matrix &other) {
    m_rowSize = other.m_rowSize;
    m_columnSize = other.m_columnSize;

    for (int rowIndex = 0; rowIndex < other.m_rowSize; ++rowIndex) {
        std::vector<double> row;
        for (int columnIndex = 0; columnIndex < other.m_columnSize; ++columnIndex) {
            row.push_back(other.m_matrix[rowIndex][columnIndex]);
        }
        m_matrix.push_back(row);
    }
}

void Matrix::initMatrix(int r, int c) {
    if (0 >= r || 0 >= c) {
        throw std::range_error("Parameters out of range");
    }

    // Initialize the m_matrix
    this->m_rowSize = r;
    this->m_columnSize = c;
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
    m_rowSize = m_columnSize = 0;
}

Matrix::~Matrix() {
    clear();
}

void Matrix::setValue(int rowIndex, int columnIndex, double value) {
    // Check the range
    if (0 > rowIndex || 0 > columnIndex ||
        rowIndex >= m_rowSize || columnIndex >= m_columnSize) {
        throw std::range_error("Parameters out of range");
    }

    // Set the value
    m_matrix[rowIndex][columnIndex] = value;
}

double Matrix::getValue(int rowIndex, int columnIndex) const {
    // Check the range
    if (0 > rowIndex || 0 > columnIndex ||
        rowIndex >= m_rowSize || columnIndex >= m_columnSize) {
        throw std::range_error("Parameters out of range");
    }

    // Get the value
    return m_matrix[rowIndex][columnIndex];
}

bool operator==(const Matrix &lhs, const Matrix &rhs) {
    // Check the size
    if (lhs.m_rowSize != rhs.m_rowSize ||
        lhs.m_columnSize != rhs.m_columnSize) {
        return false;
    }

    // Compare the elements one by one
    for (int rowIndex = 0; rowIndex < lhs.m_rowSize; ++rowIndex) {
        for (int columnIndex = 0;
             columnIndex < lhs.m_columnSize; ++columnIndex) {
            if (lhs.m_matrix[rowIndex][columnIndex] !=
                rhs.m_matrix[rowIndex][columnIndex]) {
                return false;
            }
        }
    }

    return true;
}

bool operator!=(const Matrix &lhs, const Matrix &rhs) {
    return !(lhs == rhs);
}

void Matrix::add(double value = 1) {
    for (int rowIndex = 0; rowIndex < m_rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < m_columnSize; ++columnIndex) {
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

void Matrix::swap(Matrix &current, Matrix &other) {
    std::swap(current.m_rowSize, other.m_rowSize);
    std::swap(current.m_columnSize, other.m_columnSize);
    std::swap(current.m_matrix, other.m_matrix);

    /*
    for (int rowIndex = 0; rowIndex < m_rowSize; ++rowIndex) {
        for (int columnIndex = 0;
             columnIndex < m_columnSize;
             ++columnIndex) {
            std::swap(m_matrix[rowIndex][columnIndex],
                      other.m_matrix[rowIndex][columnIndex]);
        }
    }*/
}

Matrix &Matrix::operator=(Matrix other) {
    // The swap() below is a member function of Matrix, not std::swap
    swap(*this, other);
    return *this;
}

std::ostream &operator<<(std::ostream &out, const Matrix &matrix) {
    for (int rowIndex = 0; rowIndex < matrix.m_rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < matrix.m_columnSize; ++columnIndex) {
            out << matrix.m_matrix[rowIndex][columnIndex] << "\t";
        }
        out << std::endl;
    }
    return out;
}

void Matrix::matrixAdd(const Matrix &m, bool bAdd) {
    // Check the size
    if (m_rowSize != m.m_rowSize || m_columnSize != m.m_columnSize) {
        throw std::runtime_error("Different matrix size");
    }

    // Perform addition
    for (int rowIndex = 0; rowIndex < m.m_rowSize; ++rowIndex) {
        for (int columnIndex = 0; columnIndex < m.m_columnSize; ++columnIndex) {
            m_matrix[rowIndex][columnIndex] +=
                    bAdd ? m.m_matrix[rowIndex][columnIndex] :
                    0 - m.m_matrix[rowIndex][columnIndex];
        }
    }
}

Matrix operator+(Matrix lhs, const Matrix &rhs) {
    lhs += rhs;
    return lhs;
}

Matrix &Matrix::operator+=(const Matrix &other) {
    matrixAdd(other, true);
    return *this;
}

Matrix operator-(Matrix lhs, const Matrix &rhs) {
    lhs -= rhs;
    return lhs;
}

Matrix &Matrix::operator-=(const Matrix &other) {
    matrixAdd(other, false);
    return *this;
}

Matrix Matrix::multiply(const Matrix &other) const {
    // Check the size
    if (m_columnSize != other.m_rowSize) {
        throw std::runtime_error("size mismatched");
    }

    // Perform matrix multiplication
    Matrix result(m_rowSize, other.m_columnSize);
    int rowIndex{0}, colIndex{0}, mColIndex{0};
    double sum{0};
    while (rowIndex < m_rowSize) {
        sum += m_matrix[rowIndex][colIndex] * other.m_matrix[colIndex][mColIndex];

        ++colIndex;
        if (colIndex >= m_columnSize) {
            result.setValue(rowIndex, mColIndex, sum);
            ++mColIndex;
            sum = 0;
            colIndex = 0;
            if (mColIndex >= other.m_columnSize) {
                ++rowIndex;
                mColIndex = 0;
            }
        }
    }

    return result;
}

Matrix operator*(Matrix lhs, const Matrix &rhs) {
    lhs *= rhs;
    return lhs;
}

Matrix &Matrix::operator*=(const Matrix &other) {
    *this = multiply(other);
    return *this;
}

Matrix operator*(Matrix lhs, double k) {
    for (int rowIndex = 0; rowIndex < lhs.m_rowSize; ++rowIndex) {
        for (int columnIndex = 0;
             columnIndex < lhs.m_columnSize; ++columnIndex) {
            lhs.m_matrix[rowIndex][columnIndex] *= k;
        }
    }

    return lhs;
}