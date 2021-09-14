#include <stdio.h>
#include <stdbool.h>

// Part 1
void printRow(int row) {
    int max_columns = 10;
    for (int column = 1; column <= max_columns; column++) {
        printf("%4d", row * column);
    }
}

void printMultiplicationTable() {
    int max_rows = 10;
    for (int row = 1; row <= max_rows; row++) {
        printRow(row);
        printf("\n");
    }
}

/*
// Another solution for Part 1
void printMultiplicationTable() {
    int max_rows = 10, max_columns = 10;
    for (int rowNum = 1; rowNum <= max_rows; rowNum++) {
        for (int columnNum = 1; columnNum <= max_columns; columnNum++) {
            printf("%4d", rowNum * columnNum);
        }
        printf("\n");
    }
}
*/

// Part 2
bool isPalindrome(int elements[], int size) {
    for (int indexLeft = 0, indexRight = size - 1;
         indexLeft < indexRight; indexLeft++, indexRight--) {
        if (elements[indexLeft] != elements[indexRight]) {
            return false;
        }
    }
    return true;
}

int main() {
    // Unit test for Part 1
    printMultiplicationTable();

    // Unit tests for Part 2
    int elementsTestOne[] = {1, 2, 3, 2, 1};
    if (isPalindrome(elementsTestOne, 5)) {
        printf("{1, 2, 3, 2, 1} is Palindrome.\n");
    } else {
        printf("{1, 2, 3, 2, 1} is not Palindrome.\n");
    }

    int elementsTestTwo[] = {1, 2, 3, 3, 2, 1};
    if (isPalindrome(elementsTestTwo, 6)) {
        printf("{1, 2, 3, 3, 2, 1} is Palindrome.\n");
    } else {
        printf("{1, 2, 3, 3, 2, 1} is not Palindrome.\n");
    }

    int elementsTestThree[] = {1, 2, 3, 4, 2, 1};
    if (isPalindrome(elementsTestThree, 6)) {
        printf("{1, 2, 3, 4, 2, 1} is Palindrome.\n");
    } else {
        printf("{1, 2, 3, 4, 2, 1} is not Palindrome.\n");
    }

    return 0;
}