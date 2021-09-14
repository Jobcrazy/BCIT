#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAX_WORD_LENGTH 20
#define MAX_NUMBER_OF_WORDS 100

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

int tokenize(char *paragraph,
             char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
             int *lengthOfLongestWord) {
    int currentWordIndex = 0;
    char *tmpStringPointer = paragraph;
    if (!paragraph || !tokens) return currentWordIndex;

    while (true) {
        if (currentWordIndex >= MAX_NUMBER_OF_WORDS) {
            return 0;
        }

        if (STR_END == *tmpStringPointer) {
            return currentWordIndex;
        }

        char *currentToken = moveToBeginningOfNextToken(tmpStringPointer);
        if (!currentToken) {
            return currentWordIndex;
        }

        int TokenSize = getCurrentTokenSize(currentToken);
        if (!TokenSize) {
            return currentWordIndex;
        }

        *lengthOfLongestWord = *lengthOfLongestWord < TokenSize ?
                               TokenSize : *lengthOfLongestWord;

        strncpy(tokens[currentWordIndex], currentToken, TokenSize);
        tokens[currentWordIndex][TokenSize] = STR_END;

        // move forward
        tmpStringPointer += (currentToken + TokenSize - tmpStringPointer);
        currentWordIndex++;
    }
}

int getNumberOfWordsForNextLine(
        char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
        int numberOfWordsProcessedSoFar,
        int totalNumberOfWords,
        int lineLength) {
    int numberOfWordsForNextLine = 0;
    int currentWordIndex = numberOfWordsProcessedSoFar;
    unsigned long currentTotalLength = 0;

    if (!tokens) {
        return 0;
    }

    while (true) {
        if (currentWordIndex == totalNumberOfWords) {
            // all words has been processed
            return numberOfWordsForNextLine;
        }

        currentTotalLength += strlen(tokens[currentWordIndex]);
        if (currentTotalLength > lineLength) {
            // a single line has been processed
            return numberOfWordsForNextLine;
        } else if (currentTotalLength + 1 != lineLength) {
            // if it isn't the last word in a line
            currentTotalLength++;
        }

        // move forward
        numberOfWordsForNextLine++;
        currentWordIndex++;
    }
}

void printWordAndSpaces(char word[MAX_WORD_LENGTH], int numberOfSpaces) {
    if (!word) return;

    printf("%s", word);

    for (int index = 0; index < numberOfSpaces; index++) {
        printf("%c", CHAR_SPACE);
    }
}

int computeLengthOfWords(
        char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
        int numberOfWordsProcessedSoFar,
        int numberOfWordsOnNextLine
) {
    int wordsLength = 0;

    if (!tokens) {
        return 0;
    }

    for (int wordIndex = 0; wordIndex < numberOfWordsOnNextLine; ++wordIndex) {
        wordsLength +=
                (int) strlen(tokens[numberOfWordsProcessedSoFar + wordIndex]);
    }

    return wordsLength;
}

void computeNumberOfSpaces(
        int numberOfWordsOnNextLine,
        int lineLength,
        int wordsLength,
        int *numbersOfSpaces) {
    if (!numbersOfSpaces) {
        return;
    }

    int totalSpaces = lineLength - wordsLength;

    if (1 == numberOfWordsOnNextLine) {
        numbersOfSpaces[0] = totalSpaces;
        return;
    }

    int numberOfBlanks = numberOfWordsOnNextLine - 1;
    while (totalSpaces) {
        for (int blankIndex = 0;
             blankIndex < numberOfBlanks && totalSpaces; ++blankIndex) {
            numbersOfSpaces[blankIndex]++;
            totalSpaces--;
        }
    }
}

void printLine(
        char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
        int numberOfWordsProcessedSoFar,
        int numberOfWordsOnNextLine,
        int numbersOfSpaces[MAX_NUMBER_OF_WORDS]) {
    if (!tokens || !numbersOfSpaces) {
        return;
    }

    for (int wordIndex = numberOfWordsProcessedSoFar, spaceIndex = 0;
         wordIndex < numberOfWordsProcessedSoFar + numberOfWordsOnNextLine &&
         spaceIndex < numberOfWordsOnNextLine; ++wordIndex, spaceIndex++) {
        printWordAndSpaces(tokens[wordIndex], numbersOfSpaces[spaceIndex]);
    }

    printf("\n");
}

