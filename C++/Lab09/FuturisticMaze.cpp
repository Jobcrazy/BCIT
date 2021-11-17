//
// Created by Win7x64 on 2021/11/17.
//

#include <iostream>
#include "Room.hpp"
#include "Door.hpp"
#include "FuturisticMaze.hpp"

void FuturisticMaze::print() const {
    std::cout << "An Orwellian dystopian maze" << std::endl;

    for (Room *room: rooms) {
        room->print();
    }

    for (Door *door: doors) {
        door->print();
    }
}
