#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define ARG_NUMBER 2
#define CHAR_END '\0'
#define CHAR_STAR '*'
#define CHAR_SPACE ' '
#define CHAR_MIN_LOWER_CASE 'a'
#define CHAR_MAX_LOWER_CASE 'z'
#define CHAR_MIN_UPPER_CASE 'A'
#define ARG_STRING_INDEX 1

#define ERROR_SUCCESS 0
#define ERROR_ALLOCATE_MEMORY 1
#define ERROR_INVALID_PARAMS_COUNT 2

char *stringToUpper(char *string) {
    char *tmpString = string;
    while (*tmpString) {
        if (CHAR_MIN_LOWER_CASE <= *tmpString &&
            CHAR_MAX_LOWER_CASE >= *tmpString) {
            *tmpString -= CHAR_MIN_LOWER_CASE - CHAR_MIN_UPPER_CASE;
        }
        ++tmpString;
    }
    return string;
}

void appendCharacter(
        char *concatenatedString,
        char characterToAppend,
        size_t *currentLength,
        bool needRepeat
) {
    if (CHAR_SPACE == characterToAppend) {
        return;
    }

    concatenatedString[*currentLength] = characterToAppend;
    (*currentLength)++;

    if (needRepeat) {
        appendCharacter(
                concatenatedString, characterToAppend,
                currentLength, false);
    }
}

size_t computeSizeNeeded(char *string) {
    int count = 0;
    while (*string) {
        ++count;
        if (CHAR_STAR == *string) {
            ++count;
        } else if (CHAR_SPACE == *string) {
            --count;
        }
        ++string;
    }
    return count + 1;
}

char *concatStrings(char *words) {
    size_t currentLength = 0;

    char *concatenatedString = (char *) malloc(computeSizeNeeded(words));
    if (!concatenatedString) {
        return NULL;
    }

    for (int charIndex = 0; charIndex < strlen(words); ++charIndex) {
        appendCharacter(concatenatedString, words[charIndex],
                        &currentLength, CHAR_STAR == words[charIndex]);
    }

    concatenatedString[currentLength] = CHAR_END;
    return stringToUpper(concatenatedString);
}

int validateArgNumber(int argc) {
    if (ARG_NUMBER != argc) {
        return ERROR_INVALID_PARAMS_COUNT;
    }
    return ERROR_SUCCESS;
}

int main(int argc, char **argv) {
    if (ERROR_INVALID_PARAMS_COUNT != validateArgNumber(argc)) {
        char *concatenatedString = concatStrings(argv[ARG_STRING_INDEX]);
        if (!concatenatedString) {
            return ERROR_ALLOCATE_MEMORY;
        }

        printf("%s\n", concatenatedString);
        free(concatenatedString);
        return ERROR_SUCCESS;
    }

    //Unit tests
    char *concatenatedString = concatStrings("");
    if (concatenatedString) {
        printf("%s\n", concatenatedString);
        free(concatenatedString);
    }

    concatenatedString = concatStrings("Seyed Javidi");
    if (concatenatedString) {
        printf("%s\n", concatenatedString);
        free(concatenatedString);
    }

    concatenatedString = concatStrings("I * c");
    if (concatenatedString) {
        printf("%s\n", concatenatedString);
        free(concatenatedString);
    }

    concatenatedString = concatStrings("***");
    if (concatenatedString) {
        printf("%s\n", concatenatedString);
        free(concatenatedString);
    }
}
