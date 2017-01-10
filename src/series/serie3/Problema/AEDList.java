package series.serie3.Problema;

import java.util.Iterator;

public class AEDList<E> implements Iterable<E> {

    static class Node<E>{
        Node<E> next, prev;
        E value;
        public Node(E value){
                this.value = value;
            }
    }

    private Node<E> head;
    private int size = 0;

    public AEDList() {
        head = new Node<>(null);
        head.next = head.prev = head;
    }

    public Node<E> getHead() { return head; }

    public void add(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        newNode.prev = head.prev;
        if(head.prev == null) head.prev = newNode;
        else head.prev.next = newNode;
        head.prev = newNode;
        ++size;
    }

    public int size(){ return size; }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = null, pos = head;

            @Override
            public boolean hasNext() {
                if(current != null) return true;
                if(pos.prev.value != null) {
                    pos = pos.prev;
                    current = pos;
                    return true;
                } return false;
            }

            @Override
            public E next() {
                if(hasNext()){
                    current = null;
                    return pos.value;
                } return null;
            }
        };
    }
}