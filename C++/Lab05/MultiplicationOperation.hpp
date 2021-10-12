//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include "AbstractOperation.hpp"

class MultiplicationOperation : public AbstractOperation {
public:
    static constexpr char multiplicationCode{'*'};

    // The default constructor
    MultiplicationOperation() : AbstractOperation(multiplicationCode) {};

    // Perform the multiplication operation
    // PRE: lhs is the left hand side operand
    // PRE: rhs is the right hand side operand
    // POST: the multiplication operation has been performed
    // RETURN: the result for the multiplication operation
    int perform(int lhs, int rhs) override {
        return lhs * rhs;
    }

    // The virtual destructor
    ~MultiplicationOperation() override = default;
};