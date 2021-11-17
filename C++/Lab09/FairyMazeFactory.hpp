//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include "MazeFactory.hpp"

class FairyMazeFactory : public MazeFactory {
public:
    // Create a new maze
    // RETURN: the pointer to a new Maze instance
    Maze *make_maze() override;

    // Create a new wall
    // RETURN: the pointer to a new Wall instance
    Wall *make_wall() override;

    // Create a new room
    // RETURN: the pointer to a new Room instance
    Room *make_room() override;

    // Create a new door
    // RETURN: the pointer to a new Door instance
    Door *make_door(Room *firstRoom, Room *secondRoom) override;

    // The destructor
    ~FairyMazeFactory() override = default;
};
