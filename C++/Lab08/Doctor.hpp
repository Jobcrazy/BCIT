//
// Created by Win7x64 on 2021/11/10.
//

#pragma once

#include <string>

class Doctor {
private:
    std::string name;
public:
    // The constructor
    // PRE: name is the doctor name
    explicit Doctor(std::string name);

    // Get a patient from the hospital and treat
    // POST: the patient is treated
    void treat();
};
