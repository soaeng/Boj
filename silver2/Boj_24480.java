package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    알고리즘 수업 - 깊이 우선 탐색 2
    https://www.acmicpc.net/problem/24480
*/
public class Boj_24480 {
    static int N, M, R, count = 1;
    static PriorityQueue<Integer>[] lists;
    static int[] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        lists = new PriorityQueue[N + 1];
        visited = new int[N + 1];
        for (int i = 1; i <= N; i++) lists[i] = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }
        dfs(R);
        for (int v : visited) sb.append(v).append("\n");
        sb.delete(0, 2);
        System.out.println(sb);
    }

    private static void dfs(int r) {
        visited[r] = count++;
        while (!lists[r].isEmpty()) {
            int n = lists[r].poll();
            if (visited[n] == 0) dfs(n);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_24480().solution();
    }
}
