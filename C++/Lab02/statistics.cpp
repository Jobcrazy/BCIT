//
// Created by Win7x64 on 2021/9/16.
//

#include <iostream>
#include <fstream>
#include "statistics.hpp"

using namespace std;

int analyseReadings(
        const char *strFileName,
        double *data,
        double &minData,
        double &maxData,
        double &medianData,
        double &average) {
    // Return if the pointer is invalid
    if (!strFileName || !data) {
        cout << "Invalid pointer" << endl;
        exit(1);
    }

    ifstream ifs{strFileName};
    if (!ifs) {
        // Failed to open a file, exit
        cout << "Cannot open file: " << strFileName << endl;
        exit(1);
    }

    // Read data from the file
    int lines = 0;
    double currentData{0.0}, sum{0.0};
    while (ifs >> lines >> currentData) {
        if (currentData < minData) {
            minData = currentData;
        } else if (currentData > maxData) {
            maxData = currentData;
        }
        data[lines] = currentData;
        sum += currentData;
    }
    medianData = data[lines / 2];
    lines++;
    average = sum / lines;

    return lines;
}