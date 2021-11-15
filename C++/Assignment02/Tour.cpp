//
// Created by Win7x64 on 2021/11/12.
//
#include <iostream>
#include <cmath>
#include "Tour.hpp"
#include "RandomNumber.hpp"

Tour::Tour(const std::vector<City> &cities) {
    for (const auto &city: cities) {
        vpCities.push_back(&city);
    }

    shuffleCities();
}

Tour::Tour(const Tour &tour) {
    for (auto pCity: tour.vpCities) {
        vpCities.push_back(pCity);
    }
    distance = tour.distance;
}

void Tour::shuffleCities() {
    RandomNumber &randomNumber = RandomNumber::getInstance();
    for (int i = 0; i < SHUFFLES; ++i) {
        int firstIndex = randomNumber.getRandomInt(
                0, static_cast<int>(vpCities.size() - 1));
        int secondIndex = randomNumber.getRandomInt(
                0, static_cast<int>(vpCities.size() - 1));
        if (firstIndex != secondIndex) {
            std::swap(vpCities[firstIndex], vpCities[secondIndex]);
        }
    }

    computeDistance();
}

bool operator<(const Tour &lhs, const Tour &rhs) {
    return lhs.distance < rhs.distance;
}

void Tour::computeDistance() {
    distance = 0;
    for (size_t i = 0;
         i < vpCities.size() - 1 && i + 1 <= vpCities.size();
         ++i) {
        distance += getDistanceBetweenCities(
                vpCities[i], vpCities[i + 1]);
    }
}

double Tour::getDistanceBetweenCities(
        const City *first, const City *second) {
    double xDistance{first->getX() - second->getX()};
    double yDistance{first->getY() - second->getY()};
    return sqrt(xDistance * xDistance + yDistance * yDistance);
}

bool operator==(const Tour &lhs, const Tour &rhs) {
    return lhs.distance == rhs.distance;
}

bool Tour::containsCity(const City *pOtherCity) const {
    if (std::any_of(
            vpCities.begin(),
            vpCities.end(),
            [&pOtherCity](const City *pCity) {
                return *pCity == *pOtherCity;
            }))
        return true;

    return false;
}

const City *Tour::getCity(int index) const {
    return vpCities[index];
}

void Tour::addCity(const City *pCity) {
    vpCities.push_back(pCity);
    computeDistance();
}

Tour &Tour::operator=(Tour tour) {
    std::swap(vpCities, tour.vpCities);
    std::swap(distance, tour.distance);
    return *this;
}

void Tour::swapCity(int first, int second) {
    auto it = vpCities.begin();
    if ((int) vpCities.size() == first + 1) {
        second = 0;
    }
    std::iter_swap(it + first, it + second);
}
