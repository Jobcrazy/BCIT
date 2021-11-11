//
// Created by Win7x64 on 2021/11/10.
//

#pragma once

#include <iostream>
#include <string>

class Patient {
private:
    std::string name;
    int priority;

public:
    // The Constructor
    // PRE: name is the patient name
    // PRE: priority is the priority of the patient
    Patient(const std::string &name, int priority);

    // Get the patient name
    // POST: nothing has been changed
    // RETURN: the patient name
    std::string getName() const { return name; }

    // Get the patient's priority
    // POST: nothing has been changed
    // RETURN: the patient's priority
    int getPriority() const { return priority; }

    // Overload the < operator
    // PRE: lhs is an instance of a patient
    // PRE: rhs is another instance of a patient
    // RETURN: true if lhs is less than rhs, otherwise false
    friend bool operator<(const Patient& lhs, const Patient& rhs);

    // Overload the << operator
    // PRE: os is the output stream
    // PRE: patient is an Patient instance
    // RETURN: the output stream
    friend std::ostream &operator<<(std::ostream &os, const Patient &patient);
};
