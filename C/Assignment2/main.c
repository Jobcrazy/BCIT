#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define NUMBER_OF_ARGUMENTS 3
#define ARGUMENT_FILE_PATH 1
#define ARGUMENT_WORD_TO_SEARCH 2

#define ERROR_SUCCESS 0
#define ERROR_ARGUMENT_NUMBER 1
#define ERROR_OPEN_FILE 2
#define ERROR_POINTER 3
#define ERROR_EMPTY_STRING 4
#define ERROR_NOT_FOUND 5
#define ERROR_EOF 6

#define DEFAULT_LINE_NUMBER 1
#define DEFAULT_CHAR_NUMBER 1

#define TEST_FILE_PATH "test.txt"

#define CHAR_NEW_LINE '\n'
#define CHAR_SPACE ' '
#define CHAR_TILDE '~'
#define STR_END '\0'

#define MAX_WORD_LENGTH 80

bool isValidChar(char singleChar) {
    return CHAR_SPACE < singleChar && CHAR_TILDE >= singleChar;
}

void resetString(char *string, size_t size) {
    for (int index = 0; index < size; ++index) {
        string[index] = STR_END;
    }
}

int readNextWord(FILE *file,
                 char *word,
                 int lineNumber,
                 int *nextLineNumber,
                 int *spaceBeforeWord,
                 int *spaceAfterWord) {
    if (!file || !word || !lineNumber || !nextLineNumber ||
        !spaceBeforeWord || !spaceAfterWord) {
        return ERROR_POINTER;
    }

    *spaceBeforeWord = *spaceAfterWord = 0;
    resetString(word, MAX_WORD_LENGTH);

    char currentCharacter = STR_END;
    int wordLength = 0;
    while (EOF != (currentCharacter = (char) fgetc(file))) {
        if (isValidChar(currentCharacter)) {
            word[wordLength] = currentCharacter;
            wordLength++;
        } else if (CHAR_NEW_LINE == currentCharacter && wordLength) {
            (*spaceAfterWord)++;
            *nextLineNumber = lineNumber + 1;
            return ERROR_SUCCESS;
        } else if (wordLength) {
            (*spaceAfterWord)++;
            return ERROR_SUCCESS;
        } else {
            (*spaceBeforeWord)++;
        }
    }

    return ERROR_EOF;
}

int byteCompare(char *string, char *subString, int size) {
    if (!string || !subString) {
        return ERROR_POINTER;
    }

    if (strlen(string) < size || strlen(subString) < size) {
        return ERROR_NOT_FOUND;
    }

    for (int index = 0; index < size; ++index) {
        if (string[index] != subString[index]) {
            return ERROR_NOT_FOUND;
        }
    }

    return ERROR_SUCCESS;
}

int searchSubstring(char *string, char *subString, int *positionInWord) {
    if (!string || !subString) {
        return ERROR_POINTER;
    }

    int subStrLength = (int) strlen(subString);
    for (int index = 0; index < strlen(string); ++index, string++) {
        if (ERROR_SUCCESS == byteCompare(string, subString, subStrLength)) {
            *positionInWord = index;
            return ERROR_SUCCESS;
        }
    }

    return ERROR_NOT_FOUND;
}

int searchWord(FILE *textFile,
               char *word,
               int *lineNumber,
               int *charNumber) {
    if (!textFile || !word || !lineNumber || !charNumber) {
        return ERROR_POINTER;
    }

    char currentWord[MAX_WORD_LENGTH] = {0};
    int errorCode = ERROR_SUCCESS;
    int nextLineNumber = *lineNumber;
    int spaceBeforeWord = 0;
    int spaceAfterWord = 0;

    while (ERROR_EOF != errorCode) {
        errorCode = readNextWord(textFile, currentWord,
                                 *lineNumber, &nextLineNumber,
                                 &spaceBeforeWord, &spaceAfterWord);

        int positionInWord = 0;
        if (ERROR_SUCCESS == searchSubstring(currentWord,
                                             word, &positionInWord)) {
            *charNumber += spaceBeforeWord + positionInWord;
            return ERROR_SUCCESS;
        }

        *charNumber += spaceBeforeWord +
                       (int) strlen(currentWord) + spaceAfterWord;
        if (*lineNumber != nextLineNumber) {
            *lineNumber = nextLineNumber;
            *charNumber = DEFAULT_CHAR_NUMBER;
        }
    }

    return ERROR_NOT_FOUND;
}

