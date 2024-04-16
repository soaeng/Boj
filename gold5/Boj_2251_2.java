package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    물통
    https://www.acmicpc.net/problem/2251
*/
public class Boj_2251_2 {
    static ArrayList<Integer> list;
    static int A, B, C;
    static boolean[][] visited;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[A + 1][B + 1];
        dfs(0, 0, C);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (int c : list) sb.append(c).append(" ");
        System.out.println(sb);
    }

    private static void dfs(int a, int b, int c) {
        if (visited[a][b]) return;
        visited[a][b] = true;
        if (a == 0) list.add(c);
        if (a > 0) {
            // A -> B
            if (a + b > B) dfs(a - (B - b), B, c);
            else dfs(0, a + b, c);
            // A -> C
            if (a + c > C) dfs(a - (C - c), b, C);
            else dfs(0, b, a + c);
        }
        if (b > 0) {
            // B -> A
            if (a + b > A) dfs(A, b - (A - a), c);
            else dfs(a + b, 0, c);
            // B -> C
            if (b + c > C) dfs(a, b - (C - c), C);
            else dfs(a, 0, b + c);
        }
        if (c > 0) {
            // C -> A
            if (a + c > A) dfs(A, b, c - (A - a));
            else dfs(a + c, b, 0);
            // C -> B
            if (b + c > B) dfs(a, B, c - (B - b));
            else dfs(a, b + c, 0);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
