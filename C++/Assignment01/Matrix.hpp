//
// Created by Hang Liu on 2021/9/25.
//

#pragma once

#include <iostream>
#include <vector>

class Matrix {
private:
    std::vector<std::vector<double>> m_matrix;
    int rowSize{0};
    int columnSize{0};

public:
    Matrix();

    explicit Matrix(int matrixSize);

    Matrix(int rowSize, int columnSize);

    explicit Matrix(std::vector<double> &vData);

    Matrix(const Matrix &m);

    virtual ~Matrix();

    void clear();

    void setValue(int rowIndex, int columnIndex, double value);

    [[nodiscard]] double getValue(int rowIndex, int columnIndex) const;

    friend std::ostream &operator<<(std::ostream &, const Matrix &);

    bool operator==(const Matrix &m) const;

    bool operator!=(const Matrix &m) const;

    Matrix &operator++();

    Matrix operator++(int);

    Matrix &operator--();

    Matrix operator--(int);

    Matrix &operator=(const Matrix &m);

    Matrix operator+(const Matrix &m);

    Matrix &operator+=(const Matrix &m);

    Matrix operator-(const Matrix &m);

    Matrix &operator-=(const Matrix &m);

    Matrix operator*(const Matrix &m);

    Matrix &operator*=(const Matrix &m);

private:
    void initMatrix(int r, int c);

    void add(double k);

    void matrixAdd(const Matrix &m, bool bAdd);

    void multiply(const Matrix &m);
};
