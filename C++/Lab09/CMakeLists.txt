cmake_minimum_required(VERSION 3.20)
project(lab1template)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic")
set(CMAKE_CXX_STANDARD 20)

add_executable(lab9template driver.cpp MazeFactory.hpp MazeGame.cpp Maze.hpp Room.hpp Wall.hpp Door.hpp Maze.cpp Door.cpp Room.cpp FairyMazeFactory.cpp FairyMazeFactory.hpp FairyWall.cpp FairyWall.hpp FairyRoom.cpp FairyRoom.hpp FairyDoor.cpp FairyDoor.hpp FairyMaze.cpp FairyMaze.hpp FuturisticWall.cpp FuturisticWall.hpp FuturisticRoom.cpp FuturisticRoom.hpp FuturisticDoor.cpp FuturisticDoor.hpp FuturisticMaze.cpp FuturisticMaze.hpp FuturisticMazeFactory.cpp FuturisticMazeFactory.hpp)
