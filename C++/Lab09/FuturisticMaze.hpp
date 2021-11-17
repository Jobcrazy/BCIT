//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include "Maze.hpp"

class FuturisticMaze: public Maze{
public:
    // Print the description of the maze
    // POST: nothing has been changed
    void print() const override;

    // The destructor
    ~FuturisticMaze() override = default;
};

