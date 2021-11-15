//
// Created by Win7x64 on 2021/11/12.
//

#pragma once

#include <vector>
#include "City.hpp"
#include "Tour.hpp"

class GeneticAlgorithm {
private:
    const int POPULATION_SIZE{32};
    const int ITERATIONS{1000};
    const int PARENT_POOL_SIZE{5};
    const double MUTATION_RATE{0.15};
    const double IMPROVEMENT_FACTOR{0.08};
    typedef std::vector<Tour>::iterator tourIt;
    std::vector<City> cities;

public:
    // The constructor
    // PRE: cities is a list of City instances
    explicit GeneticAlgorithm(std::vector<City> cities);

    // Get the relatively best tour
    // POST: nothing has been changed
    // RETURN: the relatively best tour
    [[nodiscard]] Tour getBestTour() const;

private:
    // Determine the shortest tour in a group of tours
    // PRE: tours is a group of tours
    // POST: nothing has been changed
    // RETURN: the shortest tour in a group of tours
    static tourIt findElite(std::vector<Tour> &tours);

    // Perform the cross algorithm on a group of tours
    // PRE: tours is a group of tours
    // POST: nothing has been changed
    // RETURN: a new vector of crossed tours
    [[nodiscard]] std::vector<Tour> crossTours(
            const std::vector<Tour> &tours) const;

    // Create a parent tour from the existing tours
    // PRE: tours is a group of existing tours
    // POST: nothing has been changed
    // RETURN: a parent tour from the existing tours
    [[nodiscard]] std::vector<Tour> selectParents(
            const std::vector<Tour> &tours) const;

    // Perform the cross parent algorithm on two parent tours
    // PRE: first is the first set of parent tours
    // PRE: second is the second set of parent tours
    // POST: nothing has been changed
    // RETURN: a new crossed tours
    [[nodiscard]] Tour crossParents(
            const std::vector<Tour> &first,
            const std::vector<Tour> &second) const;

    // Perform the mutate algorithm on a group of tours
    // PRE: tours is a group of existing tours
    // POST: tours has been mutated with MUTATION_RATE
    void mutateTours(std::vector<Tour> &tours) const;
};