void formatAndPrintCurrentLine(
        char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
        int numberOfWordsProcessedSoFar,
        int numberOfWordsOnNextLine,
        int lineLength) {
    int numbersOfSpaces[MAX_NUMBER_OF_WORDS] = {0};

    int wordsLength = computeLengthOfWords(tokens, numberOfWordsProcessedSoFar,
                                           numberOfWordsOnNextLine);

    computeNumberOfSpaces(numberOfWordsOnNextLine, lineLength,
                          wordsLength, numbersOfSpaces);

    printLine(tokens, numberOfWordsProcessedSoFar, numberOfWordsOnNextLine,
              numbersOfSpaces);
}

void formatAndPrintWords(
        char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH],
        int totalNumberOfWords,
        int lineLength) {
    int numberOfWordsProcessedSoFar = 0;

    if (!tokens || !totalNumberOfWords || !lineLength) {
        return;
    }

    while (true) {
        int numberOfWordsForNextLine = getNumberOfWordsForNextLine(
                tokens, numberOfWordsProcessedSoFar,
                totalNumberOfWords, lineLength);

        if (!numberOfWordsForNextLine) {
            return;
        }

        formatAndPrintCurrentLine(tokens, numberOfWordsProcessedSoFar,
                                  numberOfWordsForNextLine, lineLength);

        numberOfWordsProcessedSoFar += numberOfWordsForNextLine;
    }
}

void formatAndPrintParagraph(char *paragraph, int lineLength) {
    int lengthOfLongestWord = 0;
    char tokens[MAX_NUMBER_OF_WORDS][MAX_WORD_LENGTH] = {0};

    int totalNumberOfWords = tokenize(
            paragraph, tokens, &lengthOfLongestWord);
    if (!totalNumberOfWords) {
        printf("Error tokenizing. Please check the arguments.\n\n");
        return;
    }

    if (lengthOfLongestWord > lineLength) {
        printf("lineLength(%d) is too short for: %s\n\n",
               lineLength, paragraph);
        return;
    }

    formatAndPrintWords(tokens, totalNumberOfWords, lineLength);
    printf("\n");
}


int main() {
    // not a valid string pointer
    formatAndPrintParagraph(0, 10);

    // single character with a lineLength of 10
    char *singleCharacter = "a";
    formatAndPrintParagraph(singleCharacter, 10);

    // lineLength of 0
    formatAndPrintParagraph(singleCharacter, 0);

    // special characters
    char *specialCharacters = "\r\n \5\6";
    formatAndPrintParagraph(specialCharacters, 10);

    // two short word with long lineLength
    char *twoWords = "Nice People";
    formatAndPrintParagraph(twoWords, 30);

    // long word with short lineLength
    char *longWord = "There is a too long word: congratulations.";
    formatAndPrintParagraph(longWord, 7);

    // a paragraph with a length of lineLength
    char *paragraphWithLineLength = "It's my only son!";
    formatAndPrintParagraph(paragraphWithLineLength,
                            (int) strlen(paragraphWithLineLength));

    // a paragraph with a length less than lineLength
    formatAndPrintParagraph(paragraphWithLineLength,
                            (int) strlen(paragraphWithLineLength) - 1);

    // a long paragraph with a lineLength of 25
    char *longParagraph =
            "Hi everyone. This is the 2nd assignment. "\
            "Please make sure you start early as this "\
            "is going to take some time!";
    formatAndPrintParagraph(longParagraph, 25);

    //a long paragraph with a lineLength of 40
    formatAndPrintParagraph(longParagraph, 40);

    // a long paragraph with a lot of space
    char *longParagraphWithSpace =
            "          Assignment   1   of         C: "\
            "Hi everyone. This is the 2nd assignment. "\
            "Please make sure you start early as this "\
            "is going to take some time!";
    formatAndPrintParagraph(longParagraphWithSpace, 40);

    char *paragraphWithDigits =
            "My phone NO. is (778)251-9108. I love dogs and cats! "\
            "I have 11 cats and 8 dogs. Everyday I take all of them "\
            "to hang out in the 68TH Avenue. ^_^ :-) @_@";
    formatAndPrintParagraph(paragraphWithDigits, 25);

    return 0;
}
