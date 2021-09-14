#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

#define ERROR_ALLOCATE_MEMORY 1
#define MEMORY_HOLE 0

typedef struct MemoryBlock {
    struct MemoryBlock *nextBlock;
    unsigned int processId; // 0 Indicates a hole, otherwise process id
    unsigned int base;
    unsigned int limit;
} memoryBlock, *pMemoryBlock;

typedef struct MemoryBlockList {
    pMemoryBlock listHead;
    pMemoryBlock listTail;
} memoryBlockList, *pMemoryBlockList;

pMemoryBlock createMemoryBlock(
        const unsigned int processId,
        const unsigned int base,
        const unsigned int limit) {
    pMemoryBlock block = (pMemoryBlock) malloc(sizeof(memoryBlock));
    if (!block) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }

    block->processId = processId;
    block->base = base;
    block->limit = limit;
    block->nextBlock = NULL;

    return block;
}

void pushBack(
        pMemoryBlockList blockList,
        pMemoryBlock block) {
    if (NULL == blockList->listHead) {
        blockList->listHead = blockList->listTail = block;
        return;
    }

    blockList->listTail->nextBlock = block;
    blockList->listTail = block;
}

void printMemory(pMemoryBlockList blockList) {
    unsigned int index = 1;
    pMemoryBlock block = blockList->listHead;
    while (block) {
        printf("Node %u: ", index);

        if (block->processId) {
            printf("P%u, ", block->processId);
        } else {
            printf("H(Hole) ");
        }

        printf("base=%u, limit=%u\n", block->base, block->limit);

        block = block->nextBlock;
        ++index;
    }
}

void clearMemoryBlockList(pMemoryBlockList blockList) {
    pMemoryBlock block = blockList->listHead;

    while (block) {
        blockList->listHead = block->nextBlock;
        free(block);
        block = blockList->listHead;
    }

    blockList->listTail = NULL;
}

void swapMemoryBlock(pMemoryBlock firstBlock, pMemoryBlock secondBlock) {
    memoryBlock tempBlock = {0};
    pMemoryBlock nextBlock = secondBlock->nextBlock;

    memcpy(&tempBlock, firstBlock, sizeof(memoryBlock));
    memcpy(firstBlock, secondBlock, sizeof(memoryBlock));
    firstBlock->nextBlock = secondBlock;

    memcpy(secondBlock, &tempBlock, sizeof(memoryBlock));
    secondBlock->nextBlock = nextBlock;
}

void sortMemoryBlockList(pMemoryBlockList blockList) {
    pMemoryBlock block = blockList->listHead;

    while (block) {
        pMemoryBlock nextBlock = block->nextBlock;
        while (nextBlock) {
            if (block->base > nextBlock->base) {
                swapMemoryBlock(block, nextBlock);
            }
            nextBlock = nextBlock->nextBlock;
        }
        blockList->listTail = block;
        block = block->nextBlock;
    }
}

void mergeContinuousHole(
        pMemoryBlockList blockList,
        pMemoryBlock block) {
    pMemoryBlock nextBlock = block->nextBlock;
    block->limit += nextBlock->limit;
    block->nextBlock = nextBlock->nextBlock;
    if (blockList->listTail == nextBlock) {
        blockList->listTail = block;
    }
    free(nextBlock);
}

void mergeFreeBlocks(pMemoryBlockList blockList) {
    sortMemoryBlockList(blockList);

    pMemoryBlock block = blockList->listHead;
    while (block) {
        if (MEMORY_HOLE == block->processId
            && block->nextBlock
            && MEMORY_HOLE == block->nextBlock->processId) {
            mergeContinuousHole(blockList, block);
        } else {
            block = block->nextBlock;
        }
    }

}

pMemoryBlock appendProcessBlock(
        pMemoryBlockList blockList,
        pMemoryBlock *lastProcessBlock,
        pMemoryBlock currentBlock
) {
    if (!*lastProcessBlock) {
        blockList->listHead = *lastProcessBlock = currentBlock;
        currentBlock->base = 0;

        return currentBlock->nextBlock;
    }

    currentBlock->base = (*lastProcessBlock)->base + (*lastProcessBlock)->limit;
    (*lastProcessBlock)->nextBlock = currentBlock;
    *lastProcessBlock = currentBlock;

    return currentBlock->nextBlock;
}

