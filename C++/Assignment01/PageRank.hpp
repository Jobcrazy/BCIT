//
// Created by Win7x64 on 2021/10/2.
//

#pragma once

#include "Connectivity.hpp"

class PageRank : public Connectivity {
public:
    explicit PageRank(const std::string &filePath);

    // Overload operator << for output
    // PRE: out is the output stream
    // PRE: m is a Matrix instance
    // POST: the Matrix instance m is unchanged and printed
    // RETURN: the output stream
    friend std::ostream &operator<<(std::ostream &out, const Matrix &m);

private:
    void updateImportance();

    void updateTransition();

    void operator = (const Matrix &m);
};