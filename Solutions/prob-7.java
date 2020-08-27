import java.util.ArrayList;
import java.util.Scanner;
public class hw_d {
	static ArrayList<Integer>[] map;
	public static void dfs(int a,int n,long d[],long cur,boolean visited[]) {
		d[a]=cur;
		visited[a]=true;
		for(int i=0;i<map[a].size();i++) {
			if(!visited[map[a].get(i)]) {
				dfs(map[a].get(i),n,d,cur+1,visited);
			}
		}
	}
	public static int find_farthest_hotspot(long dist_1[],int n,boolean hotspots[]) {
		long max = -1;
		int index = 0;
		for(int i=1;i<=n;i++) {
			if(hotspots[i]) {
				if(dist_1[i]>max) {
					index = i;
					max = dist_1[i];
				}
			}
		}
		return index;
	}
	public static int find_total_hotspots(long dist_1[],long dist_2[],int x,int n) {
		int count = 0;
		for(int i=1;i<=n;i++) {
			if(dist_1[i]<=x && dist_2[i]<=x) {
				count++;
			}
		}
		return count;
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int h = in.nextInt();
		int x = in.nextInt();
		boolean hotspots[] = new boolean[n+1];
		for(int i=1;i<=n;i++)
			hotspots[i]=false;
		for(int i=0;i<h;i++) {
			hotspots[in.nextInt()]=true;
		}
		map = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			map[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<n-1;i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			map[a].add(b);
			map[b].add(a);
		}
		long dist_1[] = new long[n+1];
		boolean visited[] = new boolean[n+1];
		dfs(1,n,dist_1,0,visited);
		int hotspot_1 = find_farthest_hotspot(dist_1,n,hotspots);
		dist_1 = new long[n+1];
		visited = new boolean[n+1];
		dfs(hotspot_1,n,dist_1,0,visited);
		int hotspot_2 = find_farthest_hotspot(dist_1,n,hotspots);
		dist_1 = new long[n+1];
		visited = new boolean[n+1];
		long dist_2[] = new long[n+1];
		dfs(hotspot_1,n,dist_1,0,visited);
		visited = new boolean[n+1];
		dfs(hotspot_2,n,dist_2,0,visited);
		System.out.println(find_total_hotspots(dist_1,dist_2,x,n));
	}
}
