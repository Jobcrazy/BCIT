//
// Created by Hang Liu on 2021/9/23.
//

#pragma once

class MyStack {
private:
    int m_index;
    static constexpr int m_capacity{10};
    int m_stack[m_capacity]{0};

public:
    // Constructor
    MyStack();

    // Add an element into the stack
    // PRE: element is an integer
    // POST: element has been put in the stack
    // RETURN: true if there is vacancy in the stack, otherwise false
    bool push(int element);

    // Remove the top element from the stack
    // POST: the top element of the stack has been removed
    void pop();

    // Get the top element of the stack
    // POST: the stack is unchanged
    // RETURN: the top element of the stack
    int top() const;

    // Check if the stack is empty
    // POST: the stack is unchanged
    // RETURN: true if the stack is empty, otherwise false
    bool empty() const;

    // Check if the stack is full
    // POST: the stack is unchanged
    // RETURN: true if the stack is full, otherwise false
    bool full() const;

    // Print the stack on the screen
    // POST: the stack is unchanged
    void print() const;

    // Get the capacity of the stack
    // POST: the stack is unchanged
    // RETURN: the capacity of the stack
    static int capacity();
};
