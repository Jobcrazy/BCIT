//
// Created by Win7x64 on 2021/11/16.
//

#pragma once

class Wall {
public:
    // Print the description of the wall
    // POST: nothing has been changed
    virtual void print() const = 0;

    // The destructor
    virtual ~Wall() = default;
};