//
// Created by Win7x64 on 2021/11/12.
//

#include <random>
#include "RandomNumber.hpp"


RandomNumber::RandomNumber() : pRd(nullptr), pGenerator(nullptr) {
    pRd = new std::random_device();
    pGenerator = new std::mt19937((*pRd)());
}

RandomNumber::~RandomNumber() {
    delete pGenerator;
    delete pRd;
}

double RandomNumber::getRandomDouble(float lowBound, float highBound) const {
    std::uniform_real_distribution<> distribution(lowBound, highBound);
    return distribution(*pGenerator);
}

int RandomNumber::getRandomInt(int lowBound, int highBound) const {
    std::uniform_int_distribution<> distribution(lowBound, highBound);
    return distribution(*pGenerator);
}
