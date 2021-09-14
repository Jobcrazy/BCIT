#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define ERROR_ALLOCATE_MEMORY 1
#define SPACE_TIMES 4
#define TEST_FILE_PATH "test.txt"
#define CHAR_LF '\n'
#define CHAR_CR '\r'

// Question 1
typedef struct Node {
    char data;
    struct Node *next;
} *Link;

Link createNode(char data) {
    Link node = (Link) malloc(sizeof(struct Node));
    if (!node) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }
    node->data = data;
    node->next = NULL;

    return node;
}

Link pushBack(Link tail, char data) {
    Link newTail = createNode(data);
    if (tail) {
        tail->next = newTail;
    }

    return newTail;
}

void clear(Link head) {
    if (!head) {
        return;
    }

    Link node = head;
    while (node) {
        Link nextNode = node->next;
        free(node);
        node = nextNode;
    }
}

void printList(Link head) {
    if (!head) {
        return;
    }

    Link node = head;
    while (node) {
        Link nextNode = node->next;
        if (node->next) {
            printf("'%c'->", node->data);
        } else {
            printf("'%c'", node->data);
        }
        node = nextNode;
    }

    printf("\n");
}

/*
 * This recursion function can convert a list of character into an integer.
 * It will return true if the conversion succeed, otherwise return * false.
 */
bool convertListToInt(Link head, int *number) {
    if (!head) {
        // If head if NULL, keep *number as unmodified and return true.
        return true;
    }

    if (head->data > '9' || head->data < '0') {
        // Return false if the current element is not from '0' to '9'
        return false;
    }

    // Multiply the original number by 10
    *number *= 10;

    // Convert the current number to its integer form and
    // add it to *number
    *number += (head->data - '0');

    //Convert the next element in the list
    return convertListToInt(head->next, number);
}

// Question 2
int getNewIntArraySize(const int *input, int size) {
    if (!input || !size) {
        return 0;
    }

    int newSize = 0;
    for (int index = 0; index < size; ++index) {
        newSize += input[index];
    }
    return newSize;
}

int *integerFun(int *input, int size) {
    if (!input || !size) {
        return NULL;
    }

    int newSize = getNewIntArraySize(input, size);
    int *output = (int *) malloc(newSize);
    if (!output) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }

    for (int index = 0, outputIndex = 0; index < size; ++index) {
        for (int copyIndex = 0; copyIndex < input[index]; ++copyIndex) {
            output[outputIndex] = input[index];
            outputIndex++;
        }
    }

    return output;
}

void printIntArray(int *input, int size) {
    if (!input || !size) {
        return;
    }

    for (int index = 0; index < size; ++index) {
        printf("%d ", input[index]);
    }
    printf("\n");
}

// Question 3
size_t genericCountNumberOfMatches(
        void *array,
        int arraySize,
        int elementSize,
        bool (*matches)(void *)) {
    if (!array || !matches) {
        return 0;
    }

    int matchedCount = 0;
    for (int index = 0; index < arraySize; index++) {
        void *currentElement = array + elementSize * index;
        if (matches(currentElement)) {
            ++matchedCount;
        }
    }
    return matchedCount;
}

bool isIntegerOdd(void *number) {
    int integerValue = *(int *) number;
    return integerValue % 2 == 1;
}

bool isCharDigit(void *character) {
    char charValue = *(char *) character;
    return charValue >= '0' && charValue <= '9';
}

// Question 4
bool createTestFile() {
    FILE *testFile = fopen(TEST_FILE_PATH, "w");
    if (!testFile) {
        return false;
    }
    fprintf(testFile,
            "This is a text file.\n"
            "This is a line in the text file.\n"
            "C is the best programming language ever");
    fclose(testFile);
    return true;
}

void printFileLineCharacterCount(char *filePath) {
    if (!filePath) {
        printf("File does not exist!\n");
        return;
    }

    FILE *file = fopen(filePath, "r");
    if (!file) {
        return;
    }

    int count = 0;
    char tempChar = '0';
    while (EOF != fscanf(file, "%c", &tempChar)) {
        if (CHAR_CR != tempChar && CHAR_LF != tempChar) {
            ++count;
        } else {
            if (CHAR_LF == tempChar) {
                printf("%d\n", count);
            }
            count = 0;
        }
    }
    printf("%d\n", count);

    fclose(file);
}

