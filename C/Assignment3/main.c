#include <stdio.h>
#include <stdbool.h>
#include <memory.h>
#include <stdlib.h>

#define NUMBER_OF_ARGUMENTS 2
#define ARGUMENT_FILE_PATH 1

#define ERROR_SUCCESS 0
#define ERROR_ARGUMENT_NUMBER 1
#define ERROR_OPEN_FILE 2
#define ERROR_POINTER 3
#define ERROR_ALLOCATE_MEMORY 4

#define TEST_FILE_PATH "test.txt"
#define MAX_NAME_LENGTH 80
#define INITIAL_STUDENT_SIZE 2
#define STUDENT_SIZE_EXTEND_TIME 2

#define MIN_PRINTED_GPA 3.9f

struct Student {
    char name[MAX_NAME_LENGTH];
    float GPA;
};

bool createTestFile() {
    FILE *testFile = fopen(TEST_FILE_PATH, "w");
    if (!testFile) {
        return false;
    }
    fprintf(testFile,
            "Mary\t4.0\r\n"
            "Jack\t2.45\r\n"
            "John\t3.9\r\n"
            "Jane\t3.8\r\n"
            "James\t4.1\r\n"
            "Mike\t3.125");
    fclose(testFile);
    return true;
}

void printError(int errorCode) {
    if (ERROR_SUCCESS == errorCode) {
        printf("\n\n");
    } else if (ERROR_POINTER == errorCode) {
        printf("Error: Invalid Pointer.\n\n");
    } else if (ERROR_OPEN_FILE == errorCode) {
        printf("Error: Open File Error.\n\n");
    } else if (ERROR_ARGUMENT_NUMBER == errorCode) {
        printf("Invalid argument number!\n\n");
    } else if (ERROR_ALLOCATE_MEMORY == errorCode) {
        printf("Error allocate memory!\n\n");
    } else {
        printf("Error: Unknown Error.\n\n");
    }
}

struct Student *resizeArrayIfNeeded(struct Student **students,
                                    const size_t *numberOfStudents,
                                    size_t *preAllocatedSize) {
    if (*numberOfStudents >= *preAllocatedSize) {
        *preAllocatedSize *= STUDENT_SIZE_EXTEND_TIME;
        struct Student *newStudents = (struct Student *) realloc(
                *students, *preAllocatedSize * sizeof(struct Student));
        if (NULL == newStudents) {
            free(*students);
        }
        return newStudents;
    }
    return *students;
}

int readAllStudents(FILE *scoreFile,
                    struct Student **students,
                    size_t *numberOfStudents,
                    size_t *preAllocatedSize) {
    if (!scoreFile || !numberOfStudents) {
        return ERROR_POINTER;
    }

    *numberOfStudents = 0;

    struct Student tmpStudent = {0};
    while (EOF != fscanf(
            scoreFile, "%s %f", tmpStudent.name, &(tmpStudent.GPA))) {
        *students = resizeArrayIfNeeded(
                students, numberOfStudents, preAllocatedSize);
        if (!*students) {
            return ERROR_ALLOCATE_MEMORY;
        }

        memcpy(&(*students)[*numberOfStudents],
               &tmpStudent, sizeof(struct Student));
        (*numberOfStudents)++;
    }

    return ERROR_SUCCESS;
}

void swapStudents(struct Student *studentA,
                  struct Student *studentB) {
    struct Student tmpStudent = {0};
    memcpy(&tmpStudent, studentA, sizeof(struct Student));
    memcpy(studentA, studentB, sizeof(struct Student));
    memcpy(studentB, &tmpStudent, sizeof(struct Student));
}

int sortStudents(struct Student *students, size_t size) {
    if (!students) {
        return ERROR_POINTER;
    }

    for (size_t index = 0; index < size - 1; ++index) {
        for (size_t nextIndex = index + 1; nextIndex < size - 1; ++nextIndex) {
            if (students[index].GPA < students[nextIndex].GPA) {
                swapStudents(&students[index], &students[nextIndex]);
            }
        }
    }

    return ERROR_SUCCESS;
}

int printResult(struct Student *student, size_t size, float minimumScore) {
    if (!student) {
        return ERROR_POINTER;
    }

    int totalPrinted = 0;

    for (size_t index = 0; index < size - 1; ++index) {
        if (minimumScore < student[index].GPA) {
            totalPrinted++;
            printf("%s\t%f\n", student[index].name, student[index].GPA);
        }
    }

    if (!totalPrinted) {
        printf("No student's GPA is greater than %f\n", MIN_PRINTED_GPA);
    }

    return ERROR_SUCCESS;
}

int cleanUp(struct Student *students, FILE *scoreFile, int errorCode) {
    if (students) free(students);
    if (scoreFile) fclose(scoreFile);
    return errorCode;
}

int printStudentsWithHighScores(char *filePath) {
    if (!filePath) {
        return ERROR_POINTER;
    }

    int errorCode = ERROR_OPEN_FILE;
    size_t numberOfStudents = 0;
    size_t preAllocatedSize = INITIAL_STUDENT_SIZE;
    struct Student *students = NULL;
    FILE *scoreFile = NULL;

    students = (struct Student *) malloc(
            preAllocatedSize * sizeof(struct Student));
    if (!students) {
        return cleanUp(students, scoreFile, ERROR_ALLOCATE_MEMORY);
    }

    scoreFile = fopen(filePath, "r");
    if (!scoreFile) {
        return cleanUp(students, scoreFile, errorCode);
    }

    errorCode = readAllStudents(scoreFile, &students,
                                &numberOfStudents, &preAllocatedSize);
    if (ERROR_SUCCESS != errorCode) {
        return cleanUp(students, scoreFile, errorCode);
    }

    errorCode = sortStudents(students, numberOfStudents);
    if (ERROR_SUCCESS != errorCode) {
        return cleanUp(students, scoreFile, errorCode);
    }

    errorCode = printResult(students, numberOfStudents, MIN_PRINTED_GPA);
    return cleanUp(students, scoreFile, errorCode);
}

int main(int argc, char **argv) {
    int errorCode = ERROR_SUCCESS;
    if (NUMBER_OF_ARGUMENTS == argc) {
        errorCode = printStudentsWithHighScores(argv[ARGUMENT_FILE_PATH]);
        printError(errorCode);
        return errorCode;
    }

    // Perform unit tests if the arguments number is invalid,
    printf("Perform unit tests...\n\n");

    if (!createTestFile()) {
        printf("Failed creating test file.\n");
        return ERROR_OPEN_FILE;
    }

    errorCode = printStudentsWithHighScores(TEST_FILE_PATH);
    printError(errorCode);
    return errorCode;
}