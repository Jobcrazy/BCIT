//
// Created by Win7x64 on 2021/11/16.
//

#include <iostream>
#include "Room.hpp"
#include "FuturisticDoor.hpp"

void FuturisticDoor::print() const {
    std::cout << "This door has a pipe for a handle. This door connects ";
    for (size_t i = 0; i < rooms.size(); i++) {
        std::cout << "Dystopian Room " << rooms[i]->getId();
        if (i != rooms.size() - 1) {
            std::cout << " and ";
        }
    }
    std::cout << std::endl;
}

FuturisticDoor::FuturisticDoor(Room *firstRoom, Room *secondRoom) :
        Door(firstRoom, secondRoom) {
}
