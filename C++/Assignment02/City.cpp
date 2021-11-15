//
// Created by Win7x64 on 2021/11/12.
//

#include <iostream>
#include <utility>
#include "City.hpp"
#include "RandomNumber.hpp"

City::City(std::string name) :
        name(std::move(name)),
        x(RandomNumber::getInstance().getRandomDouble(0, MAP_BOUNDARY)),
        y(RandomNumber::getInstance().getRandomDouble(0, MAP_BOUNDARY)) {

}

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
