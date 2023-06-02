package linkedlist;

public class Node<T> {
    public T content;

    public Node<T> previous;
    public Node<T> next;

    /**
     *
     * @param content  The content of this node
     * @param previous The node that precedes this
     * @param next     The node that follows this
     */
    public Node(T content, Node<T> previous, Node<T> next) {
        this.content = content;
        this.previous = previous;
        this.next = next;
    }
}
