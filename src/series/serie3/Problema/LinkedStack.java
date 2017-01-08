package series.serie3.Problema;


public final class LinkedStack<E> implements Stack<E>{

    private static class Node<E> {
        private E item;
        private Node<E> next;
    }

    private Node<E> head;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E push(E e) {
        Node<E> n= new Node<E>();
        n.item = e;
        // Inser��o no in�cio duma lista simplesmente ligada.
        n.next = head;
        head = n;
        return e;
    }

    @Override
    public E pop() {
        E v = head.item;
        // Remo��o no in�cio duma lista simplesmente ligada.
        head = head.next;
        return v;
    }

    @Override
    public E peek() {
        return head.item;
    }
}
