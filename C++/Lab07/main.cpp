//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>
#include <fstream>
#include <string>
#include <sstream>
#include "Course.hpp"

using namespace std;

/**
 * Print all course schedule to the screen
 * @param vCourses a course vector
 */
void printSchedule(const vector<Course> &vCourses) {
    cout << "All Courses:" << endl;
    copy(vCourses.begin(), vCourses.end(),
         ostream_iterator<Course>(cout, "\n"));
}

/**
 * load course data from the give file path
 * @param strFilePath the course data file path
 * @param vCourses the vector to store the courses
 * @return true if the data is loaded correctly, otherwise false
 */
bool loadCourses(string &strFilePath, vector<Course> &vCourses) {
    ifstream ifs{strFilePath};
    if (!ifs) {
        return false;
    }

    string strLine;
    string strCourseName;
    string strDay;
    unsigned int startTime;
    unsigned int endTime;

    while (getline(ifs, strLine)) {
        istringstream iss{strLine};
        iss >> strCourseName >> strDay >> startTime >> endTime;
        vCourses.emplace_back(strCourseName, Course::strToDay(strDay),
                              startTime, endTime);
    }

    return true;
}

/**
 * Print course schedule with conflicts to the screen
 * @param vCourses a course vector
 */
void printConflicts(vector<Course> &vCourse) {
    for (size_t i = 0; i < vCourse.size(); ++i) {
        for (size_t j = i + 1; j < vCourse.size(); ++j) {
            if (vCourse[i].isOverlapped(vCourse[j])) {
                cout << "CONFLICT:" << endl;
                cout << vCourse[i] << endl;
                cout << vCourse[j] << endl << endl;
            }
        }
    }
}

/**
 * The main function
 */
int main() {
    //Read data from courses.txt and store in a vector
    vector<Course> vCourses;
    string strFilePath{"../courses.txt"};
    if (!loadCourses(strFilePath, vCourses)) {
        cout << " Failed to load course data, exit." << endl;
        exit(1);
    }

    // sort the courses with the sort algorithm
    sort(vCourses.begin(), vCourses.end());

    //print out schedule conflicts
    printConflicts(vCourses);

    //print out schedule
    printSchedule(vCourses);
    return 0;
}
