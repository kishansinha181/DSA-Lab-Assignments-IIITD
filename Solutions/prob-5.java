import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class portal{
	public int id;
	public int time;
	public portal(int universe,int duration) {
		this.id=universe;
		this.time=duration;
	}
}
class shortest_time{
	public int id;
	public long s_time;
	public shortest_time(int id,long time) {
		this.id=id;
		this.s_time=time;
	}
}
class The_Comparator implements Comparator<shortest_time> { 
    public int compare(shortest_time st1,shortest_time st2) 
    {  
    	if(st1.s_time>st2.s_time)
    		return 1;
    	return -1;
    } 
} 
public class hw_b {
	static ArrayList<portal>[] map;
	static long time[];
	static boolean visited[];
	public static void dijikstra(int n) {
		PriorityQueue<shortest_time> portals = new PriorityQueue<shortest_time>(new The_Comparator()); 		
		time[1]=0;
		shortest_time s = new shortest_time(1,0);
		portals.add(s);
		while(!portals.isEmpty() && !visited[portals.peek().id]) {
			//System.out.println("while -- loop");
			shortest_time top = portals.peek();
			portals.poll();
			visited[top.id]=true;
			time[top.id]=top.s_time;
			for(int i=0;i<map[top.id].size();i++) {
				portal neighbour =  map[top.id].get(i);
				if(!visited[neighbour.id]) {
					if(time[neighbour.id]>time[top.id]+neighbour.time) {
						time[neighbour.id] = time[top.id]+neighbour.time;
						shortest_time s1 = new shortest_time(neighbour.id,time[neighbour.id]);
						portals.add(s1);
					}
				}
			}
			while(!portals.isEmpty() && visited[portals.peek().id]) {
				portals.poll();
			}
		}
	}
	public static void preprocess(int n) {
		for(int i=1;i<=n;i++) {
			map[i] = new ArrayList<portal>();
			time[i]=Long.MAX_VALUE;//(long)(1e18);
			visited[i]=false;
		}
	}
	public static void main(String args[]) throws IOException {
		Reader.init(System.in);
		int n = Reader.nextInt();
		int m = Reader.nextInt();
		map = new ArrayList[n+1];
		time = new long[n+1];
		visited = new boolean[n+1];
		preprocess(n);
		while(m-->0) {
			int a = Reader.nextInt();
			int b =Reader.nextInt();
			int c = Reader.nextInt();
			portal p1 = new portal(a,c);
			portal p2 = new portal(b,c);
			map[a].add(p2);
			map[b].add(p1);
		}
		for(int i=0;i<n;i++) {
			int a = Reader.nextInt();
		}
		/*for(int i=1;i<=n;i++) {
			System.out.println("node no = "+i);
			for(int j=0;j<map[i].size();j++) {
				System.out.println(map[i].get(j).id+" , "+map[i].get(j).time);
			}
			System.out.println();
		}*/
		/*for(int i=1;i<=n;i++) {
			System.out.println(time[i]);
		}*/
		dijikstra(n);
		for(int i=1;i<=n;i++) {
			System.out.println(i+" , "+time[i]);
		}
		//System.out.println();
		if(time[n]==Long.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(time[n]);
		}
	}
}
