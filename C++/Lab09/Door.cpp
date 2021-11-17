//
// Created by Win7x64 on 2021/11/16.
//

#include "Door.hpp"
#include "Room.hpp"

Door::Door(Room *first, Room *second) {
    rooms.push_back(first);
    rooms.push_back(second);
}