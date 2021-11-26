//
// Created by Win7x64 on 2021/11/12.
//

#include <iostream>
#include "City.hpp"

City::City(std::string name, double x, double y) :
        name(std::move(name)), x(x), y(y) {}

std::ostream &operator<<(std::ostream &os, const City &city) {
    os <<
       "City Name:" << city.name <<
       " -- X:" << city.x <<
       " Y:" << city.y
       << std::endl;
    return os;
}

bool operator==(const City &lhs, const City &rhs) {
    return lhs.x == rhs.x && lhs.y == rhs.y;
}
