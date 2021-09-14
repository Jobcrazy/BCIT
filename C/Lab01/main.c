#include <stdio.h>
#include <string.h>

int main() {
    char firstString[10] = "good";
    char *secondString = "book";
    char thirdString[10];
    strncpy(thirdString, secondString, 3);
    puts(thirdString);
    puts(strncat(firstString, secondString, 2));
    return 0;
}
