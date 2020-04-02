public class Exercicio4
{
	public static Stack numNegativo(SinglyLinkedList<Double> s1)
	{
		if (s1.isEmpty())
			throw new UnderflowException();

		StaticStack<Double> aux = new StaticStack<Double>(s1.numElements());
		int elems = s1.numElements();
		double temp;

		for (int i = 0; i < elems; i++) {
			temp = s1.removeLast();

			if (temp < 0.0)
				aux.push(temp);
			else
				s1.insertFirst(temp);
		}

		return aux;
	}

	public static Stack numNegativo1(SinglyLinkedList<Double> s1)
	{
		StaticStack<Double> aux = new StaticStack<Double>(s1.numElements());
		return numNegativoR(s1, 0, 0, aux);
	}

	private static Stack numNegativoR(SinglyLinkedList<Double> s1, int i, double temp,
					Stack<Double> aux)
	{

		if (i < s1.numElements()) {
			temp = s1.get(i);
			if (temp < 0) {
				aux.push(temp);
			}

			return numNegativoR(s1, ++i, temp, aux);
		}

		return aux;
	}

	public static void main(String[] args)
	{
		SinglyLinkedList<Double> s1 = new SinglyLinkedList<Double>();
		s1.insertFirst(2.0);
		s1.insertFirst(1.0);
		s1.insertFirst(0.0);
		s1.insertFirst(-1.0);
		s1.insertFirst(-2.0);

		System.out.println(s1);
		System.out.println(numNegativo1(s1));
	}
}