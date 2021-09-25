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

    virtual ~Matrix();

    void clear();

    void setValue(int rowIndex, int columnIndex, double value);

    double getValue(int rowIndex, int columnIndex) const;

    /*double operator <<() const;*/

    bool operator==(const Matrix &m) const;

    bool operator!=(const Matrix &m) const;

private:
    void initMatrix(int r, int c);
};
