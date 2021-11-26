//
// Created by Win7x64 on 2021/11/12.
//

#pragma once

#include <string>

class City {
private:
    std::string name;
    double x;
    double y;

public:
    // The constructor
    // PRE: name is the city's name
    explicit City(std::string name, double x, double y);

    // Get the x location of the city
    // POST: nothing has been changed
    // RETURN: the x location of the city
    [[nodiscard]] inline double getX() const { return x; }

    // Get the y location of the city
    // POST: nothing has been changed
    // RETURN: the y location of the city
    [[nodiscard]] inline double getY() const { return y; }

    // Overload operator << for output
    // PRE: out is the output stream
    // PRE: matrix is a City instance
    // POST: the City instance city is unchanged and printed
    // RETURN: the output stream
    friend std::ostream &operator<<(std::ostream &os, const City &city);

    // Check two City instances to see if they are the same
    // PRE: lhs is one City instance
    // PRE: rhs is another City instance
    // POST: both City instances are unchanged
    // RETURN: true if the two instance are the same
    friend bool operator==(const City &lhs, const City &rhs);
};
