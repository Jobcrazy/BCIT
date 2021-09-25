//
// Created by Hang Liu on 2021/9/25.
//

#pragma once

#include <iostream>
#include <vector>

class Matrix {
private:
    std::vector<std::vector<double>> m_matrix;

public:
    Matrix();

    explicit Matrix(int matrixSize);

    Matrix(int rowSize, int columnSize);

    explicit Matrix(std::vector<double> &vData);

    virtual ~Matrix();

    void clear();

private:
    void initMatrix(int rowSize, int columnSize);
};
