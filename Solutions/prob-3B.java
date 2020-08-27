import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
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
    int key, height; 
    Node left, right; 
  
    Node(int d) { 
        key = d; 
        height = 1; 
    } 
} 
  
class AVLTree { 
  
    Node root; 
    int l_rot, r_rot;

    AVLTree() {
        root = null;
        l_rot = 0;
        r_rot = 0;
    }
  
    int height(Node N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
  
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    Node rightRotate(Node y) { 
        r_rot += 1;
        Node x = y.left; 
        Node T2 = x.right; 
  
        x.right = y; 
        y.left = T2; 
  
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        return x; 
    } 
  
    Node leftRotate(Node x) { 
        l_rot += 1;
        Node y = x.right; 
        Node T2 = y.left; 
  
        y.left = x; 
        x.right = T2; 
  
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        return y; 
    } 
  
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
  
    Node insert(Node node, int key) { 
  
        if (node == null) 
            return (new Node(key)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key); 
        else if (key > node.key) 
            node.right = insert(node.right, key); 
        else
            return node; 
  
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        int balance = getBalance(node); 
  
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node); 
  
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        return node; 
    } 

    int parent(Node node, int key, int par) {  
        if (node == null) 
            return -1; 
  
        if (key < node.key) 
            return parent(node.left, key, node.key);
        
        else if (key > node.key) 
            return parent(node.right, key, node.key);

        else
            return par; 
    }    
}

public class Main {

    public static void main(String args[]) throws IOException { 
    
        Reader.init(System.in);
        AVLTree tree = new AVLTree(); 

        int q = Reader.nextInt();

        for(int i = 0; i < q; ++i) {
            String type = Reader.next();

            if(type.equals("ADD")) {
                int x = Reader.nextInt();
                tree.root = tree.insert(tree.root, x);
            }
            else if(type.equals("COUNT")) {
                String c = Reader.next();
                if(c.equals("L")) {
                    System.out.println(tree.l_rot);
                }
                else {
                    System.out.println(tree.r_rot);
                }
            }
            else {
                int x = Reader.nextInt();
                int par = tree.parent(tree.root, x, 0);
                System.out.println(par);
            }
        }
    }
}
