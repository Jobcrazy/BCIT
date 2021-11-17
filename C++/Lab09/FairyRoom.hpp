//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include "Wall.hpp"
#include "Room.hpp"

class FairyRoom : public Room {
public:
    // The constructor
    // PRE: firstWall is the first wall
    // PRE: secondWall is the second wall
    // PRE: thirdWall is the third wall
    // PRE: fourthWall is the fourth wall
    FairyRoom(Wall *firstWall, Wall *secondWall,
            Wall *thirdWall, Wall *fourthWall);

    // Print the description of the room
    // POST: nothing has been changed
    void print() override;

    // The destructor
    ~FairyRoom() override = default;
};
