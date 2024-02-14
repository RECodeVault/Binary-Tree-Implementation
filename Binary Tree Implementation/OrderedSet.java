public interface OrderedSet<E> {	
	public void add(E data);
	public boolean contains(E data);
	public E first();
	public E next();
	public int size();
}