//A Classic question on BST


import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/** Class for buffered reading int and double values */
class Reader {
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	/** call this method to initialize reader for InputStream */
	static void init(InputStream input) {
		reader = new BufferedReader(
					 new InputStreamReader(input) );
		tokenizer = new StringTokenizer("");
	}

	/** get next word */
	static String next() throws IOException {
		while ( ! tokenizer.hasMoreTokens() ) {
			//TODO add check for eof if necessary
			tokenizer = new StringTokenizer(
				   reader.readLine() );
		}
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt( next() );
	}
	
	static double nextDouble() throws IOException {
		return Double.parseDouble( next() );
	}
}

class Node {
	int val;
	Node left, right;

	Node(int v) {
		this.val = v;
		this.left = null;
		this.right = null;
	}
}

class BST {
	Node root;

	BST() {
		root = null;
	}

	void insert(Node curr, int x) {
		if (this.root == null) {
			root = new Node(x);
			return;
		}
		if (curr.val >= x) {
			if (curr.left == null) {
				curr.left = new Node(x);
			}
			else {
				insert(curr.left, x);
			}
		}
		else {
			if (curr.right == null) {
				curr.right = new Node(x);
			}
			else {
				insert(curr.right, x);
			}
		}
	}

	int solve(Node curr) {
		if (curr == null) {
			return 0;
		}
		return curr.val + solve(curr.left) + solve(curr.right);
	}

	void inorder(Node curr) {
		if (curr == null) {
			return;
		} 
		inorder(curr.left);
		System.out.println(solve(curr.left) + solve(curr.right));
		inorder(curr.right);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int n = Reader.nextInt();
		BST b = new BST();
		for (int i = 0; i < n; ++i) {
			int x = Reader.nextInt();
			b.insert(b.root, x);
		}
		b.inorder(b.root);
	}
}
