//
// Created by Win7x64 on 2021/11/12.
//

#pragma once

#include <random>

class RandomNumber {
private:
    std::random_device *pRd;
    std::mt19937 *pGenerator;

public:
    // The getter for singleton
    // POST: nothing has been changed
    // RETURN: the RandomNumber singleton instance
    static RandomNumber &getInstance() {
        static RandomNumber randomNumber;
        return randomNumber;
    }

    // The destructor
    ~RandomNumber();

    // Delete the copy constructor
    // POST: copy constructor cannot be used
    RandomNumber(const RandomNumber &) = delete;

    // Delete the assign operator
    // POST: assign operator cannot be used
    RandomNumber &operator=(const RandomNumber &) = delete;

    // Generate a random number of double type
    // PRE: lowBound is the low boundary
    // PRE: highBound is the high boundary
    // POST: nothing has been changed
    // RETURN: a random number of double type
    [[nodiscard]] double getRandomDouble(float lowBound, float highBound) const;

    // Generate a random number of int type
    // PRE: lowBound is the low boundary
    // PRE: highBound is the high boundary
    // POST: nothing has been changed
    // RETURN: a random number of int type
    [[nodiscard]] int getRandomInt(int lowBound, int highBound) const;

private:
    // The default constructor
    RandomNumber();
};

