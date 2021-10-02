//
// Created by Hang Liu on 2021/9/27.
//

#include <fstream>
#include <sstream>
#include "Connectivity.hpp"

Connectivity::Connectivity(const std::string &filePath) {
    std::ifstream file(filePath);
    if (!file) {
        throw std::runtime_error("Cannot find file:" + filePath);
    }
    loadMatrixFromFile(file);
    file.close();
}

void Connectivity::loadMatrixFromFile(std::ifstream &file) {
    // Clear default matrix first
    clear();

    // Load matrix from multiline with a loop
    std::string line;
    while (std::getline(file, line)) {
        // Load a vector from a line
        std::vector<double> row;
        lineToVector(line, row);

        // Check column size
        int columnSize = static_cast<int>(row.size());
        if (0 == m_columnSize) {
            m_columnSize = columnSize;
        } else if (columnSize != m_columnSize) {
            throw std::runtime_error("Column size are not the same");
        }

        // Update matrix
        m_matrix.push_back(row);
    }

    // Check row size
    m_rowSize = static_cast<int>(m_matrix.size());
    if (m_rowSize != m_columnSize) {
        throw std::runtime_error("Matrix is not squared");
    }
}

void Connectivity::lineToVector(
        const std::string &line,
        std::vector<double> &row) {
    std::istringstream oss(line);
    double number{0};

    while (oss >> number) {
        row.push_back(number);
    }
}