int searchWordInFile(char *filePath,
                     char *word,
                     int *lineNumber,
                     int *charNumber) {
    int errorCode = ERROR_OPEN_FILE;

    if (!filePath || !word || !lineNumber || !charNumber) {
        return ERROR_POINTER;
    }

    *lineNumber = DEFAULT_LINE_NUMBER, *charNumber = DEFAULT_CHAR_NUMBER;

    if (!strlen(word)) {
        return ERROR_EMPTY_STRING;
    }

    FILE *txtFile = fopen(filePath, "r");
    if (!txtFile) {
        return errorCode;
    }

    errorCode = searchWord(txtFile, word, lineNumber, charNumber);
    fclose(txtFile);
    txtFile = NULL;

    return errorCode;
}

void printError(int errorCode,
                unsigned int lineNumber,
                unsigned int charNumber) {
    if (ERROR_SUCCESS == errorCode) {
        printf("Line: %u, character: %u\n\n", lineNumber, charNumber);
    } else if (ERROR_POINTER == errorCode) {
        printf("Error: Invalid Pointer.\n\n");
    } else if (ERROR_OPEN_FILE == errorCode) {
        printf("Error: Open File Error.\n\n");
    } else if (ERROR_EMPTY_STRING == errorCode) {
        printf("Error: String is Empty.\n\n");
    } else if (ERROR_ARGUMENT_NUMBER == errorCode) {
        printf("Invalid argument number!\n\n");
    } else if (ERROR_NOT_FOUND == errorCode) {
        printf("Not found!\n\n");
    } else {
        printf("Error: Unknown Error.\n\n");
    }
}

bool createTestFile() {
    FILE *testFile = fopen(TEST_FILE_PATH, "w");
    if (!testFile) {
        return false;
    }
    fprintf(testFile,
            "Hi everyone. This is a test file that I created for "
            "assignment 2.\n"
            "This is the beginning of the second line in the file.\n"
            "I am getting tired of typing!!\n"
            "This should be the last line.");
    fclose(testFile);
    return true;
}

int main(int argc, char **argv) {
    int lineNumber = DEFAULT_LINE_NUMBER,
            charNumber = DEFAULT_CHAR_NUMBER;
    int errorCode = ERROR_SUCCESS;

    if (NUMBER_OF_ARGUMENTS == argc) {
        errorCode = searchWordInFile(argv[ARGUMENT_FILE_PATH],
                                     argv[ARGUMENT_WORD_TO_SEARCH],
                                     &lineNumber, &charNumber);
        printError(errorCode, lineNumber, charNumber);
        return errorCode;
    }

    // If number of arguments is invalid, then perform unit tests
    printf("Perform unit tests...\n\n");

    if (!createTestFile()) {
        printf("Failed creating test file.\n");
        return ERROR_OPEN_FILE;
    }

    printf("Search '%s' in %s...\n", "assignment", TEST_FILE_PATH);
    errorCode = searchWordInFile(TEST_FILE_PATH,
                                 "assignment",
                                 &lineNumber, &charNumber);
    printError(errorCode, lineNumber, charNumber);

    printf("Search '%s' in %s...\n", "second", TEST_FILE_PATH);
    errorCode = searchWordInFile(TEST_FILE_PATH,
                                 "second",
                                 &lineNumber, &charNumber);
    printError(errorCode, lineNumber, charNumber);

    printf("Search '%s' in %s...\n", "third", TEST_FILE_PATH);
    errorCode = searchWordInFile(TEST_FILE_PATH,
                                 "third",
                                 &lineNumber, &charNumber);
    printError(errorCode, lineNumber, charNumber);

    return 0;
}
