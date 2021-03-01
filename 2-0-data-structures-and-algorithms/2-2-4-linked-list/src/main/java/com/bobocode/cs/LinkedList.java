package com.bobocode.cs;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> {

    private static class Node<T> {
        private T element;
        private Node<T> next;

        private Node(T element) {
            this.element = element;
        }

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */

    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */

    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);

        Node<T> newNode = Node.valueOf(element);

        if (head == null) {
            head = tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<T> prev;
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
                if (i == index - 1) {
                    prev.next = newNode;
                    newNode.next = current;
                }
            }
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */

    public void set(int index, T element) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            head.element = element;
        } else if (index == size - 1) {
            tail.element = element;
        } else {
            Node<T> current = head;
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    current.element = element;
                }
                current = current.next;
            }
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */

    public T get(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            return head.element;
        } else if (index == size - 1) {
            return tail.element;
        } else {
            Node<T> current = head;
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    return current.element;
                }
                current = current.next;
            }
        }

        return null;
    }

    /**
     * Returns the head element of the list. Operation is performed in constant time O(1)
     *
     * @return the head element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        return head.element;
    }

    /**
     * Returns the tail element of the list. Operation is performed in constant time O(1)
     *
     * @return the tail element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */

    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */

    public T remove(int index) {
        Objects.checkIndex(index, size);

        T removedElement = null;

        if (index == 0) {
            removedElement = head.element;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> prev = null;
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                prev = current;
                current = current.next;
            }
            removedElement = current.element;
            prev.next = current.next;
            if (index == size - 1) {
                tail = prev;
            }
        }
        size--;
        return removedElement;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */

    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */

    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */

    public void clear() {
        head = tail = null;
        size = 0;
    }
}
