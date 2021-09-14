#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define STR_END '\0'
#define MIN_CHAR_REPEAT_TIMES 2
#define MAX_STRING_LENGTH 80
#define MIN_PRIME 2

bool isPrimary(int number) {
    if (MIN_PRIME > number) {
        return false;
    } else if (MIN_PRIME == number) {
        return true;
    }

    for (int index = MIN_PRIME; index < number; ++index) {
        if (0 == number % index) {
            return false;
        }
    }

    return true;
}

int findNextPrime(int startNumber) {
    if (isPrimary(startNumber + 1)) {
        return startNumber + 1;
    }
    return findNextPrime(startNumber + 1);
}

int findNthPrime(int position) {
    if (0 == position) {
        return 0;
    }

    return findNextPrime(findNthPrime(position - 1));
}

bool isCharacterRepeated(char *string, char character) {
    int counter = 0;
    while (*string) {
        if (character == *string) {
            counter++;
        }

        if (MIN_CHAR_REPEAT_TIMES <= counter) {
            return true;
        }

        string++;
    }
    return false;
}

char getFirstNonRepeatedCharacter(char *string) {
    char *originalString = string;
    while (*string) {
        if (!isCharacterRepeated(originalString, *string)) {
            return *string;
        }
        string++;
    }
    return STR_END;
}

unsigned int bitFun(unsigned int number) {
    const int turnOnMask = 5;
    const int turnOffMask = 16;
    return (number |= turnOnMask) & (~turnOffMask);
}

void replaceCharacter(char *input, char originalChar, char mappedCHar) {
    while (*input) {
        if (*input == originalChar) {
            *input = mappedCHar;
        }
        input++;
    }
}

void mapCharactersUsingFile(char *fileName, char *input, char *output) {
    FILE *fMap = fopen(fileName, "r");
    if (!fMap) {
        printf("Failed open map file.\n");
        return;
    }

    char originalString[MAX_STRING_LENGTH] = {0};
    strcpy(originalString, input);

    char originalChar[MAX_STRING_LENGTH] = {0};
    char mappedChar[MAX_STRING_LENGTH] = {0};

    while (EOF != fscanf(fMap, "%s%s", originalChar, mappedChar)) {
        replaceCharacter(originalString, originalChar[0], mappedChar[0]);
    }

    strcpy(output, originalString);

    fclose(fMap);
}


/* This function is to copy characters of given length from the string
 * 'input' to the string 'output', and if the length of 'output' is
 * less than the given 'length', then pad character 'sub' to the string
 * 'output' to until its length reaches the given length.
 *
 * The return value is how many characters have been padded.
 *
 * As a result, the function name could be padCharacter.
 *
 * Bug: it does not check the size of 'output', if the length of 'input'
 * or the given 'length' is greater than the size of 'output', then the
 * index output will be out of range. We have better to specify the length
 * of both 'input' and 'output' and check them to avoid this issue.
*/
size_t mystery(char *input, char *output, size_t length, char sub) {
    if (!length) {
        *output = 0;
        return 0;
    }
    char *beginning = input;
    *output = (char) ((*input) ? (*input++) : sub);
    size_t value = (beginning == input);
    return value + mystery(input, output + 1, length - 1, sub);
}

int main() {
    findNthPrime(2);
    printf("%dth prime is %d\n", 2, findNthPrime(2));
    printf("%dth prime is %d\n", 10, findNthPrime(10));
    printf("%dth prime is %d\n", 53, findNthPrime(53));
    printf("%dth prime is %d\n", 1000, findNthPrime(1000));

    // Tests for question 2
    printf("%c\n", getFirstNonRepeatedCharacter("Seyed"));
    printf("%c\n", getFirstNonRepeatedCharacter("An Apple"));
    printf("%c\n", getFirstNonRepeatedCharacter("Mississippi Map"));
    printf("%c\n", getFirstNonRepeatedCharacter("AA"));

    //Test for question 3
    printf("%u\n", bitFun(9));
    printf("%u\n", bitFun(0));
    printf("%u\n", bitFun(17));
    printf("%u\n", bitFun(31));

    //Test for question 4
    char output[MAX_STRING_LENGTH] = {0};

    char input[MAX_STRING_LENGTH] = "Seyed";
    mapCharactersUsingFile("mapFile.txt", input, output);
    printf("%s -- %s\n", input, output);

    strcpy(input, "Adam’s name.");
    mapCharactersUsingFile("mapFile.txt", input, output);
    printf("%s -- %s\n", input, output);

    strcpy(input, "Ascend.");
    mapCharactersUsingFile("mapFile.txt", input, output);
    printf("%s -- %s\n", input, output);

    //Bug test for question 5
    char test_input[6] = "Hello";
    char test_output[6] = "Hang";
    size_t size = mystery(test_input, test_output, 3, 'X');
    printf("%zu, %s, %s\n", size, test_input, test_output);

    char test_output_2[3] = {0};
    size = mystery(test_input, test_output_2, 12, 'X');
    printf("%zu, %s, %s\n", size, test_input, test_output_2);

    return 0;
}
