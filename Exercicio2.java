public class Exercicio2
{
	public static int somaElem(SinglyLinkedList<Integer> s)
	{
		return somaElem(s, 0, 0);
	}

	private static int somaElem(SinglyLinkedList<Integer> s, int i, int result)
	{
		if (i != s.numElements()) {
			result += s.get(i);
			i++;
			return somaElem(s, i, result);
		}

		return result;
	}

	public static void main(String[] args)
	{
		SinglyLinkedList<Integer> s = new SinglyLinkedList<Integer>();

		s.insertFirst(2);
		s.insertFirst(4);
		s.insertFirst(10);
		System.out.println(somaElem(s));
	}
}