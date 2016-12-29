package series.serie3;


public class MyList<E> {
    private static class  Node<E>{
        Node<E> next, prev;
        E value;
        public Node(E value){
            this.value = value;
        }
    }
    private Node<E> head;
    private int size = 0;
    public MyList() {
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
}
