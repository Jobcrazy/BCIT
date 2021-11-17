//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

class Maze;

class Wall;

class Room;

class Door;

class MazeFactory {
public:
    // Create a new maze
    // RETURN: the pointer to a new Maze instance
    virtual Maze *make_maze() = 0;

    // Create a new wall
    // RETURN: the pointer to a new Wall instance
    virtual Wall *make_wall() = 0;

    // Create a new room
    // RETURN: the pointer to a new Room instance
    virtual Room *make_room() = 0;

    // Create a new door
    // RETURN: the pointer to a new Door instance
    virtual Door *make_door(Room *firstRoom, Room *secondRoom) = 0;

    // The destructor
    virtual ~MazeFactory() = default;
};
