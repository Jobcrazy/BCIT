//
// Created by Hang Liu on 2021/9/25.
//

#pragma once

#include <iostream>
#include <vector>

class Matrix {
private:
    std::vector<std::vector<double>> m_matrix;
    int m_rowSize{0};
    int m_columnSize{0};

public:
    // Default Constructor
    // POST: 1x1 sized Matrix has been initialized
    Matrix();

    // Constructor: Initialize a squared matrix in m_matrix
    // PRE: matrixSize is a positive integer and matrixSize > 0
    // POST: matrixSize * matrixSize sized matrix has been initialized
    explicit Matrix(int matrixSize);

    // Constructor: Initialize a rectangle matrix in m_matrix
    // PRE: m_rowSize is a positive integer and m_rowSize > 0
    // PRE: m_columnSize is a positive integer and m_columnSize > 0
    // POST: m_rowSize * m_columnSize sized matrix has been initialized
    Matrix(int rowSize, int columnSize);

    // Constructor: Initialize a squared matrix in m_matrix
    // PRE: vData is a vector and squr(vData.size()) is an integer
    // POST: vData has been converted to a squared matrix
    explicit Matrix(std::vector<double> &vData);

    Matrix(const Matrix &m);

    virtual ~Matrix();

    void clear();

    void setValue(int rowIndex, int columnIndex, double value);

    [[nodiscard]] double getValue(int rowIndex, int columnIndex) const;

    Matrix &operator++();

    Matrix operator++(int);

    Matrix &operator--();

    Matrix operator--(int);

    Matrix &operator=(Matrix other);

    Matrix &operator+=(const Matrix &m);

    friend Matrix operator+(Matrix lhs, const Matrix &rhs);

    Matrix &operator-=(const Matrix &m);

    friend Matrix operator-(Matrix lhs, const Matrix &rhs);

    Matrix &operator*=(const Matrix &m);

    friend Matrix operator*(Matrix lhs, const Matrix &rhs);

    friend bool operator==(const Matrix &lhs, const Matrix &rhs);

    friend bool operator!=(const Matrix &lhs, const Matrix &rhs);

    friend std::ostream &operator<<(std::ostream &, const Matrix &);

private:
    // Initialize a matrix in m_matrix
    // PRE: r is a positive integer and r > 0
    // PRE: c is a positive integer and c > 0
    // POST: r * c sized matrix has been initialized
    void initMatrix(int r, int c);

    void add(double k);

    void matrixAdd(const Matrix &m, bool bAdd);

    Matrix multiply(const Matrix &m) const;

    void swap(Matrix &other);
};
