//
// Created by Win7x64 on 2021/11/10.
//

#include "Patient.hpp"

Patient::Patient(const std::string &name, int priority)
        : name(name), priority(priority) {
}

std::ostream &operator<<(std::ostream &out, const Patient &patient) {
    out << "Patient Priority: " << patient.priority
       << " -- Patient Name:" << patient.name;
    return out;
}

bool operator<(const Patient &lhs, const Patient &rhs) {
    if (lhs.priority < rhs.priority){
        return true;
    }
    return false;
}
