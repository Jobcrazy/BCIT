#include <stdio.h>
#include <string.h>
#include <stdio.h>

#define MAX_SIZE 100
#define NULL_CHARACTER '\0'

// This is a function that copy even indexes of characters of  from *input to *output,
// but it has a problem if the input doesn't meet the null character, it will copy garbage value in the memory.
void copyEvenIndexes(char *input, char *output) {
    if (*input == NULL_CHARACTER || *(input+1) == NULL_CHARACTER) {
        *output = *input;
        *(output+1) = '\0';
        return;
    }
    *output = *input;
    copyEvenIndexes(input + 2, output + 1);
}

int main() {
    char *input = "Hello";
    // char *output = ""; // <-- Bug here, should not be constant
    char output[10] = ""; // <-- Fixed here

    copyEvenIndexes(input, output);

    // test print
    printf("%s : %s", input, output);
}
