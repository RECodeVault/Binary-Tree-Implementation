import java.util.Random;

public class Driver {
	public static void main(String[] args) {
		
		LinkedBST<Integer> bst = new LinkedBST<Integer>();
		boolean bstError = false;
		
		int n = 10;
		int seed = -1;
		boolean setSeed = false;
		boolean debug = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--debug")) debug = true;
			if (args[i].startsWith("--n=")) {
				String[] parts = args[i].split("=");
				n = Integer.parseInt(parts[1]);
			}
			if (args[i].startsWith("--seed=")) {
			   String[] parts = args[i].split("=");
			   seed = Integer.parseInt(parts[1]);
			   setSeed = true;
			}
		}
		
		Random rand;
		if (setSeed) rand = new Random( seed );
		else rand = new Random();

		System.out.println("****** BEGIN INSERTION ******");
		for (int i = 0; i < n; i++) {
			int data = debug ? rand.nextInt(100) : rand.nextInt();
			if (!bstError) {
				try {
					bst.add(data);
				}
				catch (Exception e) {
					System.out.println("Error inserting into BST.");
					bstError = true;
				}
			}
			
		}
		System.out.println("****** END INSERTION ******\n");

		if (!bstError) {
			System.out.println("****** BEGIN BST ORDER TEST ******");
			if (debug) bst.sortedTraversal();
			Integer last = null, next = null;
			try {
				last = bst.first();
			}
			catch (Exception e) {
				System.out.println("ERROR! Exception when calling BST first():");
				e.printStackTrace();
				bstError = true;
			}
			while (!bstError && last != null) {
				try {
					next = bst.next();
				}
				catch (Exception e) {
					System.out.println("ERROR! Exception when calling BST next():");
					e.printStackTrace();
					bstError = true;
				}
				if (next != null) {
					if (next.compareTo(last) < 0) {
						System.out.println("ERROR! " + last + " came before " + next);
						bstError = true;
					} else if (next.compareTo(last) == 0) {
						System.out.println("ERROR! Duplicate entries for " + last);
						bstError = true;
					}
				}
				last = next;
			}
			System.out.println("****** END BST ORDER TEST ******");
			System.out.println("BST height: " + bst.height());
			System.out.println("BST size: " + bst.size());
			System.out.println();
		}
	}
}
