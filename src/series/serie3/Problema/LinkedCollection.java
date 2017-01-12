package series.serie3.Problema;

import java.util.*;


public class LinkedCollection<E> extends AbstractQueue<E> {
    private static class Node<E> {
        Node<E> prev, next;
        E key;
        Node( ) {
            next = prev = this;
        } // sentinel
        Node ( E value, Node<E> n, Node<E> p ) {
            key = value;
            next = n;
            prev = p;
            n.prev = p.next = this;
        }
    }

    private Node<E> sentinel = new Node();
    private int size, modCount;

    private Node<E> insertNode(E value, Node<E> pred ) {
        ++modCount;
        ++size;
        return new Node<>(value, pred.next, pred );
    }

    private Node<E> removeNode( Node x ) {
        ++modCount;
        --size;
        x.prev.next = x.next;
        x.next.prev = x.prev;
        return x;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add( E v ) {
        addLast( v );
        return  true;
    }

    @Override
    public Iterator<E> iterator( ) {
        return new Iterator<E>() {
            Node<E> curr = sentinel;
            boolean flagNext = false;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return curr != sentinel.prev;
            }

            @Override
            public E next() {
                if ( expectedModCount != modCount )
                  throw new ConcurrentModificationException();
                if ( !hasNext() )
                    throw new NoSuchElementException("...");
                flagNext = true;
                curr = curr.next;
                return curr.key;
            }

            public void remove() {
                if ( expectedModCount != modCount )
                    throw new ConcurrentModificationException();
                if ( !flagNext )
                    throw new IllegalStateException("..");
                flagNext = false;
                removeNode(curr);
                expectedModCount = modCount;
                curr = curr.prev;
            }
        };
    }


    public void clear() {
        ++modCount;
        size = 0;
        sentinel.prev = sentinel.next = sentinel;
    }


    public E getFirst() { return sentinel.next.key; }
    public E getLast()  { return sentinel.prev.key; }
    public void addFirst( E value ){ insertNode( value, sentinel);       }
    public void addLast( E value ) { insertNode( value, sentinel.prev ); }
    public E removeFirst() {
        if ( isEmpty() )
            throw new IllegalStateException("Empty linked list");
        return removeNode( sentinel.next ).key;
    }
    public E removeLast() {
        if ( isEmpty() )
            throw new IllegalStateException("Empty linked list");
        return removeNode( sentinel.prev ).key;
    }

    // FIFO
    public boolean offer(E e) { addLast( e ); return true;      }
    public E poll() { return (isEmpty()) ? null : removeFirst(); }

    // STACK
    public void push(E e) { addFirst( e );       }
    public E pop()        { return removeFirst();}

    // FIFO e STACK
    public E peek() { return (isEmpty()) ? null : getFirst();   }
}