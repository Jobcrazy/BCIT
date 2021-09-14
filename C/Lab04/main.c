#include <stdio.h>
#include <string.h>

#define MIN_STRING_LENGTH 2
#define NO_REPEATED_FOUND -1

int countMinDistance(char *inputString, char *repeatedChar) {
    int minDistance = NO_REPEATED_FOUND;
    int currentRepeatedDistance = NO_REPEATED_FOUND;
    char *tempStringPointer = inputString + 1;

    *repeatedChar = 0;

    if (!inputString ||
        strlen(inputString) < MIN_STRING_LENGTH ||
        !repeatedChar) {
        return minDistance;
    }

    while (*inputString) {
        while (*tempStringPointer != *inputString && *tempStringPointer) {
            tempStringPointer++;
        }
        if (*tempStringPointer == *inputString) {
            currentRepeatedDistance = (int) (tempStringPointer - inputString - 1);
            if (NO_REPEATED_FOUND == minDistance || minDistance > currentRepeatedDistance) {
                minDistance = currentRepeatedDistance;
                *repeatedChar = *tempStringPointer;
            }
        }
        inputString++;
        tempStringPointer = inputString + 1;
    }

    return minDistance;
}

void findMinDistance(char *inputString) {
    char repeatedChar = 0;
    int minDistance = countMinDistance(inputString, &repeatedChar);

    if (NO_REPEATED_FOUND == minDistance) {
        printf("No repeats found!\n");
    }else{
        printf("Repeated char = %c, min distance = %d\n", repeatedChar, minDistance);
    }
}

int main() {
    findMinDistance("Hi there, how is it going?");
    findMinDistance("Hello there, how is it going?");
    findMinDistance("123");
    findMinDistance("Two space :)");
    findMinDistance("Two. .period");
    return 0;
}
