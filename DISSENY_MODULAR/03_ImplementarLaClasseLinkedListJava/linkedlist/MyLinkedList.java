package linkedlist;

import java.util.ArrayList;

public class MyLinkedList<T> {

    private final ArrayList<Node<T>> ramMemory = new ArrayList<>();

    public MyLinkedList() {

    }

    // FUNCIONS COMUNES

    /**
     * Returns the of this collection
     */
    public int size() {
        return ramMemory.size();
    }

    /**
     * Get the value at given index
     *
     * @throws IndexOutOfBoundsException the collection doesn't contain the
     *                                   requested element
     */
    public T get(Integer index) {
        throw new RuntimeException();
    }

    /**
     * Adds the element at index
     *
     * @throws IndexOutOfBoundsException the collection doesn't contain the
     *                                   requested element
     */
    public void set(Integer index, T element) {
        throw new RuntimeException();
    }

    /**
     * Removes the element at index
     *
     * @throws IndexOutOfBoundsException the collection doesn't contain the
     *                                   requested element
     */
    public void remove(Integer index) {
        throw new RuntimeException();
    }

    // PILES I CUES

    /**
     * Adds the element at the fist position
     */
    public void addFirst(T element) {
        throw new RuntimeException();
    }

    /**
     * Adds the element at the last position
     */
    public void addLast(T element) {
        throw new RuntimeException();
    }

    /**
     * Get and remove the element at the first position
     */
    public T popFirst() {
        throw new RuntimeException();
    }

    /**
     * Get and remove the element at the last position
     */
    public T popLast() {
        throw new RuntimeException();
    }
}