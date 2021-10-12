//
// Created by Win7x64 on 2021/10/12.
//

#pragma once

#include "Operation.hpp"

class AbstractOperation : public Operation {
private:
    char operationType;

public:
    // The default constructor
    explicit AbstractOperation(char type) {
        operationType = type;
    }

    [[nodiscard]] char getCode() const override {
        return operationType;
    }

    // The virtual destructor
    ~AbstractOperation() override = default;
};