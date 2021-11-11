//
// Created by Win7x64 on 2021/11/10.
//

#include "Hospital.hpp"

void Hospital::add_patient(const Patient &patient) {
    heapPatients.push(patient);
}

bool Hospital::has_patient() const {
    return !heapPatients.is_empty();
}

Patient Hospital::get_patient() {
    return heapPatients.pop();
}

void Hospital::print_patients() const {
    heapPatients.print_all();
    std::cout << std::endl;
}
