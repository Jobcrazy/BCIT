//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include "AbstractOperation.hpp"

class AdditionOperation : public AbstractOperation {
public:
    static constexpr char additionCode{'+'};

    // The default constructor
    AdditionOperation() : AbstractOperation(additionCode) {}

    // Perform the addition operation
    // PRE: lhs is the left hand side operand
    // PRE: rhs is the right hand side operand
    // POST: the addition operation has been performed
    // RETURN: the result for the addition operation
    int perform(int lhs, int rhs) override {
        return lhs + rhs;
    }

    // The virtual destructor
    ~AdditionOperation() override = default;
};
