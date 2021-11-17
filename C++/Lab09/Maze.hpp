//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include <vector>

class Room;

class Door;

class Maze {
protected:
    std::vector<Room *> rooms;
    std::vector<Door *> doors;

public:
    // Add a room to maze
    // PRE: room is a room to be added
    // POST: the room is added to rooms
    void add_room(Room *room);

    // Add a door to maze
    // PRE: door is a door to be added
    // POST: the door is added to doors
    void add_door(Door *door);

    // Print the description of the maze
    // POST: nothing has been changed
    virtual void print() const = 0;

    // The destructor
    virtual ~Maze();
};