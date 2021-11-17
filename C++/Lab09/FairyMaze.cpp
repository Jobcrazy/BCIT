//
// Created by Win7x64 on 2021/11/16.
//

#include <iostream>
#include "Room.hpp"
#include "Door.hpp"
#include "FairyMaze.hpp"

void FairyMaze::print() const {
    std::cout << "A pretty, magical fairy maze" << std::endl;

    for (Room *room: rooms) {
        room->print();
    }

    for (Door *door: doors) {
        door->print();
    }
}
