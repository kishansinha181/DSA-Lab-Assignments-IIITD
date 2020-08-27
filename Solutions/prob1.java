//The approach is based on recursion

import java.util.*;

public class Main
{

    static int solve_down(int i, int j, int n, int[][] matrix)
    {
        if (i == 0 && j == 0) return matrix[i][j];
        int ans = (int)-1e5;
        if (i > 0 && i-1 >= j) ans = Math.max(ans, solve_down(i-1,j,n,matrix));
        if (j > 0) ans = Math.max(ans, solve_down(i,j-1,n,matrix));
        return ans + matrix[i][j];
    }

    static int solve_up(int i, int j, int n, int[][] matrix)
    {
        if (i == n-1 && j == n-1) return matrix[i][j];
        int ans = (int)-1e5;
        if (i+1 < n && i+1 <= j) ans = Math.max(ans, solve_up(i+1,j,n,matrix));
        if (j+1 < n) ans = Math.max(ans, solve_up(i,j+1,n,matrix));
        return ans + matrix[i][j];
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < n; ++j)
                matrix[i][j] = in.nextInt();
        }
        int ans1 = solve_down(n-1,n-1,n,matrix);
        int ans2 = solve_up(0,0,n,matrix);
        System.out.println(ans1+ans2);
    }
}

