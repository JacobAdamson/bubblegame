import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
public class LinkedList<T> implements Iterable<T> {
	

	private Node head;
	private Node tail;
	private int size;
	/**
	 * creates an empty linked list
	 */
	public LinkedList(){
		 head = null;
		 tail = null;
		 size = 0;
		 
	}
	
	/**
	 * clears the list
	 */
	public void clear(){
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * 
	 * @return the size of the linked list
	 */
	public int size(){
		return this.size;
	}
	/**
	 * adds the item to the list
	 * @param item object of type T
	 */
	public void add(T item){
		this.head = new Node(item, this.head);
		this.head.previous = null; //sets the previous to null
		
		if (this.head.next != null)//only if the next node exists
			this.head.next.previous = this.head;// makes the second item in the list point to the head
	
		this.size++;//increment size
		
		if(size == 1){//if there's only one item in the list
			this.tail = this.head;
		}
	}
	
	/**
	 * removes the specified object from the list
	 * returns true if object is removed, false otherwise
	 */
	public boolean remove(Object obj){
		int count = 0;
		
		//case where the removed object is the head
		if (obj == this.head.getThing()){
			this.head = this.head.next;
			this.head.previous = null;
			this.size--;
			return true;
		}
		
		//Removes that node if it is anything else
		Node current = this.head;
		for( int i = 0; i < this.size()-1; i++){
			if(current.next.getThing() == obj){
				current.next = current.next.next;
				
				if(current.next == tail)//makes the tail the last item
					this.tail = current;
				
				if(current.next != null)
					current.next.previous = current;//makes the next node point to the previous node
				this.size--;
				return true;
			}
			current = current.next;
		}
		
		
		return false;
	}
	
	/**
	 * returns an iterator for this cell list
	 */
	public Iterator iterator(){
		return new LLIterator(this.head);
	}
	
	/**
	 * returns a string representation of the list
	 */
	public String toString(){ 
	 return "The list"+head.generateString();
	}
	
	/**
	 * 
	 * @param index an integer
	 * @return the node at the index
	 */
	private Node getNode(int index){
		Node cur = this.head;
		for(int i = 0;i<index;i++)
			cur = cur.next;
		return cur;
	}
	
	/**
	 * 
	 * @param index
	 * @return the object at the index
	 */
	public T get(int index){
		LLIterator iterator = new LLIterator(this.head);
		for(int i = 0;i<index-1;i++)
			iterator.next();
		return iterator.next();
	}
	
	/**
	 * 
	 * @return an arraylist of type T with all the objects in the list
	 */
	public ArrayList<T> toArrayList(){
		
		ArrayList<T> returned = new ArrayList<T>();
		for(Iterator<T> i = this.iterator(); i.hasNext(); ) {	
			returned.add(i.next());
		}
		return returned;
	}
	
	/**
	 * 
	 * @return a shuffled arrayList of all the cells in the landscape
	 */
	public ArrayList<T> toShuffledList(){
		
		ArrayList<T> arr = this.toArrayList(); 
		
		
		Random r = new Random();	
		int a;
		int b;
		T temp;
		for( int i = 0; i< arr.size()*2;i++)// moves the cells around in the list by swapping two at a time
		{
			a = r.nextInt(arr.size());
			b = r.nextInt(arr.size());
			temp = arr.get(a);
			arr.set(a, arr.get(b));
			arr.set(b, temp);
			
			
		}
		
		return arr;
	}
		
	public static void main(String[] argv) {
	    LinkedList<Integer> llist = new LinkedList<Integer>();

		llist.add(5);
		llist.add(10);
		llist.add(20);
		System.out.println(llist);
		System.out.printf("\nAfter setup %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		llist.clear();

		System.out.printf("\nAfter clearing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}

	    for (int i=0;i<20;i+=2) {
			llist.add( i );
		}

		System.out.printf("\nAfter setting %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		ArrayList<Integer> alist = llist.toArrayList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}	
		
		System.out.println(llist.remove(18)+"  "+llist.size());//remove the last item
		System.out.println(llist.remove(4)+"  "+llist.size());//remove a standard item
		System.out.println(llist.remove(3)+"  "+llist.size());//remove something that isn't there
		System.out.println(llist.remove(0)+"  "+llist.size());//remove the first item
		System.out.printf("\nAfter removeing %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		

		alist = llist.toShuffledList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}
		
		
	}
	
	
	private class LLIterator implements Iterator<T>{
		Node current;
		public LLIterator(Node head){
			this.current = head;
		}

		public boolean hasNext(){
			return (this.current != null);
		}

		public T next(){
			T returned = this.current.getThing();
			this.current = this.current.getNext();
			return returned;
		}

		public void remove(){

		}



	}
	
	
	private class Node{

		private Node next;
		private T object;
		private Node previous;

		public Node(T item){
			this.object = item;
		}
		public Node(T item, Node next){
			this.next = next;
			this.object = item;
		}

		public T getThing(){
			return this.object;
		}

		public void setNext(Node n){
			this.next = n;
		}
		
		public void setPrevious(Node n){
			this.previous = n;
		}
		public Node getNext(){
			return this.next;
		}
		
		public String generateString(){
			if (this.getNext() != null)
				return this.getThing() + "\n"+this.getNext().generateString();
			else
				return ""+this.getThing();
			
		}
		
		public int getLength(){
			if (this.next != null)
				return 1+next.getLength();
			else return 0;
		}
	}

}
