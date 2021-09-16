//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include <iomanip>
#include "exporter.hpp"
#include "statistics.hpp"

using namespace std;

int main() {
    // Define boundaries
    const int minLines{512}, maxLines{1024}, precision{3};
    const double lowBound{50.0}, highBound{90.0};
    const char *strFileName{"Readings.txt"};

    // Write data to the file
    int lines = writeReadings(strFileName, minLines, maxLines, lowBound,
                              highBound, precision);
    cout << lines << " lines have been wrote." << endl;

    // Read statistics from the file
    double dataRead[maxLines] = {0.0};
    double average{0.0}, maxData{lowBound}, minData{highBound}, medianData{0.0};
    lines = analyseReadings(strFileName, dataRead, minData,
                            maxData, medianData, average);

    // Print statistics result
    cout << "There are " << lines << " readings in the file.";
    cout << "The average reading is " << fixed << setprecision(precision)
         << average << "." << endl;
    cout << "The highest reading is " << fixed << setprecision(precision)
         << maxData << "." << endl;
    cout << "The lowest reading is " << fixed << setprecision(precision)
         << minData << "." << endl;
    cout << "The median reading is " << fixed << setprecision(precision)
         << medianData << "." << endl;

    return 0;
}
