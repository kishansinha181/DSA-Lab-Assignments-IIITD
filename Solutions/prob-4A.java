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

class minHeap {
    public int capacity;
    public int [] mH;
    public int currentSize;
    public minHeap(int capacity){
        this.capacity=capacity;
        mH = new int [capacity+1];
       currentSize =0;
    }
    public void createHeap(int [] arrA){
        if(arrA.length>0){
            for(int i=0;i<arrA.length;i++){
                insert(arrA[i]);
            }
        }
    }
    public void display(){
        for(int i=1;i<mH.length;i++){
            System.out.print(" " + mH[i]);
        }
        System.out.println("");
    }
    public void insert(int x) {
        if(currentSize==capacity){
            System.out.println("heap is full");
            return;
        }
        currentSize++;
        int idx = currentSize;
        mH[idx] = x;
        bubbleUp(idx);
    }

    public void bubbleUp(int pos) {
        int parentIdx = pos/2;
        int currentIdx = pos;
        while (currentIdx > 0 && mH[parentIdx] > mH[currentIdx]) {

            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    public int extractMin() {
        int min = mH[1];
        mH[1] = mH[currentSize];
        mH[currentSize] = 0;
        sinkDown(1);
        currentSize--;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < heapSize() && mH[smallest] > mH[leftChildIdx]) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && mH[smallest] > mH[rightChildIdx]) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    public void swap(int a, int b) {
        int temp = mH[a];
        mH[a] = mH[b];
        mH[b] = temp;
    }
    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int heapSize(){
        return currentSize;
    }
}


public class a {

    public static void main(String args[]) throws IOException { 

        Reader.init(System.in);
        int n = Reader.nextInt();
        int k = Reader.nextInt();
        minHeap m = new minHeap(n);
        for(int i=0;i<n;++i) {
            int x = Reader.nextInt();
            m.insert(x);
        } 

        int cnt = 0;
        while(!m.isEmpty()) {
            int x1 = m.extractMin();

            if(m.isEmpty() || x1 >= k) {
                if(x1 < k) {
                    cnt = -1;
                } 
                break;
            }

            int x2 = m.extractMin();
            cnt += 1;
            // System.out.println(x1 + " " + x2);

            int z = x1+x2;
            m.insert(z);
        }   

        System.out.println(cnt);   
    }
}
