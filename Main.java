public class Main
{
	public static void main(String[] args) {
		//SinglyLinkedList<Character> a = new SinglyLinkedList<Character>();
		DoublyLinkedList<Character> a = new DoublyLinkedList<Character>();

		a.insertFirst('F');
		a.insertFirst('E');
		a.insertFirst('D');
		a.insertFirst('C');
		a.insertFirst('B');
		a.insertFirst('A');

		System.out.println(a);
		a.swap(0, 0);
		System.out.println(a);
	}

}