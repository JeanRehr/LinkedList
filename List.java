public interface List<E> {

	// Informa a quantidade de elementos armazenados na lista.
	public int numElements();

	public boolean isEmpty();

	public boolean isFull();

	public void insert(E element, int pos);

	public E remove(int pos);

	public E get(int pos);

	 // Localiza a primeira ocorrencia do elemento indicado na lista.
	public int search(E element);
}
