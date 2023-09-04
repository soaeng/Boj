package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    알고리즘 수업 - 너비 우선 탐색 1
    https://www.acmicpc.net/problem/24444
*/
public class Boj_24444 {
    static int N, M, R, count = 1;
    static ArrayList<Integer>[] lists;
    static int[] visited;

    public void solution() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        lists = new ArrayList[N + 1];
        visited = new int[N + 1];
        for (int i = 1; i <= N; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }

        for (int i = 1; i <= N; i++) Collections.sort(lists[i]);
        visited[R] = count++;
        bfs();
        for (int v : visited) sb.append(v).append("\n");
        sb.delete(0, 2);
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>(lists[R]);
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (visited[n] == 0) {
                visited[n] = count++;
                queue.addAll(lists[n]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_24444().solution();
    }
}
