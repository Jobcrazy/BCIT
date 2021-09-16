//
// Created by Win7x64 on 2021/9/16.
//

#include <iostream>
#include <fstream>
#include <random>
#include <iomanip>
#include "exporter.hpp"

using namespace std;

int writeReadings(
        const char *strFileName,
        int minLines,
        int maxLines,
        double lowBound,
        double highBound,
        int precision) {
    // Return if the pointer is invalid
    if (!strFileName) {
        cout << "Invalid pointer" << endl;
        exit(1);
    }

    // Open a file to write (create the file if it does not exist)
    ofstream ofs{strFileName};
    if (!ofs) {
        // Failed to open a file, exit
        cout << "Cannot open file: " << strFileName << endl;
        exit(1);
    }

    // Initialize random generator
    random_device rd;
    mt19937 generator(rd());
    uniform_int_distribution<> intDistribution{minLines, maxLines};
    uniform_real_distribution<> realDistribution{lowBound, highBound};

    // Generate the max line number
    int maxIndex = intDistribution(generator);

    // Use a loop to generate data
    for (int index = 0; index < maxIndex; ++index) {
        ofs << index << " " << fixed << setprecision(precision)
            << realDistribution(generator) << endl;
    }

    // Return the line number we just wrote
    return maxIndex;
}