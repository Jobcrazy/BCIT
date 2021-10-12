//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

class Operation {
public:
    // Get the operation code
    // POST: All members are unchanged
    // RETURN: a char represents the specified operation
    virtual char getCode() const = 0;

    // Perform the specified operation
    // PRE: lhs is the left hand side operand
    // PRE: rhs is the right hand side operand
    // POST: Specified operation has been performed
    // RETURN: the result for the specified operation
    virtual int perform(int lhs, int rhs) = 0;

    // The virtual destructor
    virtual ~Operation() = default;
};
