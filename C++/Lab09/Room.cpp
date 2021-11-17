//
// Created by Win7x64 on 2021/11/16.
//

#include "Room.hpp"
#include "Wall.hpp"
#include "Door.hpp"

int Room::roomCounter = 0;

Room::Room(Wall *firstWall, Wall *secondWall,
           Wall *thirdWall, Wall *fourthWall) : id(roomCounter++) {
    walls.push_back(firstWall);
    walls.push_back(secondWall);
    walls.push_back(thirdWall);
    walls.push_back(fourthWall);
}

void Room::setDoor(Door *aDoor) {
    this->door = aDoor;
}

Room::~Room() {
    for (Wall *wall: walls) {
        delete wall;
    }
}
