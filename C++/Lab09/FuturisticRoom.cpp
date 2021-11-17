//
// Created by Win7x64 on 2021/11/17.
//

#include <iostream>
#include "FuturisticRoom.hpp"
#include "FuturisticDoor.hpp"

FuturisticRoom::FuturisticRoom(Wall *firstWall, Wall *secondWall, Wall *thirdWall,
                     Wall *fourthWall) :
        Room(firstWall, secondWall, thirdWall, fourthWall) {
}

void FuturisticRoom::print() {
    std::cout << "Futuristic Room " << getId() <<
              ": This room is run down and cracked" << std::endl;
    for (Wall *wall: walls) {
        wall->print();
    }
}
