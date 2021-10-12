//
// Created by Win7x64 on 2021/10/12.
//

#include <sstream>
#include "RPNCalculator.hpp"
#include "Operation.hpp"
#include "AdditionOperation.hpp"
#include "SubtractionOperation.hpp"
#include "MultiplicationOperation.hpp"
#include "DivisionOperation.hpp"

Operation *RPNCalculator::operation_type(char operation) {
    switch (operation) {
        case AdditionOperation::additionCode:
            return new AdditionOperation();
        case SubtractionOperation::subtractionCode:
            return new SubtractionOperation();
        case MultiplicationOperation::multiplicationCode:
            return new MultiplicationOperation();
        case DivisionOperation::divisionCode:
            return new DivisionOperation();
        default:
            return nullptr;
    }
}

void RPNCalculator::perform(Operation *operation) {
    if (operands.size() <= 1) {
        return;
    }

    int rhs{operands.top()};
    operands.pop();
    int lhs{operands.top()};
    operands.pop();

    operands.push(operation->perform(lhs, rhs));
}

int RPNCalculator::process_formula(const std::string &formula) {
    std::istringstream iss{formula};
    std::string operand;

    while (iss >> operand) {
        int number{0};
        std::istringstream issInner{operand};

        if (issInner >> number) {
            operands.push(number);
        } else {
            Operation * operation = operation_type(operand[0]);
            perform(operation);
            delete operation;
        }
    }

    result = operands.top();
    operands.pop();
    return result;
}


