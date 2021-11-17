//
// Created by Win7x64 on 2021/11/16.
//

#include "MazeGame.hpp"
#include "MazeFactory.hpp"
#include "Maze.hpp"

Maze *MazeGame::create_maze(MazeFactory *mazeFactory) {
    mFactory = mazeFactory;
    maze = mazeFactory->make_maze();
    return maze;
}

void MazeGame::print() const{
    maze->print();
}

MazeGame::~MazeGame() {
    delete mFactory;
    delete maze;
}

