//
// Created by Win7x64 on 2021/11/16.
//

#include "FuturisticMazeFactory.hpp"
#include "FuturisticWall.hpp"
#include "FuturisticRoom.hpp"
#include "FuturisticDoor.hpp"
#include "FuturisticMaze.hpp"

Maze *FuturisticMazeFactory::make_maze() {
    Maze *pFuturisticMaze = new FuturisticMaze;

    Room *firstRoom = make_room();
    pFuturisticMaze->add_room(firstRoom);

    Room *secondRoom = make_room();
    pFuturisticMaze->add_room(secondRoom);

    Door *door = make_door(firstRoom, secondRoom);
    pFuturisticMaze->add_door(door);

    return pFuturisticMaze;
}

Wall *FuturisticMazeFactory::make_wall() {
    return new FuturisticWall;
}

Room *FuturisticMazeFactory::make_room() {
    return new FuturisticRoom(make_wall(), make_wall(),
                         make_wall(), make_wall());
}

Door *FuturisticMazeFactory::make_door(
        Room *firstRoom, Room *secondRoom) {
    Door* door = new FuturisticDoor(firstRoom, secondRoom);
    firstRoom->setDoor(door);
    secondRoom->setDoor(door);
    return door;
}
