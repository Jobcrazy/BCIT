//
// Created by Win7x64 on 2021/11/12.
//

#pragma once

#include <vector>
#include "City.hpp"

class Tour {
private:
    static constexpr int SHUFFLES{64};
    std::vector<const City *> vpCities;
    double distance{0};

public:
    // The default constructor
    Tour() = default;

    // The constructor
    // PRE: cities is a group of City instance
    explicit Tour(const std::vector<City> &cities);

    // The copy constructor
    // PRE: other is another Tour instance
    // POST: all data from the other Tour instance has been copied
    Tour(const Tour &tour);

    // Get the total distance of the tour instance
    // POST: nothing has been changed
    // RETURN: the total distance of the tour instance
    [[nodiscard]] double getTourDistance() const { return distance; };

    // Get the City pointer with a given index
    // PRE: index is the position in vpCities
    // POST: nothing has been changed
    // RETURN: the City pointer with a given index
    [[nodiscard]] const City *getCity(int index) const;

    // Add a new City pointer to vpCities
    // PRE: pCity is the pointer of a City instance
    // POST: pCity has been added into vpCities
    void addCity(const City *pCity);

    // Determine if a City is in vpCities or not
    // PRE: pOtherCity is the pointer of a City instance
    // POST: nothing has been changed
    // RETURN: true if pOtherCity is in vpCities, otherwise false
    bool containsCity(const City *pOtherCity) const;

    // Return the number of City pointers in vpCities
    // POST: nothing has been changed
    // RETURN: the number of City pointers in vpCities
    [[nodiscard]] size_t size() const { return vpCities.size(); }

    // Swap two city's pointers in vpCities
    // PRE: first is the first city index
    // PRE: second is the second city index
    // POST: the two city's pointers have been swapped
    void swapCity(int first, int second);

    // Copy data from another Tour instance
    // PRE: other is another Tour instance
    // POST: all data is copied from "other"
    Tour &operator=(Tour tour);

    // Check if a Tour instances is less than another Tour instances
    // PRE: lhs is one Tour instance
    // PRE: rhs is another Tour instance
    // POST: both Tour instances are unchanged
    // RETURN: true if the lhs is less than rhs
    friend bool operator<(const Tour &lhs, const Tour &rhs);

    // Check two Tour instances to see if they are the same
    // PRE: lhs is one Tour instance
    // PRE: rhs is another Tour instance
    // POST: both Tour instances are unchanged
    // RETURN: true if the two instance are the same
    friend bool operator==(const Tour &lhs, const Tour &rhs);

private:
    // Compute the distance for this tour
    // POST: the member variable distance has been updated
    void computeDistance();

    // Shuffle the City pointers in vpCities
    // POST: the City pointers have been shuffled for SHUFFLES times
    void shuffleCities();

    // Get the distance between two cities
    // PRE: first is the first city pointer
    // PRE: second is the second city pointer
    // POST: nothing has been changed
    // RETURN: the distance between two cities
    static double getDistanceBetweenCities(
            const City *first, const City *second);
};