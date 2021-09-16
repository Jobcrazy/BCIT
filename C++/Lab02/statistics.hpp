//
// Created by Win7x64 on 2021/9/16.
//

#ifndef LAB2TEMPLATE_STATISTICS_HPP
#define LAB2TEMPLATE_STATISTICS_HPP

// Analyse data (double) from a given file
// PRE: strFileName (string) is the file path
// PRE: data (double*) is the buffer where the data will be writen into
// PRE: minData (double &) will be the min number in the file
// PRE: maxData (double &) will be the max number in the file
// PRE: medianData (double &) will be the median number in the file
// PRE: average (double &) will be the average of all numbers
// POST: only strFileName will not be changed
// RETURN: lines read from the file
int analyseReadings(
        const char *strFileName,
        double *data,
        double &minData,
        double &maxData,
        double &medianData,
        double &average);

#endif //LAB2TEMPLATE_STATISTICS_HPP
