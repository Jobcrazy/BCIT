//Name: Hang Liu, Joonhyeong Kim
//Student# : A01173804, A01077938

#include <iostream>
#include <vector>
#include "GeneticAlgorithm.hpp"

int main() {
    // Init master list (32 cities)
    constexpr int CITIES_IN_TOUR{32};
    std::vector<City> master_list;
    for (int i = 0; i < CITIES_IN_TOUR; ++i) {
        master_list.emplace_back(std::to_string(i));
    }

    // Perform genetic algorithm
    GeneticAlgorithm geneticAlgorithm(master_list);
    Tour bestTour = geneticAlgorithm.getBestTour();

    // Print the final distance
    std::cout << "The shorted distance of the tour is: "
              << bestTour.getTourDistance();

    return 0;
}
