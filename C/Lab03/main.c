#include <stdio.h>
#include <stdbool.h>

#define CHAR_SPACE ' '
#define CHAR_TILDE '~'
#define STR_END '\0'

bool isValidChar(char singleChar) {
    return CHAR_SPACE < singleChar && CHAR_TILDE >= singleChar;
}

char *moveToBeginningOfNextToken(char *inputString) {
    char *tmpStringPointer = 0;
    if (!inputString) {
        return tmpStringPointer;
    }

    tmpStringPointer = inputString;
    while (true) {
        if (!isValidChar(*tmpStringPointer) && STR_END != *tmpStringPointer) {
            tmpStringPointer++;
        } else {
            return tmpStringPointer;
        }
    }
}

int getCurrentTokenSize(char *inputString) {
    int tokenSize = 0;
    char *tmpStringPointer = inputString;
    if (!inputString) {
        return tokenSize;
    }

    while (isValidChar(*tmpStringPointer)) {
        tokenSize++;
        tmpStringPointer++;
    }

    return tokenSize;
}

void printToken(char *tokenPointer, int tokenSize) {
    for (int index = 0; index < tokenSize; ++index) {
        printf("%c", tokenPointer[index]);
    }

    printf("\n");
}

void printTokens(char *inputString) {
    char *tokenPointer = NULL;
    int tokenSize = 0;

    while (true) {
        tokenPointer = moveToBeginningOfNextToken(inputString);
        tokenSize = getCurrentTokenSize(tokenPointer);

        if (!tokenSize) {
            return;
        }

        printToken(tokenPointer, tokenSize);

        inputString += (tokenPointer + tokenSize - inputString);
    }
}

int main() {
    printTokens("  Hi    there! How   is it      going?  ");
    return 0;
}