pMemoryBlock compactHoleBlock(
        pMemoryBlockList blockList,
        pMemoryBlock holeBlock,
        pMemoryBlock lastProcessBlock,
        pMemoryBlock currentBlock) {
    holeBlock->limit += currentBlock->limit;

    if (blockList->listHead == currentBlock) {
        blockList->listHead = currentBlock->nextBlock;
    } else if (blockList->listTail == currentBlock) {
        blockList->listTail = lastProcessBlock;
    }

    pMemoryBlock nextBlock = currentBlock->nextBlock;
    if (lastProcessBlock) {
        holeBlock->base = lastProcessBlock->base + lastProcessBlock->limit;
        lastProcessBlock->nextBlock = nextBlock;
    }

    free(currentBlock);
    return nextBlock;
}

void compaction(pMemoryBlockList blockList) {
    pMemoryBlock holeBlock = createMemoryBlock(MEMORY_HOLE, 0, 0);
    if (!holeBlock) {
        perror("Failed allocating memory...\n");
        exit(ERROR_ALLOCATE_MEMORY);
    }

    pMemoryBlock currentBlock = blockList->listHead;
    pMemoryBlock lastProcessBlock = NULL;
    while (currentBlock) {
        if (currentBlock->processId) {
            currentBlock = appendProcessBlock(
                    blockList, &lastProcessBlock, currentBlock);
        } else {
            currentBlock = compactHoleBlock(
                    blockList, holeBlock, lastProcessBlock, currentBlock);
        }
    }

    if (!holeBlock->limit) {
        free(holeBlock);
        return;
    }

    pushBack(blockList, holeBlock);
}

void testOrderedBlocks() {
    printf("Test Ordered Block List...\n");
    memoryBlockList blockList = {0};

    pushBack(&blockList, createMemoryBlock(1, 0, 6));
    pushBack(&blockList, createMemoryBlock(17, 6, 1));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 7, 4));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 11, 4));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 15, 1));
    pushBack(&blockList, createMemoryBlock(3, 16, 10));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 26, 6));

    printMemory(&blockList);

    printf("Merging...\n");
    mergeFreeBlocks(&blockList);
    printMemory(&blockList);

    printf("Compacting...\n");
    compaction(&blockList);
    printMemory(&blockList);

    clearMemoryBlockList(&blockList);
    printMemory(&blockList);
    printf("Ordered Block List Cleared.\n\n");
}

void testUnorderedBlocks() {
    printf("Test Unordered Block List...\n");
    memoryBlockList blockList = {0};

    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 7, 4));
    pushBack(&blockList, createMemoryBlock(1, 0, 6));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 11, 4));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 15, 1));
    pushBack(&blockList, createMemoryBlock(17, 6, 1));
    pushBack(&blockList, createMemoryBlock(MEMORY_HOLE, 26, 6));
    pushBack(&blockList, createMemoryBlock(3, 16, 10));

    printMemory(&blockList);

    printf("Merging...\n");
    mergeFreeBlocks(&blockList);
    printMemory(&blockList);

    printf("Compacting...\n");
    compaction(&blockList);
    printMemory(&blockList);

    clearMemoryBlockList(&blockList);
    printMemory(&blockList);
    printf("Unordered Block List Cleared.\n\n");
}

void testEmptyBlocks() {
    printf("Test Empty Block List...\n");
    memoryBlockList blockList = {0};
    printMemory(&blockList);

    printf("Merging...\n");
    mergeFreeBlocks(&blockList);
    printMemory(&blockList);

    printf("Compacting...\n");
    compaction(&blockList);
    printMemory(&blockList);

    clearMemoryBlockList(&blockList);
    printMemory(&blockList);
    printf("Empty Block List Cleared.\n\n");
}

int main() {
    testOrderedBlocks();
    testUnorderedBlocks();
    testEmptyBlocks();
    return 0;
}
