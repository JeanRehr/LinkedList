public class Exercicio7e8
{
	public static boolean exercicio7(DoublyLinkedList<Integer> d1)
	{
		return exercicio7(d1, 0, 1);
	}

	public static boolean exercicio7(DoublyLinkedList<Integer> d1, int prim, int seg)
	{
		if (d1.isEmpty())
			throw new UnderflowException();

		if (seg >= d1.numElements())
			return true;

		int a = d1.get(prim);
		int b = d1.get(seg);

		if (a <= b)
			return exercicio7(d1, ++prim, ++seg);

		return false;
	}

	public static int exercicio8(DoublyLinkedList<Integer> d1, int n)
	{
		return exercicio8(d1, 0, d1.numElements(), n);
	}

	public static int exercicio8(DoublyLinkedList<Integer> d1, int min, int max, int n)
	{
		if (d1.isEmpty())
			throw new UnderflowException();

		if (!exercicio7(d1))
			throw new IllegalArgumentException();

		if (max >= min) {
			int mid = min + (max - min) / 2;
			if (mid >= d1.numElements())
				return -1;

			if (d1.get(mid) == n)
				return d1.get(mid);

			if (d1.get(mid) > n)
				return exercicio8(d1, min, mid - 1, n);
			else
				return exercicio8(d1, mid + 1, max, n);
		}

		return -1;
	}

	public static void main(String[] args)
	{
		DoublyLinkedList<Integer> d1 = new DoublyLinkedList<Integer>();

		d1.insertFirst(10);
		d1.insertFirst(9);
		d1.insertFirst(8);
		d1.insertFirst(7);
		d1.insertFirst(6);
		d1.insertFirst(5);
		d1.insertFirst(4);
		d1.insertFirst(3);
		d1.insertFirst(2);
		d1.insertFirst(1);
		d1.insertFirst(-2);
		System.out.println(d1);
		System.out.println(exercicio8(d1, 2));
	}
}