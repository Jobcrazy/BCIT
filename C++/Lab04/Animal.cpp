//
// Created by Win7x64 on 2021/9/29.
//

#include <iostream>
#include "Animal.hpp"

long Animal::count = 1;

Animal::Animal() :
        id(count++),
        age(0),
        alive(true),
        latitude(0),
        longitude(0) {
    std::cout << "Animal(" << id << ") default constructor" << std::endl;
}

Animal::Animal(int iAge, double dLatitude, double dLongitude)
        : id(count++),
          age(iAge),
          alive(true),
          latitude(dLatitude),
          longitude(dLongitude) {
    std::cout << "Animal(" << id << ") 3 parameters constructor" << std::endl;
}

Animal::Animal(const Animal &animal) {
    std::cout << "Animal(" << id << ") copy constructor" << std::endl;

    id = count++;
    age = animal.age;
    alive = animal.alive;
    longitude = animal.longitude;
    latitude = animal.latitude;
}

Animal::~Animal() {
    std::cout << "Animal(" << id << ") deconstructed" << std::endl;
}

void Animal::move(double dLatitude, double dLongitude) {
    std::cout << "Animal(" << id << ") move" << std::endl;

    latitude = dLatitude;
    longitude = dLongitude;
}

void Animal::sleep() const {
    std::cout << "Animal(" << id << ") sleep" << std::endl;
}

void Animal::eat() const {
    std::cout << "Animal(" << id << ") eat" << std::endl;
}

void Animal::setAlive(bool bAlive) {
    std::cout << "Animal(" << id << ") set alive to "
              << std::boolalpha << bAlive << std::endl;

    alive = bAlive;
}

std::ostream &operator<<(std::ostream &out, const Animal &animal) {
    out << "Animal(" << animal.id
        << "): \tage:" << animal.age
        << "\tlongitude: " << animal.longitude
        << "\tlatitude: " << animal.latitude
        << "\theight: " << animal.getHeight()
        << "\talive: " << std::boolalpha << animal.alive;
    return out;
}