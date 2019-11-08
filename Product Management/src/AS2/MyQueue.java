package AS2;


import java.util.LinkedList;


class MyQueue {

    LinkedList<BTreeNode> t;

    MyQueue() {
        t = new LinkedList<>();
    }

    boolean isEmpty() {
        return (t.isEmpty());
    }

    void enqueue(BTreeNode p) {
        t.addLast(p);
    }

    BTreeNode dequeue() {
        if (isEmpty()) {
            return (null);
        }
        return (t.removeFirst());
    }

    BTreeNode front() {
        if (isEmpty()) {
            return (null);
        }
        return (t.getFirst());
    }
}
