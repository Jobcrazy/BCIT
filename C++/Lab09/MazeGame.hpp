//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

class MazeFactory;

class Maze;

class MazeGame {
private:
    MazeFactory* mFactory{nullptr};
    Maze *maze{nullptr};

public:
    // Create a new maze
    // PRE: mazeFactory is the maze factory
    // RETURN: the pointer to the new maze
    Maze *create_maze(MazeFactory *mazeFactory);

    // Print the description of the maze
    // POST: nothing has been changed
    void print() const;

    // The destructor
    virtual ~MazeGame();
};
