//
// Created by Hang Liu on 2021/9/23.
//
#define CATCH_CONFIG_MAIN

#include "catch.hpp"
#include "myStack.hpp"

TEST_CASE("A new stack is empty", "testTag1")
{
    MyStack tester;
    REQUIRE(tester.empty() == true);
    REQUIRE(tester.full() == false);
}

TEST_CASE("Top in an empty stack", "testTag1")
{
    MyStack tester;
    REQUIRE(tester.top() == -1);
}

TEST_CASE("Pop from a empty stack", "testTag1")
{
    MyStack tester;
    tester.pop();
    REQUIRE(tester.top() == -1);
    tester.pop();
    REQUIRE(tester.top() == -1);
    tester.pop();
    REQUIRE(tester.top() == -1);
    REQUIRE(tester.empty() == true);
    REQUIRE(tester.full() == false);
}

TEST_CASE("Pop from a empty stack then push", "testTag1")
{
    MyStack tester;
    tester.pop();
    tester.pop();
    tester.pop();
    tester.push(1);
    REQUIRE(tester.top() == 1);
    tester.push(2);
    REQUIRE(tester.top() == 2);
    tester.push(3);
    REQUIRE(tester.top() == 3);
}

TEST_CASE("A new stack that is not empty", "testTag1")
{
    MyStack tester;
    tester.push(1);
    tester.push(2);
    tester.push(3);
    REQUIRE(tester.empty() == false);
    REQUIRE(tester.full() == false);
    REQUIRE(tester.top() == 3);
    REQUIRE(tester.empty() == false);
    REQUIRE(tester.full() == false);
}

TEST_CASE("Top/Pop from a stack that is not empty", "testTag1")
{
    MyStack tester;
    tester.push(1);
    tester.push(2);
    tester.push(3);
    REQUIRE(tester.empty() == false);
    REQUIRE(tester.full() == false);
    REQUIRE(tester.top() == 3);
    tester.pop();
    REQUIRE(tester.top() == 2);
    tester.pop();
    REQUIRE(tester.top() == 1);
    tester.pop();
    REQUIRE(tester.top() == -1);
    REQUIRE(tester.empty() == true);
    REQUIRE(tester.full() == false);
}

TEST_CASE("A new stack is full", "testTag1")
{
    MyStack tester;
    tester.push(1);
    tester.push(2);
    tester.push(3);
    tester.push(4);
    tester.push(5);
    tester.push(6);
    tester.push(7);
    tester.push(8);
    tester.push(9);
    tester.push(10);
    REQUIRE(tester.empty() == false);
    REQUIRE(tester.full() == true);
    REQUIRE(tester.top() == 10);
}

TEST_CASE("Push/Top element into a full stack", "testTag1")
{
    MyStack tester;
    tester.push(1);
    tester.push(2);
    tester.push(3);
    tester.push(4);
    tester.push(5);
    tester.push(6);
    tester.push(7);
    tester.push(8);
    tester.push(9);
    tester.push(10);
    REQUIRE(tester.push(11) == false);
    REQUIRE(tester.push(12) == false);
    REQUIRE(tester.push(13) == false);
    REQUIRE(tester.top() == 10);
    REQUIRE(tester.empty() == false);
    REQUIRE(tester.full() == true);
}

TEST_CASE("Pop/Top from an full stack", "testTag1")
{
    MyStack tester;
    tester.push(1);
    tester.push(2);
    tester.push(3);
    tester.push(4);
    tester.push(5);
    tester.push(6);
    tester.push(7);
    tester.push(8);
    tester.push(9);
    tester.push(10);
    REQUIRE(tester.top() == 10);
    tester.pop();
    REQUIRE(tester.top() == 9);
    tester.pop();
    REQUIRE(tester.top() == 8);
    tester.pop();
    REQUIRE(tester.top() == 7);
    tester.pop();
    REQUIRE(tester.top() == 6);
    tester.pop();
    REQUIRE(tester.top() == 5);
    tester.pop();
    REQUIRE(tester.top() == 4);
    tester.pop();
    REQUIRE(tester.top() == 3);
    tester.pop();
    REQUIRE(tester.top() == 2);
    tester.pop();
    REQUIRE(tester.top() == 1);
    tester.pop();
    REQUIRE(tester.top() == -1);
    REQUIRE(tester.empty() == true);
    REQUIRE(tester.full() == false);
}
