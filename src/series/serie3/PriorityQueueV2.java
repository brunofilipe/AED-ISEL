package series.serie3;


import java.util.Comparator;

public class PriorityQueueV2<E,V> {
    public static class Node<E, V> {
        E value;
        V prio;
        int key;
        public Node(E value, V prio) {
            this.value = value;
            key = this.value.hashCode();
            this.prio = prio;
        }
    }


    private HashTable<Integer, Integer> map;

    private static final int DEFAULT_CAPACITY = 32;

    private Node<E, V>[] queue;

    private int size;

    private Comparator<V> cmp;

    public PriorityQueueV2(Comparator<V> cmp) {
        this(DEFAULT_CAPACITY, cmp);
    }


    @SuppressWarnings("unchecked")
    public PriorityQueueV2(int capacity, Comparator<V> cmp) {
        queue = (Node<E, V>[]) new Node[capacity];
        map = new HashTable<>(capacity,2);
        size = 0;
        this.cmp = cmp;
    }

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    private void grow() {
        Node<E, V>[] aux = (Node<E, V>[]) new Node[size * 2];
        for (int i = 0; i < queue.length; i++) {
            aux[i] = queue[i];
        }
        queue = aux;
    }

    public int add(E elem, V prio) {
        if (size >= queue.length) {
            grow();
        }
        Node<E, V> toAdd = new Node<>(elem, prio);
        map.put(toAdd.key, size);
        queue[size] = toAdd;
        increaseKey(size);
        size++;

        return toAdd.key;
    }

    private Node<E, V> peekNode() {
        return size > 0 ? queue[0] : null;
    }

    public E peek() {
        Node<E, V> peekNode = peekNode();
        return peekNode == null ? null : peekNode.value;
    }

    private Node<E, V> pollNode() {
        if (size > 0) {
            Node<E, V> aux = queue[0];
            remove(queue[0].key);
            return aux;
        }
        return null;
    }

    public E poll() {
        Node<E, V> pollNode = pollNode();
        return pollNode == null ? null : pollNode.value;
    }

    public void update(int key, V prio) {
        if (cmp == null) {
            updateNoCmp(key, prio);
        } else {
            updateCmp(key, prio);
        }
    }

    public void updateCmp(int key, V prio) {
        Integer index = map.get(key);

        if (index != null) {
            V aux = queue[index].prio;
            queue[index].prio = prio;

            if (cmp.compare(aux, prio) < 0) {
                decreaseKeyCmp(index);
            } else {
                increaseKeyCmp(index);
            }
            return;
        }

        throw new IllegalArgumentException("No Such key to update");
    }

    public void updateNoCmp(int key, V prio) {
        Integer index = map.get(key);

        if (index != null) {
            Comparable<? super V> aux = (Comparable<? super V>) queue[index].prio;
            queue[index].prio = prio;

            if (aux.compareTo(prio) < 0) {
                decreaseKeyNoCmp(index);
            } else {
                increaseKeyNoCmp(index);
            }
            return;
        }

        throw new IllegalArgumentException("No Such key to update");
    }

    public void remove(int key) {
        Integer i = map.get(key);
        if (i != null) {
            queue[i] = queue[--size];
            map.put(queue[size].key, i);
            decreaseKey(i);
            map.remove(key);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void decreaseKey(int index) {
        if (cmp == null) {
            decreaseKeyNoCmp(index);
        } else {
            decreaseKeyCmp(index);
        }
    }

    private void decreaseKeyCmp(int index) {
        int i, son;
        for (i = index; i * 2 + 1 <= this.size - 1; i = son) {
            son = i * 2 + 1;
            if (son < this.size - 1 && cmp.compare(queue[son].prio, queue[son + 1].prio) > 0) {
                son++;
            }
            if (cmp.compare(queue[i].prio, queue[son].prio) > 0) {
                Node<E, V> aux = queue[i];
                queue[i] = queue[son];
                map.put(queue[son].key, i);

                queue[son] = aux;
                map.put(aux.key, son);
            } else
                break;
        }
    }

    private void decreaseKeyNoCmp(int index) {
        int i, son;
        for (i = index; i * 2 + 1 <= this.size - 1; i = son) {
            son = i * 2 + 1;
            if (son < this.size - 1 && ((Comparable<? super V>)queue[son].prio).compareTo(queue[son + 1].prio) > 0) {
                son++;
            }
            if (((Comparable<? super V>)queue[i].prio).compareTo(queue[son].prio) > 0) {
                Node<E, V> aux = queue[i];
                queue[i] = queue[son];
                map.put(queue[son].key, i);

                queue[son] = aux;
                map.put(aux.key, son);
            } else
                break;
        }
    }

    private void increaseKey(int index) {
        if (cmp == null) {
            increaseKeyNoCmp(index);
        } else {
            increaseKeyCmp(index);
        }
    }

    private void increaseKeyCmp(int index) {
        int i;
        for (i = index; i > 0; i = (i - 1) / 2) {
            if (cmp.compare(queue[i].prio, queue[(i - 1) / 2].prio) < 0) {
                Node<E, V> aux = queue[i];

                queue[i] = queue[(i - 1) / 2];
                map.put(queue[(i - 1) / 2].key, i);

                queue[(i - 1) / 2] = aux;
                map.put(aux.key, (i - 1) / 2);
            } else {
                break;
            }
        }
    }

    private void increaseKeyNoCmp(int index) {
        int i;
        for (i = index; i > 0; i = (i - 1) / 2) {
            if (((Comparable<? super V>) queue[i].prio).compareTo(queue[(i - 1) / 2].prio) < 0) {
                Node<E, V> aux = queue[i];

                queue[i] = queue[(i - 1) / 2];
                map.put(queue[(i - 1) / 2].key, i);

                queue[(i - 1) / 2] = aux;
                map.put(aux.key, (i - 1) / 2);
            } else {
                break;
            }
        }
    }
}
