 // Implementacao de uma Pilha com armazenamento estatico,
 // baseado em array.

public class StaticStack<E> implements Stack<E> {
	// Indice do elemento no topo da pilha
	protected int top;

	// Array que armazena as referencias para os elementos
	protected E elements[];

	@SuppressWarnings("unchecked")
	public StaticStack(int maxSize) {
		elements = (E[])new Object[maxSize];
		top = -1;
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public boolean isFull() {
		return top == elements.length - 1;
	}

	public int numElements() {
		return top + 1;
	}

	public void push(E element) throws OverflowException {
		if (isFull())
			throw new OverflowException();

		elements[++top] = element;
	}

	public E pop() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();

		E element = elements[top];
		elements[top--] = null; // p/ coleta de lixo
		return element;
	}

	public E top() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();

		return elements[top];
	}

	public void clear() throws UnderflowException {
		if (isEmpty())
			throw new UnderflowException();

		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
	}

	public void invertePilha(StaticStack<E> s1) {
		StaticStack<E> aux1 = new StaticStack<E>(s1.numElements());
		StaticStack<E> aux2 = new StaticStack<E>(s1.numElements());

		while (!s1.isEmpty())
			aux1.push(s1.pop());
		while (!aux1.isEmpty())
			aux2.push(s1.pop());
		while (!aux2.isEmpty())	
			s1.push(aux2.pop());
	}

	public int elementoPos(StaticStack<E> s1, E elem) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(elem))
				return i;
		}

		int a1;
		
		return -1;
	}

	public boolean contains(E elem) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].equals(elem))
				return true;
		}

		return false;
	}

	public void flip() {
		StaticStack<E> aux1 = new StaticStack<E>(this.numElements());
		
		while (!this.isEmpty())
			aux1.push(this.pop());
		while (!aux1.isEmpty())
			this.push(aux1.pop());
	}

	public void push(StaticStack<E> s1) {
		StaticStack<E> aux1 = new StaticStack<E>(this.numElements());
		
		while (!s1.isEmpty())
			aux1.push(s1.pop());
		while (!this.isEmpty())
			this.push(aux1.pop());
	}

	public boolean equals(StaticStack<E> s1) {
		if (this.numElements() != s1.numElements())
			return false;

		StaticStack<E> aux1 = new StaticStack<E>(this.numElements());
		StaticStack<E> aux2 = new StaticStack<E>(s1.numElements());

		@SuppressWarnings("unchecked")
		E a1 = (E) new Object();
		@SuppressWarnings("unchecked")
		E a2 = (E) new Object();

		boolean flag = true;

		while (!this.isEmpty()) {
			a1 = this.pop();
			a2 = s1.pop();

			if (!a1.equals(a2))
				flag = false;	

			aux1.push(a1);
			aux2.push(a2);
		}

		while (!aux1.isEmpty())
			this.push(aux1.pop());

		while (!aux2.isEmpty())
			s1.push(aux2.pop());

		return flag;
	}

	public StaticStack<E> clone() {
		StaticStack<E> aux1 = new StaticStack<E>(this.numElements());
		StaticStack<E> aux2 = new StaticStack<E>(this.numElements());
		@SuppressWarnings("unchecked")
		E a1 = (E) new Object();

		while(!this.isEmpty()) {
			a1 = this.pop();

			aux1.push(a1);
			aux2.push(a1);
		}

		while(!aux2.isEmpty())
			this.push(aux2.pop());

		aux1.flip();
		
		return aux1;
	}

	public boolean checkBrackets(Stack<Character> s1) {
		StaticStack<Character> aux1 = new StaticStack<Character>(s1.numElements());
		StaticStack<Character> aux2 = new StaticStack<Character>(s1.numElements());
		int a = 0;
		int b = 0;
		char c = '0';
		boolean flag = false;

		while (!s1.isEmpty()) {
			c = s1.pop();
			aux1.push(c);
			aux2.push(c);
			if (c == '(')
				a++;
			if (c == ')')
				a--;
		}

		if (a != 0)
			return false;

		int i = 0;
		while (!aux1.isEmpty()) {
			c = aux1.pop();
			if (c == ')')
				a = i;
			if (c == '(')
				b = i;
			i++;
			if (a > b)
				flag = true;
		}

		return flag;
	}
	public boolean checkBrackets1(Stack<Character> s1)
	{
		if (s1.isEmpty())
			throw new OverflowException();

		Stack<Character> aux = new StaticStack<Character>(s1.numElements());

		while (!s1.isEmpty()) {
			char c = s1.pop();
			if (c == ')')
				aux.push(s1.pop());
			else if (c == '(') {
				if(aux.isEmpty())
					return false;
				aux.pop();
			}
		}
		return true;
	}

	// Retorna uma representacao da pilha
	public String toString() {
		if (isEmpty())
			return "[Empty]";

		String s = "[";
		for (int i = numElements() - 1; i >= 0; i--)
			s += "\n" + elements[i];

		s += "\n]";
		return s;
	}
}