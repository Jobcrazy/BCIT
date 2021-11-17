//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include <vector>

class Wall;

class Door;

class Room {
private:
    static int roomCounter;
    int id;

protected:
    Door *door;
    std::vector<Wall *> walls;

public:
    // The constructor
    // PRE: firstWall is the first wall
    // PRE: secondWall is the second wall
    // PRE: thirdWall is the third wall
    // PRE: fourthWall is the fourth wall
    Room(Wall *firstWall, Wall *secondWall,
         Wall *thirdWall, Wall *fourthWall);

    // Getter for room id
    // POST: nothing has been changed
    // RETURN: the room id
    [[nodiscard]] int getId() const { return id; }

    // Set the door of the room
    // PRE: aDoor is the door
    // POST: door is set
    void setDoor(Door *aDoor);

    // Print the description of the room
    // POST: nothing has been changed
    virtual void print() = 0;

    // The destructor
    virtual ~Room();
};