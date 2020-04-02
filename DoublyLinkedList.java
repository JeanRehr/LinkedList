/* Implementacao de uma lista linear com armazenamento dinamico,
 * baseado em nos duplamente encadeados */

public class DoublyLinkedList<E> implements List<E> {
	DNode<E> head;
	DNode<E> tail;
	int numElements;

	public DoublyLinkedList() {
		head = tail = null;
		numElements = 0;
	}

	public int numElements() {
		return numElements;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public boolean isFull() {
		// uma lista com alocacao dinamica nunca estara cheia!
		return false;
	}

	public void insertFirst(E element) {
		// cria um novo no e o torna o novo "head"
		DNode<E> newNode = new DNode<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	public void insertLast(E element) {
		// cria um novo no e o torna o novo "tail"
		DNode<E> newNode = new DNode<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			newNode.setPrevious(tail);
			tail = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	public void insert(E element, int pos) {
		// verifica se a posicao eh valida
		if (pos < 0  ||  pos > numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: insercao no inicio...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou insercao no final
			insertLast(element);
		else { // caso geral: insercao no meio da lista
			// localiza o no imediatamente anterior a posicao
			// onde o novo sera inserido
			DNode<E> prev = head;
			for (int i = 0; i < pos-1; i++)
				prev = prev.getNext();

			// cria um novo no e o posiciona logo apos "prev",
			// ajustando os apontamentos e o total de elementos
			DNode<E> newNode = new DNode<E>(element);
			newNode.setPrevious(prev);
			newNode.setNext(prev.getNext());
			prev.getNext().setPrevious(newNode);
			prev.setNext(newNode);
			numElements++;
		}
	}

	public E removeFirst() {
		// verifica se ha pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma referencia temporaria ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...senao, o segundo elemento passa a ser o "head"
			head = head.getNext();
			head.setPrevious(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	public E removeLast() {
		// verifica se ha pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma referencia temporaria ao elemento sendo removido
		E element = tail.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...senao, o penultimo elemento passa a ser o "tail"
			tail = tail.getPrevious();
			tail.setNext(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	public E remove(int pos) {
		// verifica se a posicao eh valida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: remocao do inicio...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements-1) // ... ou remocao do final
			return removeLast();
		else { // caso geral: remocao do meio da lista
			// localiza o no imediatamente anterior a posicao
			// de onde o elemento sera removido
			DNode<E> prev = head;
			for (int i = 0; i < pos-1; i++)
				prev = prev.getNext();

			// guarda uma ref. temporaria ao elemento sendo removido
			E element = prev.getNext().getElement();

			// ajusta o encadeamento "pulando" o no sendo removido
			prev.setNext(prev.getNext().getNext());
			prev.getNext().setPrevious(prev);

			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	public E get(int pos) {
		// verifica se a posicao eh valida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento ate chegar ao elemento
		DNode<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();

		return current.getElement();
	}

	public int search(E element) {
		// percorre o encadeamento ate encontrar o elemento
		DNode<E> current = head;
		int i = 0;
		while (current != null) {
			if (element.equals(current.getElement()))
					return i;
			i++;
			current = current.getNext();
		}

		// se chegar ate aqui, eh porque nao encontrou
		return -1;
	}

	// exercicio 5
	public void swap(int pos1, int pos2) {
		if (isEmpty())
			throw new UnderflowException();
		if (pos1 < 0 || pos1 > numElements || pos2 < 0|| pos2 > numElements)
			throw new ArrayIndexOutOfBoundsException();
		if (pos1 == pos2)
			return;

		E a1 = (E) new Object();
		E a2 = (E) new Object();
		a1 = remove(pos1);
		pos2--;
		a2 = remove(pos2);
		insert(a1, pos2);
		insert(a2, pos1);
	}

	public String toString() {
		String s = "";

		DNode<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}
}
