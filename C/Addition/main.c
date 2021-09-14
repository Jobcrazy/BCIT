#include <stdio.h>
#include <stdlib.h>

#define ERROR_ALLOCATE_MEMORY 1

typedef struct IntNode {
    struct IntNode *previousLink;
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
    node->previousLink = node->nextLink = NULL;
    return node;
}

intListLink pushIntListFront(intListLink head, int data) {
    intListLink node = createIntNode(data);
    if (!head) {
        return node;
    }

    head->previousLink = node;
    node->nextLink = head;

    return node;
}

intListLink pushIntListBack(intListLink tail, int data) {
    intListLink node = createIntNode(data);
    if (!tail) {
        return node;
    }

    tail->nextLink = node;
    node->previousLink = tail;

    return node;
}

void printIntList(intListLink head) {
    while (head != NULL) {
        intListLink next = head->nextLink;
        printf("%d", head->data);
        if (next) {
            printf("<-->");
        }
        head = next;
    }

    printf("\n");
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

intListLink addIntList(intListLink firstTail, intListLink secondTail) {
    int carryOut = 0;
    intListLink result = NULL;

    while (NULL != firstTail || NULL != secondTail) {
        int firstInt = firstTail ? firstTail->data : 0;
        int secondInt = secondTail ? secondTail->data : 0;
        int tmpResult = firstInt + secondInt + carryOut;

        result = pushIntListFront(result, tmpResult % 10);

        carryOut = tmpResult >= 10 ? 1 : 0;

        if (firstTail) firstTail = firstTail->previousLink;
        if (secondTail) secondTail = secondTail->previousLink;
    }

    if (carryOut) {
        result = pushIntListFront(result, carryOut);
    }

    return result;
}

int main() {
    intListLink firstNumber = pushIntListBack(NULL, 9);
    intListLink firstTail = pushIntListBack(firstNumber, 0);
    firstTail = pushIntListBack(firstTail, 1);

    intListLink secondNumber = pushIntListBack(NULL, 9);
    intListLink secondTail = pushIntListBack(secondNumber, 9);

    intListLink result = addIntList(firstTail, secondTail);

    printIntList(firstNumber);
    printIntList(secondNumber);
    printIntList(result);

    clearIntList(firstNumber);
    clearIntList(secondNumber);
    clearIntList(result);

    return 0;
}
