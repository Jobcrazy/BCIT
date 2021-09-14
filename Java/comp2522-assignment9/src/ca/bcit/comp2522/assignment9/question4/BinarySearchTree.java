package ca.bcit.comp2522.assignment9.question4;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node rootNode = null;

    private class Node {
        private T data;
        private Node leftLeaf;
        private Node rightLeaf;

        private Node(T data) {
            this.data = data;
            this.leftLeaf = null;
            this.rightLeaf = null;
        }
    }

    public void addNode(T obj) {
        Node node = new Node(obj);
        if (null == rootNode) {
            rootNode = node;
            return;
        }

        Node tmpNode = rootNode;
        while (null != tmpNode) {
            if (0 > obj.compareTo(tmpNode.data)) {
                if (null == tmpNode.leftLeaf) {
                    tmpNode.leftLeaf = node;
                    return;
                } else {
                    tmpNode = tmpNode.leftLeaf;
                }
            } else if (0 < obj.compareTo(tmpNode.data)) {
                if (null == tmpNode.rightLeaf) {
                    tmpNode.rightLeaf = node;
                    return;
                } else {
                    tmpNode = tmpNode.rightLeaf;
                }
            } else {
                tmpNode.data = obj;
            }
        }
    }

    public T search(T obj) {
        Node tmpNode = rootNode;
        while (null != tmpNode) {
            if (0 > obj.compareTo(tmpNode.data)) {
                if (null == tmpNode.leftLeaf) {
                    return null;
                } else {
                    tmpNode = tmpNode.leftLeaf;
                }
            } else if (0 < obj.compareTo(tmpNode.data)) {
                if (null == tmpNode.rightLeaf) {
                    return null;
                } else {
                    tmpNode = tmpNode.rightLeaf;
                }
            } else {
                break;
            }
        }

        return tmpNode == null ? null : tmpNode.data;
    }
}
