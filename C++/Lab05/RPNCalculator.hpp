//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include <stack>
#include <string>

class Operation;

class RPNCalculator {
private:
    int result{0};
    std::stack<int> operands;

public:
    // Perform the formula and return the result
    // PRE: formula is a string
    // POST: the operation result is stored in the member variable
    // RETURN: the operation result
    int process_formula(const std::string &formula);

private:
    // Get the operation instance for the given operation
    // PRE: operation is a character represents a kind of operations
    // POST: all member variables are unchanged
    // RETURN: an Operation instance for the given operation
    static Operation *operation_type(char operation);

    // The virtual destructor
    void perform(Operation *operation);
};

