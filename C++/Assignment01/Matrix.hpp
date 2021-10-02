//
// Created by Hang Liu on 2021/9/25.
//

#pragma once

#include <iostream>
#include <vector>

class Matrix {
protected:
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

    // The copy constructor
    // PRE: m is another Matrix instance
    // POST: all data from the other Matrix instance has been copied
    Matrix(const Matrix &m);

    // The deconstructor
    // POST: Clean up in m_matrix
    virtual ~Matrix();

    // Delete all data in m_matrix
    // POST: All data in m_matrix has been deleted
    void clear();

    // Set value for the given position
    // PRE: rowIndex is the row index of the value
    // PRE: columnIndex is the column index of the value
    // PRE: value is the new value for the position
    // POST: the value of given position has been updated
    void setValue(int rowIndex, int columnIndex, double value);

    // Get value from the given position
    // PRE: rowIndex is the row index of the value
    // PRE: columnIndex is the column index of the value
    // POST: matrix is unchanged
    // RETURN: the value of the given position
    [[nodiscard]] double getValue(int rowIndex, int columnIndex) const;

    // Add 1 to each element in the matrix (PREFIX)
    // POST: each element in the matrix has increased by 1
    Matrix &operator++();

    // Add 1 to each element in the matrix (POSTFIX)
    // POST: each element in the matrix has increased by 1
    Matrix operator++(int);

    // Decrease 1 from each element in the matrix (PREFIX)
    // POST: each element in the matrix has decreased by 1
    Matrix &operator--();

    // Decrease 1 from each element in the matrix (POSTFIX)
    // POST: each element in the matrix has decreased by 1
    Matrix operator--(int);

    // Copy data from another Matrix instance
    // PRE: other is another Matrix instance
    // POST: all data is copied from "other"
    Matrix &operator=(Matrix other);

    // Add two Matrix and store the result
    // PRE: other is another Matrix instance
    // POST: the addition result is stored in the current instance
    // RETURN: the current Matrix instance
    Matrix &operator+=(const Matrix &m);

    // Add two Matrix and return the result
    // PRE: other is another Matrix instance
    // POST: both matrix instances are unchanged
    // RETURN: a new Matrix instance that stores the addition result
    friend Matrix operator+(Matrix lhs, const Matrix &rhs);

    // Subtract current instance by another instance and store the result
    // PRE: m is another Matrix instance
    // POST: the subtraction result is stored in the current instance
    // RETURN: the current Matrix instance
    Matrix &operator-=(const Matrix &m);

    // Subtract current instance by another instance and return the result
    // PRE: m is another Matrix instance
    // POST: both matrix instances are unchanged
    // RETURN:  a new Matrix instance that stores the subtraction result
    friend Matrix operator-(Matrix lhs, const Matrix &rhs);

    // Multiple two Matrix and store the result
    // PRE: other is another Matrix instance
    // POST: the multiplication result is stored in the current instance
    // RETURN: the current instance
    Matrix &operator*=(const Matrix &m);

    // Multiple two Matrix and return the result
    // PRE: other is another Matrix instance
    // POST: both matrix instances are unchanged
    // RETURN: a new Matrix instance that stores the multiplication result
    friend Matrix operator*(Matrix lhs, const Matrix &rhs);

    // Check two Matrix instances to see if they are the same
    // PRE: lhs is one Matrix instance
    // PRE: rhs is another Matrix instance
    // POST: both matrix instances are unchanged
    // RETURN: true if the two instance are the same
    friend bool operator==(const Matrix &lhs, const Matrix &rhs);

    // Check two Matrix instances to see if they are not the same
    // PRE: lhs is one Matrix instance
    // PRE: rhs is another Matrix instance
    // POST: both matrix instances are unchanged
    // RETURN: true if the two instance are not the same
    friend bool operator!=(const Matrix &lhs, const Matrix &rhs);

    // Overload operator << for output
    // PRE: out is the output stream
    // PRE: m is a Matrix instance
    // POST: the Matrix instance m is unchanged and printed
    // RETURN: the output stream
    friend std::ostream &operator<<(std::ostream &out, const Matrix &m);

private:
    // Initialize a matrix in m_matrix
    // PRE: r is a positive integer and r > 0
    // PRE: c is a positive integer and c > 0
    // POST: r * c sized matrix has been initialized
    void initMatrix(int r, int c);

    // Add k to each element in m_matrix
    // PRE: k is an integer
    // POST: k is added to each element in m_matrix
    void add(double k);

    // Add/Subtract the current Matrix by another Matrix
    // PRE: m is another Matrix instance
    // POST: current Matrix instance has been updated
    void matrixAdd(const Matrix &m, bool bAdd);

    // Multiply the current Matrix by another Matrix
    // PRE: m is another Matrix instance
    // POST: current Matrix instance has been updated
    [[nodiscard]] Matrix multiply(const Matrix &m) const;

    // Swap the data between the current instance and another one
    // PRE: other is another Matrix instance
    // POST: the data of the current and another instances has been exchanged
    void swap(Matrix &other);
};
