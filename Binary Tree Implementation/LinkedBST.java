public class LinkedBST<E extends Comparable<E>> implements OrderedSet<E> {
	
	protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;
		
		

		protected Node(E data, Node<E> parent) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.parent = parent;
		}
		
		protected Node(E data) {
			this(data, null);
		}
		
        public E getData(E data) {
            return data;
        }
	}
	
	protected Node<E> root;
	protected Node<E> current;
	protected int size;

    public LinkedBST() {
        this.root = null;
        this.current = null;
        this.size = 0;
    }


	public int size() {
		return size;
	}
	
	public void add(E data) {
		if (root == null) {
			root = new Node<E>(data);
			size++;
		} else {
			bstAdd(root, data);
		}
	}
	
	protected void bstAdd(Node<E> n, E data) {		
		int compare = data.compareTo(n.data);
		if (compare == 0) return;
		else if (compare < 0) {
			if (n.left != null) bstAdd(n.left, data);
			else {
				Node<E> newNode = new Node<E>(data, n);
				n.left = newNode;
				size++;
			}
		} else {
			if (n.right != null) bstAdd(n.right, data);
			else {
				Node<E> newNode = new Node<E>(data, n);
				n.right = newNode;
				size++;
			}
		}
	}

    @Override
    public boolean contains(E data) {
        return search(this.root, data);
    }

    private boolean search(Node<E> root, E data) {
        if (root == null) {
            return false;
        } 
        else {
            int compare = data.compareTo(root.data);
            if (compare == 0) {
                return true;
            }
            else if (compare < 0) {
                return search(root.left, data);
            }
            else {
                return search(root.right, data);
            }
        }
    }

    @Override
    public E first() {
        if (root == null) {
    		return null;
    	}
    	current = root;
        while (current.left != null) {
        	current = current.left;
        }
		return current.data;
    }   

    @Override
    public E next() {
		if (root == null) return null;
        return nextHelper();
    }
    private E nextHelper() {
        if (root == findMax(root) || current == null) {
			return null;
		}
		if (current.right != null) {
			current = current.right;
			while (current.left != null) {
				current = current.left;
			}
			return current.data;
		} else {
			Node<E> nextNode = current.parent;
			while (nextNode != null && current == nextNode.right) {
				current = nextNode;
				nextNode = nextNode.parent;
			}
			current = nextNode;
			return current != null ? current.data : null;
		}
	}

    private E findMax(Node <E> root) {
		Node <E> maxNode = root;
        while(maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode.data;
    }

	public int height() {
		return height(root);
	}
	
	protected int height(Node<E> n) {
		if (n == null) return 0;
		else return 1 + Math.max(height(n.left), height(n.right));
	}

    public void sortedTraversal() {
        System.out.println(sortedTraversalHelper(root));
    }

    private String sortedTraversalHelper(Node <E> root) {
        if (root == null) {
           return ""; 
        }
        String result = "";
        result += sortedTraversalHelper(root.left);
        result += root.data + " ";
        result += sortedTraversalHelper(root.right);
        return result;
    }

}