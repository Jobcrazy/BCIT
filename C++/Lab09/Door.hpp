//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include <vector>

class Room;

class Door {
protected:
    std::vector<Room *> rooms;
public:
    // The constructor
    // PRE: first is the first room
    // PRE: second is the second room
    Door(Room *first, Room *second);

    // Print the description of the door
    // POST: nothing has been changed
    virtual void print() const = 0;

    // The destructor
    virtual ~Door() = default;
};
