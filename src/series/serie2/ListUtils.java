package series.serie2;

import java.util.Comparator;

public class ListUtils {
	
	public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp){
        if(first!= null && last!=null){
            Node<E> middle  = partition(first,last,cmp);
            if(middle !=first)quicksort(first,middle.previous,cmp);
            if(middle!=last)quicksort(middle.next,last,cmp);
        }
    }
    private static <E> Node<E> partition(Node<E> first, Node<E> last, Comparator<E> cmp) {
		Node<E>pivot = last;
		Node<E>wall = new Node<>();
        wall.next = first;
		for(Node<E>actual = first;actual!= pivot;actual=actual.next) {
            if(cmp.compare(actual.value,pivot.value)<0){
                wall = wall.next;
                swap(wall,actual);
            }
        }
        swap(wall.next,pivot);
        return wall.next;
    }
    private static <E> void swap(Node<E> a, Node<E> b) {
		E aux = a.value;
        a.value = b.value;
		b.value = aux;
	}

	public static Node<Node<String>> splitBySentence(Node<String> list) {
        Node <Node<String>> sentinel = new Node<>();
        sentinel.next = sentinel.previous = sentinel;
        Node <Node<String>> curr = sentinel;
        Node <String> subList = null;
        while(true){
            if(list.next.value == null){                                //verifica se já cheguei ao inicio da lista
                return sentinel;
            }
            if(".".equals(list.next.value)){                           //caso seja "."
                curr = curr.next;                                       //avanço no nós principais
                list = list.next;                                       //avanço na lista de palavras
            }
            else {
                Node<String> extracted = extractHeadfromList(list);     //removo da lista de palavras e reaproveito
                if(curr == curr.next){                                  //verifico se estou no mesmo nó
                    insertOnNext(curr,new Node <> ());
                    curr.next.value = extracted;
                } else {
                    subList.next = extracted;
                }
                subList = extracted;
            }
        }
    }

    private static void insertOnNext(Node<Node<String>> curr, Node<Node<String>> newNode) {
        newNode.next = curr.next;
        curr.next = newNode;
        newNode.next.previous = newNode;
        newNode.previous = curr;
    }

    private static Node<String> extractHeadfromList(Node<String> list) {
        Node <String> aux  = list.next;
        list.next = aux.next;
        list.next.previous = aux.previous;
        aux.next = null;
        aux.previous = null;
        return aux;
    }
}
