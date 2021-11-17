//
// Created by Win7x64 on 2021/11/16.
//

#include "FairyMazeFactory.hpp"
#include "FairyWall.hpp"
#include "FairyRoom.hpp"
#include "FairyDoor.hpp"
#include "FairyMaze.hpp"

Maze *FairyMazeFactory::make_maze() {
    Maze *fairyMaze = new FairyMaze;

    Room *firstRoom = make_room();
    fairyMaze->add_room(firstRoom);

    Room *secondRoom = make_room();
    fairyMaze->add_room(secondRoom);

    Door *door = make_door(firstRoom, secondRoom);
    fairyMaze->add_door(door);

    return fairyMaze;
}

Wall *FairyMazeFactory::make_wall() {
    return new FairyWall;
}

Room *FairyMazeFactory::make_room() {
    return new FairyRoom(make_wall(), make_wall(),
                         make_wall(), make_wall());
}

Door *FairyMazeFactory::make_door(
        Room *firstRoom, Room *secondRoom) {
    Door* door = new FairyDoor(firstRoom, secondRoom);
    firstRoom->setDoor(door);
    secondRoom->setDoor(door);
    return door;
}
