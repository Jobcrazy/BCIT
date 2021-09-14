#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAX_REPEATED_TIME 1

bool isIntRepeated(
        void *elementArray,
        void *element,
        size_t arraySize) {
    int *intArray = elementArray;
    int intValue = *((int *) element);
    size_t counter = 0;
    for (int index = 0;
         index < arraySize && counter <= MAX_REPEATED_TIME; ++index) {
        if (intValue == intArray[index]) {
            ++counter;
        }
    }

    return counter > MAX_REPEATED_TIME;
}

bool isFloatRepeated(
        void *elementArray,
        void *element,
        size_t arraySize) {
    float *floatArray = elementArray;
    float floatValue = *((float *) element);
    size_t counter = 0;
    for (int index = 0;
         index < arraySize && counter <= MAX_REPEATED_TIME; ++index) {
        if (floatValue == floatArray[index]) {
            ++counter;
        }
    }

    return counter > MAX_REPEATED_TIME;
}

bool isLongRepeated(
        void *elementArray,
        void *element,
        size_t arraySize) {
    long *longArray = elementArray;
    long longValue = *((long *) element);
    size_t counter = 0;
    for (int index = 0;
         index < arraySize && counter <= MAX_REPEATED_TIME; ++index) {
        if (longValue == longArray[index]) {
            ++counter;
        }
    }

    return counter > MAX_REPEATED_TIME;
}

bool isCharRepeated(
        void *elementArray,
        void *element,
        size_t arraySize) {
    char *charArray = elementArray;
    char charValue = *((char *) element);
    size_t counter = 0;
    for (int index = 0;
         index < arraySize && counter <= MAX_REPEATED_TIME; ++index) {
        if (charValue == charArray[index]) {
            ++counter;
        }
    }

    return counter > MAX_REPEATED_TIME;
}

bool areElementsInGenericArrayUnique(
        void *elementArray,
        size_t arraySize,
        size_t elementSize,
        bool (*isRepeated)(void *, void *, size_t)) {
    for (int index = 0; index < arraySize; ++index) {
        void *currentElement = (char *) elementArray + index * elementSize;
        if (isRepeated(elementArray, currentElement, arraySize)) {
            return false;
        }
    }

    return true;
}

int main() {
    // Unit Tests
    int intArrayOne[] = {1, 2, 3, 4, 5};
    if (areElementsInGenericArrayUnique(
            intArrayOne, 5, sizeof(int), isIntRepeated)) {
        printf("Elements in intArrayOne are unique\n");
    } else {
        printf("Elements in intArrayOne are not unique\n");
    }

    int intArrayTwo[] = {1, 5, 3, 4, 5};
    if (areElementsInGenericArrayUnique(
            intArrayTwo, 5, sizeof(int), isIntRepeated)) {
        printf("Elements in intArrayTwo are unique\n");
    } else {
        printf("Elements in intArrayTwo are not unique\n");
    }

    int longArrayOne[] = {1, 2, 3, 4, 5};
    if (areElementsInGenericArrayUnique(
            longArrayOne, 5, sizeof(long), isLongRepeated)) {
        printf("Elements in longArrayOne are unique\n");
    } else {
        printf("Elements in longArrayOne are not unique\n");
    }

    int longArrayTwo[] = {1, 2, 3, 4, 5};
    if (areElementsInGenericArrayUnique(
            longArrayTwo, 5, sizeof(long), isLongRepeated)) {
        printf("Elements in longArrayTwo are unique\n");
    } else {
        printf("Elements in longArrayTwo are not unique\n");
    }

    float floatArrayOne[] = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f};
    if (areElementsInGenericArrayUnique(
            floatArrayOne, 5, sizeof(float), isFloatRepeated)) {
        printf("Elements in floatArrayOne are unique\n");
    } else {
        printf("Elements in floatArrayOne are not unique\n");
    }

    float floatArrayTwo[] = {1.0f, 5.5f, 3.0f, 5.5f, 5.0f};
    if (areElementsInGenericArrayUnique(
            floatArrayTwo, 5, sizeof(float), isFloatRepeated)) {
        printf("Elements in floatArrayTwo are unique\n");
    } else {
        printf("Elements in floatArrayTwo are not unique\n");
    }

    char stringOne[] = "James Liu";
    if (areElementsInGenericArrayUnique(
            stringOne, strlen(stringOne), sizeof(char), isCharRepeated)) {
        printf("Elements in stringOne are unique\n");
    } else {
        printf("Elements in stringOne are not unique\n");
    }

    char stringTwo[] = "Hello World!";
    if (areElementsInGenericArrayUnique(
            stringTwo, strlen(stringTwo), sizeof(char), isCharRepeated)) {
        printf("Elements in stringTwo are unique\n");
    } else {
        printf("Elements in stringTwo are not unique\n");
    }

    return 0;
}