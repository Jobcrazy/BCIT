cmake_minimum_required(VERSION 3.20)
project(lab2template)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic")
set(CMAKE_CXX_STANDARD 20)

add_executable(lab2template main.cpp exporter.cpp exporter.hpp statistics.cpp statistics.hpp)
