//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

#include "Wall.hpp"

class FuturisticWall : public Wall {
public:
    // Print the description of the wall
    // POST: nothing has been changed
    void print() const override;

    // The destructor
    ~FuturisticWall() override = default;
};