package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
    알고리즘 수업 - 깊이 우선 탐색 1
    https://www.acmicpc.net/problem/24479
*/
public class Boj_24479 {
    static int N, M, R, count = 1;
    static int[] visited;
    static ArrayList<Integer>[] lists;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        lists = new ArrayList[N + 1];
        visited = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(lists[i]);
        }
        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int v : visited) sb.append(v).append("\n");
        sb.delete(0, 2);
        System.out.println(sb);
    }

    private static void dfs(int r) {
        visited[r] = count++;
        for (int n : lists[r]) {
            if (visited[n] == 0) dfs(n);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_24479().solution();
    }
}
