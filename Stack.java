// Interface que define o comportamento de uma Pilha.

public interface Stack<E> {
	public boolean isEmpty();

	public boolean isFull();

	// Retorna a quantidade de elementos da pilha
	public int numElements();

	// Adiciona um elemento no topo da pilha
	public void push(E element) throws OverflowException;

	// Retira e retorna o elemento do topo da pilha
	public E pop() throws UnderflowException;

	// Informa e retorna o elemento do topo da pilha
	public E top() throws UnderflowException;
}