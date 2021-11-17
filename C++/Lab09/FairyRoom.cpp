//
// Created by Win7x64 on 2021/11/16.
//

#include <iostream>
#include "FairyRoom.hpp"
#include "FairyDoor.hpp"

FairyRoom::FairyRoom(Wall *firstWall, Wall *secondWall, Wall *thirdWall,
                     Wall *fourthWall) :
        Room(firstWall, secondWall, thirdWall, fourthWall) {
}

void FairyRoom::print() {
    std::cout << "Fairy Room " << getId() <<
              ": This room has faeries in it" << std::endl;

    for (Wall *wall: walls) {
        wall->print();
    }
}
