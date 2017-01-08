package series.serie3.Problema;

public interface Stack<E> {
	 boolean isEmpty();
	 E push(E e);
	/* Assume-se que antes de invocar os m�todos
	 * � testado se a pilha est� vazia */
	 E pop();
	 E peek();
}
