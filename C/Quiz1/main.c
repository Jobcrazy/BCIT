#include <stdio.h>

#define MAX_SENTENCE_LENGTH 80

void doubleA(char *input, char *output) {
    if (!input || !output) {
        return;
    }

    *output = *input;

    if (!*input) {
        return;
    }

    if ('A' == *input) {
        *++output = *input;
    }

    doubleA(++input, ++output);
}

int main() {
    char result[MAX_SENTENCE_LENGTH] = {0};

    char *hiAdam = "Hi Adam, How Are You?";
    doubleA(hiAdam, result);
    printf("Input: %s\nOutput: %s\n", hiAdam, result);

    char *fiveAs = "AAAAA";
    doubleA(fiveAs, result);
    printf("Input: %s\nOutput: %s\n", fiveAs, result);

    char *aAron = "Aaron is our AD";
    doubleA(aAron, result);
    printf("Input: %s\nOutput: %s\n", aAron, result);

    return 0;
}