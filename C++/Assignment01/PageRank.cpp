//
// Created by Win7x64 on 2021/10/2.
//

#include "PageRank.hpp"

PageRank::PageRank(const std::string &filePath) : Connectivity(filePath) {
    updateImportance();
    updateTransition();
}

void PageRank::updateImportance() {
    for (int column = 0; column < m_columnSize; ++column) {
        double sum = 0;
        for (int row = 0; row < m_rowSize; ++row) {
            sum += m_matrix[row][column];
        }

        double importance = sum != 0 ? 1.0 / sum : 1.0 / m_rowSize;
        for (int row = 0; row < m_rowSize; ++row) {
            if (0 != m_matrix[row][column] && 0 != sum) {
                m_matrix[row][column] = importance;
            } else if (0 == sum) {
                m_matrix[row][column] = importance;
            }
        }
    }
}

void PageRank::updateTransition() {
    constexpr double follow{0.85};
    constexpr double notFollow{0.15};

    Matrix teleportation(m_rowSize);
    for (int row = 0; row < m_rowSize; ++row) {
        for (int column = 0; column < m_columnSize; ++column) {
            teleportation.setValue(row, column, 1.0 / m_rowSize);
        }
    }

    *this =  (*this) * follow + teleportation * notFollow;
}

void PageRank::operator=(const Matrix &m) {
    for (int row = 0; row < m_rowSize; ++row) {
        for (int column = 0; column < m_columnSize; ++column) {
            m_matrix[row][column] = m.getValue(row, column);
        }
    }
}
