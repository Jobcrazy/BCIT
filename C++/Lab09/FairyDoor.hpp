//
// Created by Win7x64 on 2021/11/16.
//

#include "Door.hpp"

class FairyDoor : public Door {
public:
    // The constructor
    // PRE: first is the first room
    // PRE: second is the second room
    FairyDoor(Room *firstRoom, Room *secondRoom);

    // Print the description of the door
    // POST: nothing has been changed
    void print() const override;

    // The destructor
    ~FairyDoor() override = default;
};
