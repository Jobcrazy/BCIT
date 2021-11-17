//
// Created by Win7x64 on 2021/11/16.
//

#include "Maze.hpp"
#include "Room.hpp"
#include "Door.hpp"

void Maze::add_room(Room *room) {
    rooms.push_back(room);
}

void Maze::add_door(Door *door) {
    doors.push_back(door);
}

Maze::~Maze() {
    for (Room *room: rooms) {
        delete room;
    }

    for (Door *door: doors) {
        delete door;
    }
}
