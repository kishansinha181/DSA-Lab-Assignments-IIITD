import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) throws IOException { 

        Reader.init(System.in);
        int n = Reader.nextInt();
        int d = Reader.nextInt();
        
        Trainer trainers[] = new Trainer[n];
        for(int i=0;i<n;++i) {
            long di = Reader.nextInt();
            long ti = Reader.nextInt();
            long si = Reader.nextInt();
            trainers[i] = new Trainer(di, ti, si);
        }

        merge_sort(trainers, 0 , n-1);
        int idx = 0;
        maxHeap m = new maxHeap(n);
        
        for (int i = 1; i <= d; i++) {
            while (idx < n && trainers[idx].day == i) {
                m.insert(trainers[idx]);
                idx += 1;
            }
            Trainer tmp = m.extractMax();
            if (tmp == null)
                continue;
            tmp.lectures -= 1;
            
            if (tmp.lectures > 0) {
                m.insert(tmp);
            }
        }

        System.out.println(m.getCurse());
    }

    public static void merge(Trainer arr[], int l, int m, int r) 
    { 
        int n1 = m - l + 1; 
        int n2 = r - m;
        
        Trainer L[] = new Trainer [n1]; 
        Trainer R[] = new Trainer [n2]; 
  
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 

        int i = 0, j = 0;

        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i].day <= R[j].day) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    public static void merge_sort(Trainer arr[], int l, int r)
    {
        if (l < r)
        {
            int m = (l+r)/2;
            merge_sort(arr, l, m);
            merge_sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }
}

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

class Trainer {
    public long day, lectures, curse;
    public Trainer(long day, long lectures, long curse) {
        this.day = day;
        this.lectures = lectures;
        this.curse = curse;
    }
}

class maxHeap {
    public int capacity;
    public Trainer [] mH;
    public int currentSize;
    public maxHeap(int capacity){
        this.capacity=capacity;
        mH = new Trainer [capacity+1];
       currentSize =0;
    }
    public void createHeap(Trainer [] arrA){
        if(arrA.length>0){
            for(int i=0;i<arrA.length;i++){
                insert(arrA[i]);
            }
        }
    }
    public void insert(Trainer x) {
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
        while (parentIdx > 0 && mH[parentIdx].curse < mH[currentIdx].curse) {

            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    public Trainer extractMax() {
        Trainer min = mH[1];
        if (currentSize == 0)
            return null;
        mH[1] = mH[currentSize];
        mH[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < heapSize() && mH[smallest].curse < mH[leftChildIdx].curse) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < heapSize() && mH[smallest].curse < mH[rightChildIdx].curse) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            swap(k, smallest);
            sinkDown(smallest);
        }
    }
    public long getCurse(){
        long curse = 0;
        for(int i=1;i<mH.length;i++){
            if (mH[i] == null)
                break;
            curse += (mH[i].lectures*mH[i].curse);
        }
        return curse;
    }
    public void swap(int a, int b) {
        Trainer temp = mH[a];
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
