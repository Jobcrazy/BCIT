//
// Created by Win7x64 on 2021/9/29.
//

#pragma once

#include "Animal.hpp"

class Bird : public Animal {
private:
    double height;

public:
    // The default constructor
    Bird();

    // The copy constructor
    // PRE: bird is another instance of Bird
    // POST: copy all info from bird except the id
    Bird(const Bird &bird);

    // The 4-parameters constructor
    // PRE: iAge is a positive integer
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // PRE: dHeight is a double
    // POST: initialize a new Bird with given info
    Bird(int iAge, double dLatitude, double dLongitude, double dHeight);

    // The destructor
    ~Bird() override;

    // Move Bird to a new location
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // PRE: dHeight is a double
    // POST: new location of Bird is set
    void move(double dLatitude, double dLongitude, double dHeight) override;

    // Bird sleeps
    void sleep() const override;

    // Bird eats
    void eat() const override;

    // Get the height of the bird
    // RETURN: the height of the bird
    [[nodiscard]] inline double getHeight() const override { return height; }

    // Overload operator <<
    friend std::ostream &operator<<(std::ostream &out, const Animal &animal);
};