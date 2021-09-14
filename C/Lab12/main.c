#include <stdio.h>
#include <stdlib.h>

#define ERROR_ALLOCATE_MEMORY 1

typedef struct Node {
    int data;
    struct Node *next;
} *link;

link createNode(int data) {
    link node = (link) malloc(sizeof(struct Node));
    if (!node) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }
    node->data = data;
    node->next = NULL;

    return node;
}

link pushFront(link head, int data) {
    link newHead = createNode(data);
    newHead->next = head;

    return newHead;
}

link pushBack(link tail, int data) {
    link newTail = createNode(data);
    if (tail) {
        tail->next = newTail;
    }

    return newTail;
}

void setAt(link head, int index, int data) {
    if (!head) {
        return;
    }

    int nIndex = 0;
    link node = head;
    while (node) {
        link nextNode = node->next;
        if (nIndex == index) {
            node->data = data;
            return;
        }
        node = nextNode;
        nIndex++;
    }
}

void clear(link head) {
    if (!head) {
        return;
    }

    link node = head;
    while (node) {
        link nextNode = node->next;
        free(node);
        node = nextNode;
    }
}

link copyList(link oldHead) {
    if (!oldHead) {
        return NULL;
    }

    link newHead = NULL;
    link newTail = NULL;
    link node = oldHead;
    while (node) {
        if (!newHead) {
            newTail = newHead = pushBack(newHead, node->data);
        } else {
            newTail = pushBack(newTail, node->data);
        }
        node = node->next;
    }

    return newHead;
}

link copyListReverse(link oldHead) {
    if (!oldHead) {
        return NULL;
    }

    link newHead = NULL;
    link node = oldHead;
    while (node) {
        newHead = pushFront(newHead, node->data);
        node = node->next;
    }

    return newHead;
}

void printList(link head, char *listName) {
    if (!head || !listName) {
        return;
    }

    printf("%s:", listName);

    link node = head;
    while (node) {
        link nextNode = node->next;
        printf("%d ", node->data);
        node = nextNode;
    }

    printf("\n");
}

int main() {
    // Unit tests
    link oldHead = pushBack(NULL, 1);
    link oldTail = pushBack(oldHead, 2);
    oldTail = pushBack(oldTail, 3);
    pushBack(oldTail, 4);

    // Copy List
    link copiedHead = copyList(oldHead);

    // Copy Reversed List
    link reversedHead = copyListReverse(oldHead);

    // Set original list data
    setAt(oldHead, 1, 10);

    // Print results
    printList(oldHead, "Old List");
    printList(copiedHead, "Copied List");
    printList(reversedHead, "Reversed List");

    clear(oldHead);
    clear(copiedHead);
    clear(reversedHead);

    return 0;
}
