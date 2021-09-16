//
// Created by Win7x64 on 2021/9/16.
//

#ifndef LAB2TEMPLATE_EXPORTER_HPP
#define LAB2TEMPLATE_EXPORTER_HPP

// Write random number (double) into a given file
// PRE: strFileName (string) is the file path
// PRE: minLines (integer) is the min lines will be writen to the file
// PRE: maxLines (integer) is the max lines will be writen to the file
// PRE: lowBound (double) is the min number will be writen to the file
// PRE: highBound (double) is the max number will be writen to the file
// PRE: precision (integer) is the precision of the number
// POST: all parameters will not be changed
// RETURN: lines written in to the file
int writeReadings(
        const char *strFileName,
        int minLines,
        int maxLines,
        double lowBound,
        double highBound,
        int precision);

#endif //LAB2TEMPLATE_EXPORTER_HPP
