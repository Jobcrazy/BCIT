#include <stdio.h>
#include <stdlib.h>

#define ERROR_ALLOCATE_MEMORY 1

typedef struct TreeNode {
    int data;
    struct TreeNode *left;
    struct TreeNode *right;
}treeNode, *pTreeNode;

pTreeNode createNode(int data) {
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
    pTreeNode treeNode = createNode(data);
    treeNode->left = left;
    treeNode->right = right;
    return treeNode;
}

pTreeNode insert(pTreeNode node, int data) {
    pTreeNode newNode = createNode(data);

    if (!node) {
        node = newNode;
        return node;
    }

    if (data < node->data) {
        node->left = insert(node->left, data);
    } else {
        node->right = insert(node->right, data);
    }

    return node;
}

pTreeNode find(pTreeNode node, int data) {
    if (!node) {
        return NULL;
    }

    if (data < node->data) {
        return find(node->left, data);
    } else if (data > node->data) {
        return find(node->right, data);
    }

    return node;
}

size_t getHeight(pTreeNode node) {
    if (!node) {
        return 0;
    }

    size_t leftHeight = getHeight(node->left) + 1;
    size_t rightHeight = getHeight(node->right) + 1;

    return leftHeight > rightHeight ? leftHeight : rightHeight;
}

pTreeNode findMaxInNode(pTreeNode node, int currentMax) {
    if (!node) {
        return NULL;
    }

    if (node->right && currentMax < node->right->data) {
        return findMaxInNode(node->right, node->right->data);
    }

    return node;
}

pTreeNode findMax(pTreeNode node) {
    if (!node) {
        return node;
    }

    return findMaxInNode(node, node->data);
}

void clear(pTreeNode root) {
    if (root->left) {
        clear(root->left);
    }

    if (root->right) {
        clear(root->right);
    }

    free(root);
}

pTreeNode findMaxInNonBstNode(pTreeNode node, pTreeNode maxNode) {
    if (!node) {
        return NULL;
    }

    pTreeNode left = node->left;
    pTreeNode right = node->right;

    if (left) {
        maxNode = findMaxInNonBstNode(
                node->left, node->left->data > maxNode->data ?
                            node->left : maxNode);
    }

    if (right) {
        maxNode = findMaxInNonBstNode(
                node->right, node->right->data > maxNode->data ?
                             node->right : maxNode);
    }

    return maxNode;
}

pTreeNode findNonBstMax(pTreeNode node) {
    if (!node) {
        return node;
    }

    return findMaxInNonBstNode(node, node);
}

int main() {
    pTreeNode root = insert(NULL, 8);
    pTreeNode node = insert(root, 6);
    node = insert(node, 4);
    node = insert(node, 11);
    node = insert(node, 9);
    node = insert(node, 7);
    insert(node, 10);

    printf("6 is in Node: %p\n", find(root, 6));
    printf("Tree Depth is: %zu\n", getHeight(root));
    printf("Max Number is: %d\n", findMax(root) ? findMax(root)->data : 0);

    clear(root);


    pTreeNode left = createNode(8);
    pTreeNode right = createNode(7);
    left = insertWithChildren(50, left, right);
    right = createNode(2);
    right = insertWithChildren(20, left, right);
    root = insertWithChildren(11, createNode(1), right);

    printf("Tree Depth is: %zu\n", getHeight(root));
    printf("Max Number is: %d\n",
           findNonBstMax(root) ? findNonBstMax(root)->data : 0);

    return 0;
}