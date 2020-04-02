public class Exercicio6
{
	public static Stack exercicio6(DoublyLinkedList<String> d1)
	{
		StaticStack<String> aux = new StaticStack<String>(d1.numElements());
		return exercicio6R(d1, aux, d1.numElements() - 1);
	}

	public static Stack exercicio6R(DoublyLinkedList<String> d1, Stack<String> aux,
					int i)
	{

		int limite = 0;
		if (i >= limite) {
			aux.push(d1.get(i));
			i--;
			return exercicio6R(d1, aux, i);
		}
		return aux;
	}

	public static void main(String[] args)
	{
		DoublyLinkedList<String> d1 = new DoublyLinkedList<String>();

		d1.insertFirst("a");
		d1.insertFirst("b");
		d1.insertFirst("d1a");
		d1.insertFirst("c");
		System.out.println(exercicio6(d1));
	}
}