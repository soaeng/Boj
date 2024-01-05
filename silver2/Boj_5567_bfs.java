package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    결혼식
    https://www.acmicpc.net/problem/5567
*/
public class Boj_5567_bfs {
    static int n;
    static boolean[] visited;
    static ArrayList<Integer>[] friends;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        friends = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            friends[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            friends[u].add(v);
            friends[v].add(u);
        }
        bfs();
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (visited[i]) count++;
        }
        System.out.println(count);
    }

    private static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        visited[0] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (count == 2) break;
            count++;
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int j = 0; j < n; j++) {
                    if (visited[j]) continue;
                    if (!friends[u].contains(j)) continue;
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_5567_bfs().solution();
    }
}
