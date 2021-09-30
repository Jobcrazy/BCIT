//
// Created by Win7x64 on 2021/9/29.
//

#include <iostream>
#include <cmath>
#include "Canine.hpp"

Canine::Canine() : Animal() {
    std::cout << "Canine(" << getId() << ") default constructor" << std::endl;
}

Canine::Canine(int iAge, double dLatitude, double dLongitude) :
        Animal(iAge, dLatitude, dLongitude) {
    std::cout << "Canine(" << getId() << ") 3-parameters constructor" << std::endl;
}

void Canine::move(double dLatitude, double dLongitude) {
    std::cout << "Canine(" << getId() << ") move" << std::endl;
    Animal::move(dLatitude, dLongitude);
}

Canine::Canine(const Canine &canine) : Animal(canine) {
    std::cout << "Canine(" << getId() << ") copy constructor" << std::endl;
}

Canine::~Canine() {
    std::cout << "Canine(" << getId() << ") deconstructed" << std::endl;
}

void Canine::sleep() const {
    std::cout << "Canine(" << getId() << ") sleep" << std::endl;
}

void Canine::eat() const {
    std::cout << "Canine(" << getId() << ") eat" << std::endl;
}

void Canine::hunt(Animal &animal) {
    std::cout << "Canine(" << getId() << ") hunt animal" << std::endl;

    if (fabs(getHeight() - animal.getHeight()) <= 1 &&
        fabs(getLatitude() - animal.getLatitude()) <= 1 &&
        fabs(getLongitude() - animal.getLongitude()) <= 1) {
        animal.setAlive(false);
    }
}

std::ostream &operator<<(std::ostream &out, const Canine &canine) {
    out << "Canine(" << canine.getId()
        << "): \tage:" << canine.getAge()
        << "\tlongitude: " << canine.getLongitude()
        << "\tlatitude: " << canine.getLatitude()
        << "\talive: " << std::boolalpha << canine.isAlive()
        << "\theight: " << canine.getHeight();
    return out;
}
