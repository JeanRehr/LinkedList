import java.util.Random;

/* Implementacao de uma lista linear com armazenamento dinamico,
baseado em nos singularmente encadeados */

public class SinglyLinkedList<E> implements List<E>
{
	Node<E> head;
	Node<E> tail;
	int numElements;

	public SinglyLinkedList()
	{
		head = tail = null;
		numElements = 0;
	}

	public int numElements()
	{
		return numElements;
	}

	public boolean isEmpty()
	{
		return numElements == 0;
	}

	public boolean isFull()
	{
		// uma lista com alocacao dinamica nunca estara cheia!
		return false;
	}

	public void insertFirst(E element)
	{
		// cria um novo no e o torna o novo "head"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			newNode.setNext(head);
			head = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	public void insertLast(E element)
	{
		// cria um novo no e o torna o novo "tail"
		Node<E> newNode = new Node<E>(element);
		if (isEmpty())
			head = tail = newNode;
		else {
			tail.setNext(newNode);
			tail = newNode;
		}

		// ajusta o total de elementos
		numElements++;
	}

	public void insert(E element, int pos)
	{
		// verifica se a posicao eh valida
		if (pos < 0 || pos > numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: insercao no inicio...
		if (pos == 0)
			insertFirst(element);
		else if (pos == numElements) // ... ou insercao no final
			insertLast(element);
		else { // caso geral: insercao no meio da lista
				// localiza o no imediatamente anterior a posicao
				// onde o novo sera inserido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();

			// cria um novo no e o posiciona logo apos "prev",
			// ajustando os apontamentos e o total de elementos
			Node<E> newNode = new Node<E>(element);
			newNode.setNext(prev.getNext());
			prev.setNext(newNode);
			numElements++;
		}
	}

	public E removeFirst()
	{
		// verifica se ha pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma refer���ncia temporaria ao elemento sendo removido
		E element = head.getElement();

		// se a lista possui somente 1 elemento, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else
			// ...senao, o segundo elemento passa a ser o "head"
			head = head.getNext();

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	public E removeLast()
	{
		// verifica se ha pelo menos um elemento a ser removido
		if (isEmpty())
			throw new UnderflowException();

		// guarda uma referencia temporaria ao elemento sendo removido
		E element = tail.getElement();

		// se a lista possui somente 1 elemeno, basta definir
		// "head" e "tail" para null...
		if (head == tail)
			head = tail = null;
		else {
			// ...senao, eh necessario percorrer o encadeamento
			// ate chegar ao no imediatamente anterior ao ultimo...
			Node<E> prev = head;
			while (prev.getNext() != tail)
				prev = prev.getNext();

			// ...para poder torna-lo o novo "tail"
			tail = prev;
			prev.setNext(null);
		}

		// ajusta o total de elementos e retorna o removido
		numElements--;
		return element;
	}

	public E remove(int pos)
	{
		// verifica se a posicao eh valida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// casos especiais: remocao do inicio...
		if (pos == 0)
			return removeFirst();
		else if (pos == numElements - 1) // ... ou remocao do final
			return removeLast();
		else { // caso geral: remocao do meio da lista
				// localiza o no imediatamente anterior eh posicao
				// de onde o elemento sera removido
			Node<E> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.getNext();

			// guarda uma ref. temporaria ao elemento sendo removido
			E element = prev.getNext().getElement();

			// ajusta o encadeamento "pulando" o no sendo removido
			prev.setNext(prev.getNext().getNext());

			// ajusta o total de elementos e retorna o removido
			numElements--;
			return element;
		}
	}

	public E get(int pos)
	{
		// verifica se a posicao eh valida
		if (pos < 0 || pos >= numElements)
			throw new IndexOutOfBoundsException();

		// percorre o encadeamento ate chegar ao elemento
		Node<E> current = head;
		for (int i = 0; i < pos; i++)
			current = current.getNext();

		return current.getElement();
	}

	public int search(E element)
	{
		// percorre o encadeamento ate encontrar o elemento
		Node<E> current = head;
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

	// exercicio 3
	public SinglyLinkedList split(E divisor)
	{
		if (isEmpty())
			throw new UnderflowException();

		int pos = search(divisor);
		if (pos == -1)
			throw new IllegalArgumentException("Elemento nao existe");

		SinglyLinkedList<E> aux = new SinglyLinkedList<E>();
		for (int i = 0; i <= pos; i++)
			aux.insertLast(this.removeFirst());

		return aux;
	}

	public void trocaNodes()
	{
		if (isEmpty())
			throw new UnderflowException();

		E aux;
		Node<E> nodeAux;
		Node<E> current;

		if (numElements() >= 2) {
			current = head;
			nodeAux = current.getNext();
			aux = current.getElement();
			current.setElement(nodeAux.getElement());
			nodeAux.setElement(aux);

			for (int i = 1; i < numElements() / 2; i++) {
				nodeAux = nodeAux.getNext().getNext();
				current = current.getNext().getNext();

				aux = current.getElement();
				current.setElement(nodeAux.getElement());
				nodeAux.setElement(aux);
			}
		}
	}

	public void inverteLista()
	{
		if (isEmpty())
			throw new UnderflowException();

		SinglyLinkedList<E> aux = new SinglyLinkedList<E>();

		while (!this.isEmpty())
			aux.insertLast(this.removeLast());

		while (!aux.isEmpty())
			this.insertLast(aux.removeFirst());
	}

	public void removeNodes(int pos, int qtdNodes)
	{
		if (isEmpty())
			throw new UnderflowException();

		if (pos < 0 || pos > numElements() || qtdNodes > numElements() || qtdNodes < 0
						|| pos + qtdNodes > numElements())
			throw new ArrayIndexOutOfBoundsException();

		SinglyLinkedList<E> aux = new SinglyLinkedList<E>();

		int i = 0;
		while (i < pos) {
			E elem = this.removeFirst();
			aux.insertFirst(elem);
			i++;
		}

		for (int j = 0; j < qtdNodes; j++) {
			E elem = this.removeFirst();
			pos++;
		}

		while (!aux.isEmpty())
			this.insertFirst(aux.removeFirst());

	}

	public void insereLista(SinglyLinkedList<E> s1, int pos)
	{
		if (this.isEmpty() || s1.isEmpty())
			throw new UnderflowException();

		if (pos < 0 || pos > this.numElements())
			throw new ArrayIndexOutOfBoundsException();

		pos--;
		while (!s1.isEmpty())
			this.insert(s1.removeFirst(), pos++);

	}

	public void shuffle()
	{
		if (isEmpty())
			throw new UnderflowException();

		Random random = new Random();
		int n = this.numElements();

		for (int i = n - 1; i > 0; i--) {
			int r = random.nextInt(i + 1);
			E rand = (E) new Object();
			rand = this.get(r);

			E temp = (E) new Object();
			temp = this.get(i); // salva o conteudo apontado por i

			E a1 = (E) new Object();
			E a2 = (E) new Object();

			a1 = this.remove(i);
			this.insert(rand, i);
			a2 = this.remove(r);
			this.insert(temp, r);

		}
	}

	public String toString()
	{
		String s = "";

		Node<E> current = head;
		while (current != null) {
			s += current.getElement().toString() + " ";
			current = current.getNext();
		}
		return s;
	}
}
