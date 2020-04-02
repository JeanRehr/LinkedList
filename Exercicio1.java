public class Exercicio1
{
	public static int convertToInt(SinglyLinkedList<Integer> s)
	{
		String a = "";
		String b = "";
		for (int i = 0; i < s.numElements(); i++) {
			b = Integer.toString(s.get(i));
			a += b;
		}
		return Integer.parseInt(a);
	}

	public static void main(String[] args)
	{
		SinglyLinkedList<Integer> s = new SinglyLinkedList<Integer>();

		s.insertFirst(1);
		s.insertFirst(0);
		s.insertFirst(1);
		s.insertFirst(4);
		System.out.println(convertToInt(s));
	}
}