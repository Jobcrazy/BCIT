//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include "AbstractOperation.hpp"

class DivisionOperation : public AbstractOperation {
public:
    static constexpr char divisionCode{'/'};

    // The default constructor
    DivisionOperation() : AbstractOperation(divisionCode) {};

    // Perform the division operation
    // PRE: lhs is the left hand side operand
    // PRE: rhs is the right hand side operand
    // POST: the division operation has been performed
    // RETURN: the result for the division operation
    int perform(int lhs, int rhs) override {
        return lhs / rhs;
    }

    // The virtual destructor
    ~DivisionOperation() override = default;
};
