#include <stdio.h>

#define NUMBER_OF_ARGUMENTS 3
#define ARGUMENT_SOURCE_FILE_PATH 1
#define ARGUMENT_DESTINED_FILE_PATH 2

#define ERROR_SUCCESS 0
#define ERROR_OPEN_SOURCE_FILE_ERROR 1
#define ERROR_OPEN_DESTINED_FILE_ERROR 2
#define ERROR_ARGUMENTS_NUMBER 3

#define BLOCK_COUNT 1

int copyFile(char *sourceFile, char *destinedFile) {
    FILE *fSourceFile = fopen(sourceFile, "r");
    if (!fSourceFile) {
        return ERROR_OPEN_SOURCE_FILE_ERROR;
    }

    FILE *fDestinedFile = fopen(destinedFile, "w+");
    if (!fDestinedFile) {
        fclose(fSourceFile);
        return ERROR_OPEN_DESTINED_FILE_ERROR;
    }

    char tmpByte = 0;
    while (BLOCK_COUNT == fread(
            &tmpByte, sizeof(tmpByte), BLOCK_COUNT, fSourceFile)) {
        fwrite(&tmpByte, sizeof(tmpByte), BLOCK_COUNT, fDestinedFile);
    }

    fclose(fSourceFile);
    fclose(fDestinedFile);

    return ERROR_SUCCESS;
}

int main(int argc, char **argv) {
    if (NUMBER_OF_ARGUMENTS != argc) {
        printf("Invalid argument number.\n");
        return ERROR_ARGUMENTS_NUMBER;
    }

    int result = copyFile(argv[ARGUMENT_SOURCE_FILE_PATH],
                          argv[ARGUMENT_DESTINED_FILE_PATH]);

    if (ERROR_SUCCESS == result) {
        printf("Copy file succeed.\n");
    } else if (ERROR_OPEN_SOURCE_FILE_ERROR == result) {
        printf("Error opening source file.\n");
    } else if (ERROR_OPEN_DESTINED_FILE_ERROR == result) {
        printf("Error opening destined file.\n");
    }

    return result;
}
