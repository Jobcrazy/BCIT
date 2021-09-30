//
// Created by Win7x64 on 2021/9/29.
//

#pragma once

#include "Animal.hpp"

class Canine : public Animal {
public:
    // The default constructor
    Canine();

    // The copy constructor
    // PRE: canine is another instance of Canine
    // POST: copy all info from canine except the id
    Canine(const Canine &canine);

    // The 3-parameters constructor
    // PRE: iAge is a positive integer
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // POST: initialize a new Canine with given info
    Canine(int iAge, double dLatitude, double dLongitude);

    // The destructor
    ~Canine() override;

    // Move Canine to another location
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // POST: new location is set
    void move(double dLatitude, double dLongitude) override;

    // Canine sleeps
    void sleep() const override;

    // Canine eats
    void eat() const override;

    // Canine hunts another animal
    // PRE: animal is another instance of Animal
    // POST: animal will die if its location is within 1 of Canine
    void hunt(Animal &animal);

private:
    // Overload operator <<
    friend std::ostream &operator<<(std::ostream &out, const Canine &animal);
};


