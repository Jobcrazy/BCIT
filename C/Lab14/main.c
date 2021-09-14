#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define ERROR_ALLOCATE_MEMORY 1
#define SIZE_OF_TEST_ARRAY 5

typedef struct TreeNode {
    void *data;
    struct TreeNode *left;
    struct TreeNode *right;
} treeNode, *treeNodeLink;

treeNodeLink createNode(void *data) {
    treeNodeLink node = (treeNodeLink) malloc(sizeof(treeNode));
    if (!node) {
        perror("Error allocate memory...");
        exit(ERROR_ALLOCATE_MEMORY);
    }
    node->data = data;
    node->left = node->right = NULL;
    return node;
}

treeNodeLink insert(
        treeNodeLink node,
        void *data,
        bool (*compareCallback)(const void *first, const void *second)) {
    treeNodeLink newNode = createNode(data);
    if (!node) {
        node = newNode;
        return node;
    }

    if (!compareCallback(data, node->data)) {
        node->left = insert(node->left, data, compareCallback);
    } else {
        node->right = insert(node->right, data, compareCallback);
    }

    return node;
}

void clear(treeNodeLink root) {
    if (root->left) {
        clear(root->left);
    }

    if (root->right) {
        clear(root->right);
    }

    free(root);
}

void printInOrder(
        treeNodeLink root,
        void (*printCallBack)(const void *data)) {
    if (!root) {
        return;
    }

    printInOrder(root->left, printCallBack);
    printCallBack(root->data);
    printInOrder(root->right, printCallBack);
}

void sort(
        void *array,
        size_t elementNumber,
        size_t elementSize,
        bool (*compareCallback)(const void *first, const void *second),
        void (*printCallBack)(const void *data)) {
    treeNodeLink root = NULL;
    for (int index = 0; index < elementNumber / elementSize; ++index) {
        void *data = array + index * elementSize;
        root = insert(root, data, compareCallback);
    }

    printInOrder(root, printCallBack);
    printf("\n");
    clear(root);
}

bool intCompare(
        const void *first,
        const void *second) {
    return *(int *) first > *(int *) second;
}

void intPrint(const void *data) {
    printf("%d ", *(int *) data);
}

bool charCompare(
        const void *charOne,
        const void *charTwo) {
    return *(char *) charOne > *(char *) charTwo;
}

void charPrint(const void *data) {
    printf("%c ", *(char *) data);
}

bool floatCompare(
        const void *floatOne,
        const void *floatTwo) {
    return *(float *) floatOne > *(float *) floatTwo;
}

void floatPrint(const void *data) {
    printf("%f ", *(float *) data);
}

bool stringCompare(
        const void *stringOne,
        const void *stringTwo) {
    char *firstValue = *(char **) stringOne;
    char *secondValue = *(char **) stringTwo;
    return strcmp(firstValue, secondValue) > 0;
}

void stringPrint(const void *data) {
    printf("%s ", *(char **) data);
}

int main() {
    // int test
    int intArray[SIZE_OF_TEST_ARRAY] = {12, 50, 3, 99, 6};
    sort(intArray, sizeof(intArray), sizeof(int), intCompare, intPrint);

    // float Test
    float floatArray[SIZE_OF_TEST_ARRAY] = {12.52f, 50.99f, 3.14f, 99.99f, 6.3f};
    sort(floatArray, sizeof(floatArray), sizeof(float), floatCompare, floatPrint);

    // char test
    char charArray[SIZE_OF_TEST_ARRAY] = {'d', 'Z', 'H', 'x', 'L'};
    sort(charArray, sizeof(charArray), sizeof(char), charCompare, charPrint);

    // string test
    char *stringArray[SIZE_OF_TEST_ARRAY] = {
            "Hello",
            "World",
            "James",
            "Liu",
            "LiuA"
    };
    sort(stringArray, sizeof(stringArray), sizeof(char*), stringCompare, stringPrint);

    return 0;
}
