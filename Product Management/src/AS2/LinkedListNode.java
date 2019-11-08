package AS2;


public class LinkedListNode<E> {
    E info;
    LinkedListNode<E> next;

    public LinkedListNode(E i, LinkedListNode p) {
        this.info = i;
        this.next = p;
    }

    public LinkedListNode(E b) {
        this(b, null);
    }
}
