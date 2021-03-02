package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }

        public static <T> Node<T> valueOf(T value) {
            return new Node<>(value);
        }
    }

    private Node<T> root;
    private int size = 0;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> bst = new RecursiveBinarySearchTree<>();
        Stream.of(elements).forEach(bst::insert);
        return bst;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        boolean isInserted = insertElement(element);
        if (isInserted) {
            size++;
        }
        return isInserted;
    }

    private boolean insertElement(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            return true;
        } else {
            return insertSubTree(root, element);
        }
    }

    private boolean insertSubTree(Node<T> subTreeRoot, T element) {
        int comparison = element.compareTo(subTreeRoot.value);

        if (comparison > 0) {
            return insertRightSubTree(subTreeRoot, element);
        } else if (comparison < 0) {
            return insertLeftSubTree(subTreeRoot, element);
        } else {
            return false;
        }
    }

    private boolean insertRightSubTree(Node<T> node, T element) {
        if (node.right != null) {
            return insertSubTree(node.right, element);
        } else {
            node.right = Node.valueOf(element);
            return true;
        }
    }

    private boolean insertLeftSubTree(Node<T> node, T element) {
        if (node.left != null) {
            return insertSubTree(node.left, element);
        } else {
            node.left = Node.valueOf(element);
            return true;
        }
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return findNode(root, element) != null;
    }

    private Node<T> findNode(Node<T> node, T element) {
        if(node == null) {
            return null;
        }

        int comparison = element.compareTo(node.value);

        if (comparison > 0) {
            return findNode(node.right, element);
        } else if (comparison < 0) {
            return findNode(node.left, element);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(node.left), depth(node.right));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }
}
