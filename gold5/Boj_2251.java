package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    쿨통
    https://www.acmicpc.net/problem/2251
*/
public class Boj_2251 {
    static int A, B, C;
    static boolean[][] visited;
    static ArrayList<Integer> capacity;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        capacity = new ArrayList<>();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[A + 1][B + 1];
        dfs(0, 0, C);
        Collections.sort(capacity);
        for (int c : capacity) {
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int a, int b, int c) {
        if (visited[a][b]) return;
        if (a == 0) capacity.add(c);
        visited[a][b] = true;
        if (a > 0) {
            if (a + b <= B) dfs(0, a + b, c);
            else dfs(a + b - B, B, c);
            if (a + c <= C) dfs(0, b, a + c);
            else dfs(a + c - C, b, C);
        }
        if (b > 0) {
            if (a + b <= A) dfs(a + b, 0, c);
            else dfs(A, a + b - A, c);
            if (b + c <= C) dfs(a, 0, b + c);
            else dfs(a, b + c - C, C);
        }
        if (c > 0) {
            if (a + c <= A) dfs(a + c, b, 0);
            else dfs(A, b, a + c - A);
            if (b + c <= B) dfs(a, b + c, 0);
            else dfs(a, B, b + c - B);
        }
    }
  
    public static void main(String[] args) throws Exception {
        new Boj_2251().solution();
    }
}
