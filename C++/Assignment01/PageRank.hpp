//
// Created by Win7x64 on 2021/10/2.
//

#pragma once

#include "Connectivity.hpp"

class PageRank : public Connectivity {
public:
    // Constructor
    // PRE: filePath is the path of the connectivity matrix file
    // POST: the matrix data has been loaded into m_matrix
    explicit PageRank(const std::string &filePath);

    // Copy data from another Matrix instance
    // PRE: m is another Matrix instance
    // POST: all data is copied from m
    PageRank &operator=(const Matrix &m);

    // Overload operator << for output
    // PRE: out is the output stream
    // PRE: m is a Matrix instance
    // POST: the Matrix instance m is unchanged and printed
    // RETURN: the output stream
    friend std::ostream &operator<<(std::ostream &out, const PageRank &pr);

private:
    // Update Importance Matrix
    // POST: m_matrix has been update to importance matrix
    void updateImportance();

    // Update Transition Matrix
    // POST: m_matrix has been update to Transition matrix
    void updateTransition();

    // Update Markov Matrix
    // POST: m_matrix has been update to Markov matrix
    void updateMarkov();

    // Update PageRank Matrix
    // POST: m_matrix has been update to PageRank matrix
    void updatePageRank();
};