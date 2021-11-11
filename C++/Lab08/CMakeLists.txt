cmake_minimum_required(VERSION 3.20)
project(lab1template)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic")
set(CMAKE_CXX_STANDARD 20)

add_executable(lab8template main.cpp Heap.cpp Heap.hpp Patient.cpp Patient.hpp Hospital.cpp Hospital.hpp Doctor.cpp Doctor.hpp)
