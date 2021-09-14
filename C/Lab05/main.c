#include <stdio.h>
#include <stdbool.h>

#define MAX_COLUMNS 10
#define TEST_FILE_PATH "Text_A01173804.txt"

int fromBinary(int *binaryArray, int size) {
    if (0 == size) {
        return 0;
    }

    int number = fromBinary(binaryArray, size - 1);
    return 2 * number + binaryArray[size - 1];
}

void readNextNumberFromFile(
        FILE *file,
        int binaryArray[MAX_COLUMNS],
        int numberOfColumns) {
    for (int index = 0; index < numberOfColumns; ++index) {
        fscanf(file, "%d", &binaryArray[index]);
    }
}

void processFile(char *fileName) {
    int numberOfRows = 0, numberOfColumns = 0, tmpResult = 0;
    int binaryArray[MAX_COLUMNS] = {0};

    FILE *fp = fopen(fileName, "r");
    if (!fp) {
        printf("Error open file!\n");
        return;
    }

    fscanf(fp, "%d %d", &numberOfRows, &numberOfColumns);

    for (int rowIndex = 0; rowIndex < numberOfRows; ++rowIndex) {
        readNextNumberFromFile(fp, binaryArray, numberOfColumns);
        printf("%d\n", fromBinary(binaryArray, numberOfColumns));
    }

    fclose(fp);
}

bool createTestFile(char *filePath, char *testString) {
    FILE *fp = fopen(filePath, "w");
    if (!fp) {
        return false;
    }
    fprintf(fp, "%s", testString);
    fclose(fp);
    return true;
}

int main() {
    /*
     * Unit Test Started
     * The code below will write a test string in a file
     * from which data will be loaded, and then convert
    */
    char *testString = "3 4\n"
                       "1 0 1 1\n"
                       "0 0 0 1\n"
                       "1 1 1 1";
    if (createTestFile(TEST_FILE_PATH, testString)) {
        printf("Unit testing...\n");
        printf("%s\n", testString);
        processFile(TEST_FILE_PATH);
        printf("Unit test finished!\n\n");
    } else {
        printf("Failed to create the test file!\n\n");
    }
    // Unit Test Finished

    return 0;
}
