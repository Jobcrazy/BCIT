/*
 * Copyright (c) 2021, Hang Liu
 * All rights reserved.
 *
 * This is a template list written in C, and the idea is from Windows DDK.
 * This template list can store any data type of C, including structure.
 * However, you should manage the memory by yourself, since this template
 * list does not manage the memory.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *     * Neither the name of the author nor the names of its contributors
 *       may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

#include <stdio.h>
#include <stdlib.h>

#define CONTAINING_RECORD(address, type, field) \
            ((type *)((char *)(address) - (long)(&((type *)0)->field)))

typedef struct ListEntry {
    struct ListEntry *fLink;
    struct ListEntry *bLink;
} listEntry;

void initListEntry(listEntry *pListEntry) {
    pListEntry->fLink = pListEntry->bLink = NULL;
}

typedef struct List {
    listEntry *pHead;
    listEntry *pTail;
    size_t size;
} list;

void initList(list *pList) {
    if (!pList) {
        return;
    }
    pList->pHead = pList->pTail = NULL;
    pList->size = 0;
}

void pushListFront(list *pList, listEntry *pEntry) {
    initListEntry(pEntry);
    pEntry->fLink = pList->pHead;

    if (pList->pHead) {
        pList->pHead->bLink = pEntry;
        pList->pHead = pEntry;
    } else {
        pList->pHead = pList->pTail = pEntry;
    }

    ++(pList->size);
}

void pushListBack(list *pList, listEntry *pEntry) {
    initListEntry(pEntry);
    pEntry->bLink = pList->pTail;

    if (pList->pTail) {
        pList->pTail->fLink = pEntry;
        pList->pTail = pEntry;
    } else {
        pList->pHead = pList->pTail = pEntry;
    }

    ++(pList->size);
}

listEntry *getListElement(list *pList, int index) {
    listEntry *pEntry = pList->pHead;

    for (int nIndex = 0; nIndex < index &&
                         nIndex < pList->size; ++nIndex) {
        pEntry = pEntry->fLink;
    }

    return pEntry;
}

listEntry *removeListElement(list *pList, int index) {
    listEntry *entry = getListElement(pList, index);
    if (!entry) {
        return NULL;
    }

    if (entry->bLink) {
        entry->bLink->fLink = entry->fLink;
    }

    if (entry->fLink) {
        entry->fLink->bLink = entry->bLink;
    }

    --(pList->size);

    return entry;
}

listEntry *popListFront(list *pList) {
    if (!pList->pHead) {
        return NULL;
    }

    listEntry *oldHead = pList->pHead;
    pList->pHead = oldHead->fLink;

    if (pList->pHead) {
        pList->pHead->bLink = NULL;
    }

    --(pList->size);

    return oldHead;
}

listEntry *popListBack(list *pList) {
    if (!pList->pTail) {
        return NULL;
    }

    listEntry *oldTail = pList->pTail;
    pList->pTail = oldTail->bLink;

    if (pList->pTail) {
        pList->pTail->fLink = NULL;
    }

    --(pList->size);

    return oldTail;
}

typedef struct IntNode {
    listEntry entry;
    int value;
} intNode, *pIntNode;

intNode *createIntNode(int number) {
    pIntNode node = (pIntNode) malloc(sizeof(listEntry));
    if (!node) {
        return NULL;
    }
    node->value = number;
    return node;
}

void printList(list *pList) {
    listEntry *pTempEntry = pList->pHead;

    while (pTempEntry != NULL) {
        intNode *node = CONTAINING_RECORD(pTempEntry, intNode, entry);
        printf("%d\t", node->value);
        pTempEntry = pTempEntry->fLink;
    }

    printf("\n\n");
}

void clearIntList(list *pList) {
    listEntry *pTempEntry = popListFront(pList);

    while (pTempEntry) {
        free(pTempEntry);
        pTempEntry = popListFront(pList);
    }
}

int main() {
    list myList;
    initList(&myList);

    int testNumber = 1;

    // Test push element to head
    pIntNode node = createIntNode(testNumber++);
    if (node) pushListFront(&myList, &node->entry);


    node = createIntNode(testNumber++);
    if (node) pushListFront(&myList, &node->entry);

    // Test push element to tail
    node = createIntNode(testNumber++);
    if (node) pushListBack(&myList, &node->entry);

    node = createIntNode(testNumber++);
    if (node) pushListBack(&myList, &node->entry);

    node = createIntNode(testNumber++);
    if (node) pushListBack(&myList, &node->entry);

    printf("list size: %zu\n", myList.size);
    printList(&myList);

    // Test get 2nd element
    intNode *secondNode = CONTAINING_RECORD(
            getListElement(&myList, 1), intNode, entry);
    printf("The second element is %d\n\n", secondNode->value);

    // Test pop element from head
    listEntry *popped = popListFront(&myList);
    if (popped) free(popped);

    printf("list size: %zu\n", myList.size);
    printList(&myList);

    // Test pop element from tail
    popped = popListBack(&myList);
    if (popped) free(popped);

    printf("list size: %zu\n", myList.size);
    printList(&myList);

    // Test remove 2nd element
    listEntry *removed = removeListElement(&myList, 1);
    if (removed) free(removed);

    printf("list size: %zu\n", myList.size);
    printList(&myList);

    // Test clear int list
    clearIntList(&myList);

    printf("list size: %zu\n", myList.size);
    printList(&myList);

    return 0;
}
