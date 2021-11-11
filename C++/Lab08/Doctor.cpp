//
// Created by Win7x64 on 2021/11/10.
//

#include "Doctor.hpp"
#include "Hospital.hpp"

#include <utility>

Doctor::Doctor(std::string name) : name(std::move(name)) {

}

void Doctor::treat() {
    Patient patient = Hospital::getInstance().get_patient();
    std::cout << name << " is treating: " << patient << std::endl;
}