// Question 5
typedef struct TreeNode {
    int data;
    struct TreeNode *left;
    struct TreeNode *right;
} treeNode, *pTreeNode;

pTreeNode createTreeNode(int data) {
    pTreeNode node = (pTreeNode) malloc(sizeof(treeNode));
    if (!node) {
        perror("Error allocate memory...");
        exit(ERROR_ALLOCATE_MEMORY);
    }
    node->data = data;
    node->left = node->right = NULL;
    return node;
}

pTreeNode insertWithChildren(int data, pTreeNode left, pTreeNode right) {
    pTreeNode treeNode = createTreeNode(data);
    treeNode->left = left;
    treeNode->right = right;
    return treeNode;
}

void clearTree(pTreeNode root) {
    if (root->left) {
        clearTree(root->left);
    }

    if (root->right) {
        clearTree(root->right);
    }

    free(root);
}

void printTree(pTreeNode root, int level) {
    for (int i = 0; i < level * SPACE_TIMES; i++) {
        printf(" ");
    }

    if (root == NULL) {
        printf("NULL\n");
        return;
    }

    printf("%d\n", root->data);

    printTree(root->left, level + 1);
    printTree(root->right, level + 1);
}

pTreeNode duplicateNodeToLeft(pTreeNode root) {
    if (!root) {
        return NULL;
    }

    pTreeNode newNode = createTreeNode(root->data);
    newNode->left = createTreeNode(root->data);

    if (root->left) {
        newNode->left->left = duplicateNodeToLeft(root->left);
    }

    if (root->right) {
        newNode->right = duplicateNodeToLeft(root->right);
    }

    return newNode;
}

int main() {
    // Test for Question 1
    printf("-----Question 1-----\n");
    Link listRoot = pushBack(NULL, '1');
    Link listTail = pushBack(listRoot, '0');
    listTail = pushBack(listTail, '2');
    pushBack(listTail, '4');

    int convertedResult = 0;
    if (convertListToInt(listRoot, &convertedResult)) {
        printList(listRoot);
        printf("Converted result: %d\n", convertedResult);
    }

    clear(listRoot);

    // Test for Question 2
    printf("-----Question 2-----\n");
    int intArray[3] = {1, 2, 3};
    int oldIntArraySize = sizeof(intArray) / sizeof(int);
    int newIntArraySize = getNewIntArraySize(intArray, oldIntArraySize);
    int *newIntArray = integerFun(intArray, oldIntArraySize);
    printIntArray(intArray, oldIntArraySize);
    printIntArray(newIntArray, newIntArraySize);
    free(newIntArray);

    // Test for Question 3
    printf("-----Question 3-----\n");
    int intArrayToBeCounted[6] = {1, 2, 3, 4, 5, 6};
    size_t count = genericCountNumberOfMatches(
            intArrayToBeCounted, 6, sizeof(int), isIntegerOdd);
    printf("Odd number count: %zu\n", count);

    char charArrayToBeCounted[6] = {'a', 'c', '5', 'z', '7', 'o'};
    size_t charCount = genericCountNumberOfMatches(
            charArrayToBeCounted, 6, sizeof(char), isCharDigit);
    printf("Digit char count: %zu\n", charCount);

    // Test for Question 4
    printf("-----Question 4-----\n");
    if (createTestFile()) {
        printFileLineCharacterCount(TEST_FILE_PATH);
    }

    // Test for Question 5
    printf("-----Question 5-----\n");
    pTreeNode left = createTreeNode(2);
    pTreeNode right = createTreeNode(3);
    pTreeNode root = insertWithChildren(1, left, right);
    pTreeNode newRoot = duplicateNodeToLeft(root);

    printf("-----Old tree-----\n");
    printTree(root, 0);

    printf("-----New tree-----\n");
    printTree(newRoot, 0);

    clearTree(root);
    clearTree(newRoot);

    return 0;
}
