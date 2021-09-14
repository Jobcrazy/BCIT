#include <stdio.h>
#include <stdlib.h>

#define ERROR_ALLOCATE_MEMORY 1

typedef struct Node {
    int data;
    struct Node *next;
} *pNode;

pNode createNode(int data) {
    pNode node = (pNode) malloc(sizeof(struct Node));
    if (!node) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }
    node->data = data;
    node->next = NULL;

    return node;
}

pNode pushBack(pNode tail, int data) {
    pNode newTail = createNode(data);
    if (tail) {
        tail->next = newTail;
    }

    return newTail;
}

void clear(pNode head) {
    if (!head) {
        return;
    }

    pNode node = head;
    while (node) {
        pNode nextNode = node->next;
        free(node);
        node = nextNode;
    }
}

void printList(pNode head) {
    if (!head) {
        return;
    }

    pNode node = head;
    while (node) {
        pNode nextNode = node->next;
        if (node->next) {
            printf("%d->", node->data);
        } else {
            printf("%d", node->data);
        }
        node = nextNode;
    }

    printf("\n");
}

pNode concatenateListToSelf(pNode head) {
    pNode tmpTail = head;
    pNode tmpNewHeadOne = NULL;
    pNode tmpNewHeadTwo = NULL;
    pNode tmpNewTailOne = NULL;
    pNode tmpNewTailTwo = NULL;

    while (tmpTail) {
        if (!tmpNewHeadOne) {
            tmpNewTailOne = tmpNewHeadOne = pushBack(NULL, tmpTail->data);
            tmpNewTailTwo = tmpNewHeadTwo = pushBack(tmpNewHeadOne, tmpTail->data);
        } else {
            tmpNewTailOne = pushBack(tmpNewTailOne, tmpTail->data);
            tmpNewTailTwo = pushBack(tmpNewTailTwo, tmpTail->data);
        }

        tmpTail = tmpTail->next;
    }

    if (tmpNewHeadOne) {
        tmpNewTailOne->next = tmpNewHeadTwo;
    }

    return tmpNewHeadOne;
}

void test() {
    pNode head = pushBack(NULL, 1);
    pNode tail = pushBack(head, 2);
    tail = pushBack(tail, 3);
    tail = pushBack(tail, 4);
    pushBack(tail, 5);

    pNode newHead = concatenateListToSelf(head);

    printList(head);
    printList(newHead);

    clear(head);
    clear(newHead);
}

int main() {
    test();
    return 0;
}