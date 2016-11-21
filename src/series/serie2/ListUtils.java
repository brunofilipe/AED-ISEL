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
        Node<Node<String>>sentinel = new Node<>();
        sentinel.next = sentinel.previous = sentinel;
        Node<String>curr = list.next;
        sentinel.next.value = curr;
        while (curr!=list){
            if(curr.value.equals(".")){
                insertSublist(sentinel,curr);
            }
            curr = curr.next;
        }
        return sentinel;
    }

    private static <E> void insertSublist(Node<Node<E>> sentinel, Node<E> subList) {
        Node<Node<E>> n = new Node<>( subList.previous);
        sentinel.previous = n;
    }
}
