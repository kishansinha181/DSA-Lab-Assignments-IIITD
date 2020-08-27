import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
    static double nextLong() throws IOException {
        return Long.parseLong( next() );
    }
}
public class hw_c {
	static int parity[];
	static int root[];
	static int size[];
	static boolean is_blocked[];
	public static void preprocess(int n) {
		parity = new int[n+1];
		root = new int[n+1];
		size = new int[n+1];
		is_blocked = new boolean[n+1];
		for(int i=1;i<=n;i++) {
			parity[i]=0;
			root[i]=i;
			size[i]=0;
			is_blocked[i]=true;
		}
	}
	public static int find_set(int i) {
		if(root[i]!=i) {
			int a = find_set(root[i]);
			int p = parity[a];
			parity[i] = (parity[i]+p+1)%2; 
		}
		return i;
	}
	public static void add_edge(int a,int b) {
		int a_index = find_set(a);
		a = root[a_index];
		int x = parity[a_index];
		int b_index = find_set(b);
		b = root[b_index];
		int y = parity[b_index];
		/*if(a_root==b_root && a_parity==b_parity) {
			is_blocked[a_index]=false;
		}
		else {
			if(size[a_index]<size[b_index]) {
				root[a_index]=b_index;
				parity[a_index]=b_parity^a_parity^1;
				is_blocked[b_index]  &= is_blocked[a_index]; 
				if(size[b_index]==size[a_index]) {
					size[b_index]+=1;
				}
			}
			else {
				root[b_index]=a_index;
				parity[b_index]=a_parity^b_parity^1;
				is_blocked[a_index]  &= is_blocked[b_index]; 
				if(size[a_index]==size[b_index]) {
					size[a_index]+=1;
				}
			}
		}*/
		if (a == b) {
	        if (x == y)
	            is_blocked[a] = false;
	    } else {
	        if (size[a] < size[b]) {
	        	int t = a;
	        	a = b;
	        	b = t;
	        }
	        //parent[b] = make_pair(a, x^y^1);
	        root[b]=a;
	        parity[b]=(x+y+1)%2;
	        is_blocked[a] &= is_blocked[b];
	        if (size[a] == size[b])
	            ++size[a];
	    }
	}
	public static boolean is_biparite(int a) {
		return is_blocked[root[find_set(a)]];
	}
	public static long find_gcd(long a , long b) {
		if(a==0) {
			return b;
		}
		return find_gcd(b%a,a);
	}
	public static void fraction_form(int i,int j,int w,int sv[]) {
		System.out.println("function");
		System.out.println(parity[i]);
		System.out.println(parity[j]);
		if(parity[i]==parity[j]){
			System.out.println("same parity");
			if(w<0) {
				System.out.print("-");
			}
		}
		else {
			System.out.println("diff parity");
			if(w>0) {
				System.out.print("-");
			}
		}
		long product = sv[i]*w;
		long gcd = find_gcd(product,sv[j]);
		long a = product/gcd;
		long b = sv[j]/gcd;
		System.out.println(a+"/"+b);
	}
	public static void main(String args[]) throws IOException{
		Reader.init(System.in);
		int n = Reader.nextInt();
		int q = Reader.nextInt();
		int special_value[] = new int[n+1];
		for(int i=1;i<=n;i++) {
			special_value[i]=Reader.nextInt();
		}
		preprocess(n);
		while(q-->0) {
			int a = Reader.nextInt();
			if(a==1) {
				int i = Reader.nextInt();
				int v = Reader.nextInt();
				special_value[i]=v;
			}
			else if(a==2) {
				int i = Reader.nextInt();
				int j = Reader.nextInt();
				add_edge(i,j);
			}
			else if(a==3) {
				int i = Reader.nextInt();
				int j = Reader.nextInt();
				int w = Reader.nextInt();
				//System.out.println(root[find_set(i)]);
				//System.out.println(root[find_set(j)]);
				//System.out.println(is_blocked[i]);
				//System.out.println(is_blocked[j]);
				//int pi = parity[i];
				//int pj = parity[j];
				/*if(!is_blocked[i] || !is_blocked[j] || (root[find_set_only(i)]!=root[find_set_only(j)])) {
					System.out.println(0);
				}
				else {
					fraction_form(i,j,w,special_value);
				}*/
				find_set(i);
				find_set(j);
				System.out.println("pi = "+parity[i]);
				System.out.println("pj = "+parity[j]);
				System.out.println("ri = "+root[find_set(i)]);
				System.out.println("rj = "+root[find_set(j)]);
				//System.out.println("display"+i+" , "+j+" , "+w);
			}
			for(int i=1;i<=n;i++) {
				System.out.print(parity[i]+" ");
			}
		}
	}
}
