//
// Created by Win7x64 on 2021/9/29.
//

#include <iostream>
#include "Bird.hpp"

Bird::Bird() : Animal(), height(0) {
    std::cout << "Bird(" << getId() << ") default constructor" << std::endl;
}

Bird::Bird(int iAge, double dLatitude, double dLongitude, double dHeight = 0) :
        Animal(iAge, dLatitude, dLongitude),
        height(dHeight) {
    std::cout << "Bird(" << getId() << ") 4 parameters constructor"
              << std::endl;
}

Bird::Bird(const Bird &bird) :
        Animal(bird),
        height(bird.height) {
    std::cout << "Bird(" << getId() << ") Copy constructor" << std::endl;
}

void Bird::move(double dLatitude, double dLongitude, double dHeight) {
    std::cout << "Bird(" << getId() << ") move" << std::endl;
    Animal::move(dLatitude, dLongitude);
    height = dHeight;
}

Bird::~Bird() {
    std::cout << "Bird(" << getId() << ") deconstructed" << std::endl;
}

void Bird::sleep() const {
    std::cout << "Bird(" << getId() << ") sleep" << std::endl;
}

void Bird::eat() const {
    std::cout << "Bird(" << getId() << ") eat" << std::endl;
}

std::ostream &operator<<(std::ostream &out, const Bird &bird) {
    out << "Bird(" << bird.getId()
            << "): \tage:" << bird.getAge()
            << "\tlongitude: " << bird.getLongitude()
            << "\tlatitude: " << bird.getLatitude()
            << "\talive: " << std::boolalpha << bird.isAlive()
            << "\theight: " << bird.getHeight();
    return out;
}
