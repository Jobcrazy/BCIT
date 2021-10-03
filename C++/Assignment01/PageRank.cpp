//
// Created by Win7x64 on 2021/10/2.
//

#include "PageRank.hpp"
#include <iomanip>

PageRank::PageRank(const std::string &filePath) : Connectivity(filePath) {
    updateImportance();
    updateTransition();
    updateMarkov();
    updatePageRank();
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

    *this = (*this) * follow + teleportation * notFollow;
}

PageRank &PageRank::operator=(const Matrix &m) {
    this->Matrix::operator=(m);
    return *this;
}

void PageRank::updateMarkov() {
    Matrix rank(m_columnSize, 1);
    for (int row = 0; row < m_columnSize; ++row) {
        rank.setValue(row, 0, 1.0);
    }

    Matrix result(m_columnSize, 1);
    while (result != rank) {
        result = rank;
        rank = *this * rank;
    }

    *this = result;
}

void PageRank::updatePageRank() {
    double sum = 0;
    for (int row = 0; row < m_rowSize; ++row) {
        sum += m_matrix[row][0];
    }

    for (int row = 0; row < m_rowSize; ++row) {
        m_matrix[row][0] = m_matrix[row][0] / sum;
    }
}

std::ostream &operator<<(std::ostream &out, const PageRank &pr) {
    constexpr int precision{2}, coefficient{100};
    for (size_t row = 0; row < pr.m_matrix.size(); ++row) {
        out << "Page " << (char) ('A' + row) << ": "
            << std::setprecision(precision) << std::fixed
            << pr.m_matrix[row][0] * coefficient << "%" << std::endl;
    }
    return out;
}

