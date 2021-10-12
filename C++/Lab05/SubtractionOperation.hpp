//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include "AbstractOperation.hpp"

class SubtractionOperation : public AbstractOperation {
public:
    static constexpr char subtractionCode{'-'};

    // The default constructor
    SubtractionOperation() : AbstractOperation(subtractionCode) {}

    // Perform the subtraction operation
    // PRE: lhs is the left hand side operand
    // PRE: rhs is the right hand side operand
    // POST: the subtraction operation has been performed
    // RETURN: the result for the subtraction operation
    int perform(int lhs, int rhs) override {
        return lhs - rhs;
    }

    // The virtual destructor
    ~SubtractionOperation() override = default;
};

