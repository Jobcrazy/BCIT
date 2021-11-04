// Course.h
#pragma once

#include <iostream>
#include <vector>

using namespace std;

class Course {

public:
    enum dayOfWeek {
        UNK = -1, MON, TUE, WED, THUR, FRI
    };

    // The constructor
    // PRE: title is the course title
    // PRE: day is the course day
    // PRE: start_time is the course start time
    // PRE: finish_time is the course finish time
    Course(string title, dayOfWeek day, unsigned int start_time,
           unsigned int finish_time);

    // The copy constructor
    // PRE: m is an existing Course instance
    Course(const Course &m);

    // The overloaded operator =
    // PRE: m is the copy of an existing Course instance
    // POST: the values of m have been swapped to the current instance
    // RETURN: the current Course instance
    Course &operator=(Course m);

    // The overloaded operator <
    // PRE: m is the reference of an existing Course instance
    // POST: nothing has been changed
    // RETURN: true if the courses are on the same day and start_time if less
    //         than m.start_time, otherwise false
    bool operator<(const Course &m) const;

    // The overloaded operator ==
    // PRE: m is the reference of an existing Course instance
    // POST: nothing has been changed
    // RETURN: true if the courses are on the same day and both courses have the same
    //         start_time and finish_time, otherwise false
    bool operator==(const Course &m) const;

    // Determine if two course have overlapped schedule
    // PRE: m is the reference of an existing Course instance
    // POST: nothing has been changed
    // RETURN: true if the courses time is overlapped, otherwise false
    bool isOverlapped(const Course &m) const;

    // Convert day string to dayWeek
    // PRE: strDay represents the day of the week
    // RETURN: dayWeek related to strDay
    static dayOfWeek strToDay(const string &strDay);

    // Overload operator << for output
    // PRE: os is the output stream
    // PRE: m is a Course instance
    // POST: the Course instance m is unchanged and printed
    // RETURN: the output stream
    friend ostream &operator<<(ostream &os, const Course &m);

private:
    string title; // Name of Course
    dayOfWeek day; // Day of Course
    unsigned int start_time; // Course start time in HHMM format
    unsigned int finish_time; // Course finish time in HHMM format
    void swap(Course &m);
};
