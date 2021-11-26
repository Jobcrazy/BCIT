//
// Created by Win7x64 on 2021/11/12.
//

#include <utility>
#include <iostream>
#include "RandomNumber.hpp"
#include "GeneticAlgorithm.hpp"

GeneticAlgorithm::GeneticAlgorithm(std::vector<City> cities) :
        cities(std::move(cities)) {
}

Tour GeneticAlgorithm::getBestTour() const {
    std::vector<Tour> tours;

    // Init Tours
    for (int index = 0; index < POPULATION_SIZE; ++index) {
        tours.emplace_back(cities);
    }

    // Find the first elite
    auto elite = findElite(tours);
    double originalDistance = elite->getTourDistance();

    // Loop to find a better elite
    double improvement = 0;
    for (int itNumber = 0;
         improvement < IMPROVEMENT_FACTOR && itNumber < ITERATIONS;
         ++itNumber) {
        // Find elite and put it at the front of the original tours
        std::iter_swap(tours.begin(), elite);

        // Cross Parents for tours
        std::vector<Tour> crosses = crossTours(tours);
        copy(crosses.begin(), crosses.end(), tours.begin() + 1);

        // Mutate the tours
        mutateTours(tours);

        // Get the latest elite
        auto oldElite = tours.begin();
        auto newElite = findElite(tours);
        bool improved = *newElite < *oldElite;
        elite = newElite;
        if (improved) {
            improvement =
                    (oldElite->getTourDistance() -
                     newElite->getTourDistance()) /
                    oldElite->getTourDistance();
        }

        //Reports
        {
            std::cout << "Iteration number: " << itNumber << std::endl;

            std::cout << "Improved of current iteration: " << std::boolalpha
                      << improved;
            if (improved) {
                std::cout << " (" << improvement * 100 << "%)" << std::endl;
            } else {
                std::cout << std::endl;
            }

            std::cout << "Best distance before this iteration: "
                      << oldElite->getTourDistance() <<
                      " -- Best distance of this iteration: "
                      << newElite->getTourDistance() <<
                      std::endl;

            std::cout << "Best distance so far: " <<
                      newElite->getTourDistance() << std::endl;

            std::cout << "The Original best distance before loop: " <<
                      originalDistance << std::endl;

            std::cout << "Improvement over base so far: " <<
                      originalDistance << " - " <<
                      elite->getTourDistance() << " = "
                      << originalDistance - elite->getTourDistance()
                      << " (Percentage:" << (originalDistance -
                      elite->getTourDistance()) / originalDistance * 100 << "%"
                      << ")" << std::endl << std::endl;
        }
    }

    return *elite;
}

std::vector<Tour>
GeneticAlgorithm::crossTours(const std::vector<Tour> &tours) const {
    std::vector<Tour> result;

    //Add the original elite first
    result.push_back(*tours.begin());

    for (int index = 1; index < POPULATION_SIZE - 1; ++index) {
        // Generate two parents tour sets and cross
        std::vector<Tour> first = selectParents(tours);
        std::vector<Tour> second = selectParents(tours);
        result.push_back(crossParents(first, second));
    }
    return result;
}

Tour GeneticAlgorithm::crossParents(
        const std::vector<Tour> &first,
        const std::vector<Tour> &second) const {
    auto firstElite = std::min_element(first.begin(), first.end());
    auto secondElite = std::min_element(second.begin(), second.end());

    // Generate a random index
    RandomNumber &randomNumber = RandomNumber::getInstance();
    int randomIndex = randomNumber.getRandomInt(
            0, POPULATION_SIZE - 1);

    // Mix two elites into one tour
    Tour childTour;
    for (int index = 0; index <= randomIndex; ++index) {
        childTour.addCity(firstElite->getCity(index));
    }

    for (int i = 0;
         childTour.size() < (size_t) POPULATION_SIZE; ++i) {
        // Skip the city if it exists in the mixed tour
        if (!childTour.containsCity(secondElite->getCity(i))) {
            childTour.addCity(secondElite->getCity(i));
        }
    }

    return childTour;
}

std::vector<Tour> GeneticAlgorithm::selectParents(
        const std::vector<Tour> &tours) const {
    RandomNumber &randomNumber = RandomNumber::getInstance();
    std::vector<Tour> result;

    for (int index = 1; index <= PARENT_POOL_SIZE; ++index) {
        int randomIndex = randomNumber.getRandomInt(
                0, POPULATION_SIZE - 1);
        result.push_back(tours[randomIndex]);
    }

    return result;
}

GeneticAlgorithm::tourIt GeneticAlgorithm::findElite(std::vector<Tour> &tours) {
    return std::min_element(tours.begin(), tours.end());
}

void GeneticAlgorithm::mutateTours(std::vector<Tour> &tours) const {
    RandomNumber &randomNumber = RandomNumber::getInstance();

    for (int index = 1; index < (int) tours.size(); ++index) {
        for (int cityIndex = 0;
             cityIndex < (int) tours[index].size(); ++cityIndex) {
            if (randomNumber.getRandomDouble(
                    0, 1) < MUTATION_RATE) {
                // Mutate only if the random number is equal to MUTATION_RATE
                tours[index].swapCity(cityIndex, cityIndex + 1);
            }
        }
    }
}