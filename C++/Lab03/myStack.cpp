//
// Created by Hang Liu on 2021/9/23.
//

#include "myStack.hpp"
#include <iostream>

MyStack::MyStack() : m_index(-1) {
}

bool MyStack::push(int element) {
    if (m_capacity - 1 <= m_index) {
        return false;
    }
    ++m_index;
    m_stack[m_index] = element;
    return true;
}

void MyStack::pop() {
    if (0 <= m_index) {
        --m_index;
    }
}

int MyStack::top() const {
    if (0 <= m_index) {
        return m_stack[m_index];
    }
    return -1;
}

bool MyStack::empty() const {
    return m_index == -1;
}

bool MyStack::full() const {
    return m_index == m_capacity - 1;
}

void MyStack::print() const {
    for (int index = m_index; 0 <= index; --index) {
        std::cout << "[" << index << "] " << m_stack[index] << std::endl;
    }
}

int MyStack::capacity() {
    return m_capacity;
}
