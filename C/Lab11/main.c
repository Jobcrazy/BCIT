#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <memory.h>

#define ERROR_ALLOCATE_MEMORY 1
#define SIZE_OF_TEST_ARRAY 5

void genericSwap(
        void *dataOne,
        void *dataTwo,
        const size_t sizeOfData) {
    void *tempData = malloc(sizeOfData);
    if (!tempData) {
        perror("Failed allocate memory");
        exit(ERROR_ALLOCATE_MEMORY);
    }

    memcpy(tempData, dataOne, sizeOfData);
    memcpy(dataOne, dataTwo, sizeOfData);
    memcpy(dataTwo, tempData, sizeOfData);

    free(tempData);
}

void genericBubbleSort(
        void *array,
        const size_t arraySize,
        const size_t dataSize,
        bool (*compareFunction)(void *, void *)) {
    for (int outerIndex = 0; outerIndex < arraySize; ++outerIndex) {
        for (int innerIndex = outerIndex + 1;
             innerIndex < arraySize;
             ++innerIndex) {
            void *dataOne = (char *) array + dataSize * outerIndex;
            void *dataTwo = (char *) array + dataSize * innerIndex;
            if (compareFunction(dataOne, dataTwo)) {
                genericSwap(dataOne, dataTwo, dataSize);
            }
        }
    }
}

bool intCompare(
        const int *intOne,
        const int *intTwo) {
    return *intOne > *intTwo;
}

bool charCompare(
        const char *charOne,
        const char *charTwo) {
    return *charOne > *charTwo;
}

bool floatCompare(
        const float *floatOne,
        const float *floatTwo) {
    return *floatOne > *floatTwo;
}

bool stringCompare(
        const void *stringOne,
        const void *stringTwo) {
    char * firstValue = *(char **)stringOne;
    char * secondValue = *(char **)stringTwo;
    return strcmp(firstValue, secondValue) > 0;
}

int main() {
    // int test
    int intArray[SIZE_OF_TEST_ARRAY] = {10, 1, -2, 5, 7};

    genericBubbleSort(intArray, SIZE_OF_TEST_ARRAY, sizeof(int),
                      (bool (*)(void *, void *)) intCompare);

    for (int i = 0; i < SIZE_OF_TEST_ARRAY; i++) {
        printf("intArray[%d] = %d\n", i, intArray[i]);
    }

    // float Test
    float floatArray[SIZE_OF_TEST_ARRAY] = {10.5f, 1.3f, -2.2f, 5.7f, 7.8f};

    genericBubbleSort(floatArray, SIZE_OF_TEST_ARRAY, sizeof(float),
                      (bool (*)(void *, void *)) floatCompare);

    for (int i = 0; i < SIZE_OF_TEST_ARRAY; i++) {
        printf("floatArray[%d] = %.2f\n", i, floatArray[i]);
    }

    // char test
    char charArray[SIZE_OF_TEST_ARRAY] = {'d', 'Z', 'H', 'x', 'L'};
    genericBubbleSort(charArray, SIZE_OF_TEST_ARRAY, sizeof(char),
                      (bool (*)(void *, void *)) charCompare);
    for (int i = 0; i < SIZE_OF_TEST_ARRAY; i++) {
        printf("charArray[%d] = %c\n", i, charArray[i]);
    }

    // string test
    char *stringArray[SIZE_OF_TEST_ARRAY] = {
            "Hello",
            "World",
            "James",
            "Liu",
            "LiuA"
    };
    genericBubbleSort(stringArray, SIZE_OF_TEST_ARRAY, sizeof(unsigned long),
                      (bool (*)(void *, void *)) stringCompare);
    for (int i = 0; i < SIZE_OF_TEST_ARRAY; i++) {
        printf("stringArray[%d] = %s\n", i, stringArray[i]);
    }

    return 0;
}
