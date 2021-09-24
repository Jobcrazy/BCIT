//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include <random>
#include "myStack.hpp"

using namespace std;

void checkStackStatus(MyStack& stack){
    // Check if the stack is empty
    cout << "Stack is: " << (stack.empty() ? "empty" : "not empty") << endl;

    // Check if the stack is full
    cout << "Stack is: " << (stack.full() ? "full" : "not full") << endl;
}

/*
int main() {
    // Generate some random integer for the stack testing purpose
    constexpr int lowBound{0}, highBound{100};
    random_device rd;
    mt19937 generator(rd());
    uniform_int_distribution<> intDistribution{lowBound, highBound};

    MyStack stack;
    checkStackStatus(stack);

    // Add random integers into the stack
    for (int i = 0; i < MyStack::capacity(); ++i) {
        int randomNumber = intDistribution(generator);
        cout << "Adding: " << randomNumber << endl;
        stack.push(randomNumber);
    }

    checkStackStatus(stack);

    // Print the stack
    stack.print();

    // Get the top element of the stack
    cout << "Top element is: " << stack.top() << endl;

    // Remove the top element from the stack
    cout << "Pop an element from the stack" << endl;
    stack.pop();

    checkStackStatus(stack);

    return 0;
}
*/