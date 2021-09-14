#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define ERROR_ALLOCATE_MEMORY 1

typedef struct IntNode {
    struct IntNode *nextLink;
    int data;
} intNode, *intListLink;

intNode *createIntNode(int data) {
    intListLink node = (intListLink) malloc(sizeof(intNode));
    if (!node) {
        perror("Error allocating memory!");
        exit(ERROR_ALLOCATE_MEMORY);
    }

    node->data = data;
    node->nextLink = NULL;
    return node;
}

intListLink pushIntListBack(intListLink tail, int data) {
    intListLink node = createIntNode(data);
    if (!tail) {
        return node;
    }

    tail->nextLink = node;

    return node;
}

void printIntList(intListLink head) {
    while (head != NULL) {
        intListLink next = head->nextLink;
        printf("%d-->", head->data);
        head = next;
    }

    printf("NULL\n");
}

size_t getIntListSize(intListLink head) {
    size_t size = 0;

    while (head) {
        ++size;
        head = head->nextLink;
    }

    return size;
}

bool split(
        intListLink head,
        intListLink *firstHead,
        intListLink *secondHead) {
    if (!head || !firstHead || !secondHead) {
        return false;
    }

    size_t originalSize = getIntListSize(head);
    size_t currentFistSize = 0;
    size_t firstTotalSize =
            originalSize % 2 ? originalSize / 2 + 1 : originalSize / 2;
    intListLink firstTail = NULL, secondTail = NULL;

    while (head) {
        if (currentFistSize < firstTotalSize) {
            firstTail = pushIntListBack(firstTail, head->data);
            if (!*firstHead) *firstHead = firstTail;
            ++currentFistSize;
        } else {
            secondTail = pushIntListBack(secondTail, head->data);
            if (!*secondHead) *secondHead = secondTail;
        }
        head = head->nextLink;
    }

    return true;
}

void clearIntList(intListLink head) {
    if (!head) {
        return;
    }

    while (head) {
        intListLink tmpHead = head->nextLink;
        free(head);
        head = tmpHead;
    }
}

int main() {
    intListLink partOne = NULL, partTwo = NULL;
    intListLink head = NULL, tail = NULL;

    head = pushIntListBack(NULL, 1);
    if (split(head, &partOne, &partTwo)) {
        printIntList(head);
        printIntList(partOne);
        printIntList(partTwo);
        printf("\n");
    }

    clearIntList(head);
    clearIntList(partOne);
    clearIntList(partTwo);
    head = tail = partOne = partTwo = NULL;

    head = tail = pushIntListBack(NULL, 1);
    tail = pushIntListBack(tail, 2);
    tail = pushIntListBack(tail, 3);
    tail = pushIntListBack(tail, 4);

    if (split(head, &partOne, &partTwo)) {
        printIntList(head);
        printIntList(partOne);
        printIntList(partTwo);
        printf("\n");
    }

    clearIntList(head);
    clearIntList(partOne);
    clearIntList(partTwo);
    head = tail = partOne = partTwo = NULL;

    head = tail = pushIntListBack(NULL, 1);
    tail = pushIntListBack(tail, 2);
    tail = pushIntListBack(tail, 3);
    tail = pushIntListBack(tail, 4);
    tail = pushIntListBack(tail, 5);

    if (split(head, &partOne, &partTwo)) {
        printIntList(head);
        printIntList(partOne);
        printIntList(partTwo);
        printf("\n");
    }

    clearIntList(head);
    clearIntList(partOne);
    clearIntList(partTwo);
    head = tail = partOne = partTwo = NULL;

    return 0;
}
