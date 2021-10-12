cmake_minimum_required(VERSION 3.20)
project(lab1template)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic")
set(CMAKE_CXX_STANDARD 20)

add_executable(lab5template main.cpp Operation.hpp AbstractOperation.hpp AdditionOperation.hpp SubtractionOperation.hpp MultiplicationOperation.hpp DivisionOperation.hpp RPNCalculator.cpp RPNCalculator.hpp)
