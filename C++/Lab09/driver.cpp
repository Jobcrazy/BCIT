//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include "MazeGame.hpp"
#include "MazeFactory.hpp"
#include "FairyMazeFactory.hpp"
#include "FuturisticMazeFactory.hpp"
#include "Maze.hpp"

int main() {
    // Test the fairy maze
    auto fairyFactory = new FairyMazeFactory;
    auto mazeGame = new MazeGame;
    mazeGame->create_maze(fairyFactory);
    mazeGame->print();
    delete mazeGame;

    std::cout << std::endl;

    // Test the futuristic maze
    auto futuristicFactory = new FuturisticMazeFactory;
    mazeGame = new MazeGame;
    mazeGame->create_maze(futuristicFactory);
    mazeGame->print();
    delete mazeGame;

    return 0;
}
