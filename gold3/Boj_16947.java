package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    서울 지하철 2호선
    https://www.acmicpc.net/problem/16947
*/
public class Boj_16947 {
    static int N;
    static boolean[] cycle;
    static int[] dist;
    static ArrayList<Integer>[] lists;
    static Queue<Integer> queue = new ArrayDeque<>();

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        lists = new ArrayList[N];
        dist = new int[N];
        for (int i = 0; i < N; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            lists[u].add(v);
            lists[v].add(u);
        }
        for (int i = 0; i < N; i++) {
            cycle = new boolean[N];
            if (dfs(i, -1, i)) break;
        }
        for (int i = 0; i < N; i++) {
            if (cycle[i]) queue.offer(i);
            else dist[i] = -1;
        }
        bfs();
        for (int d : dist) sb.append(d).append(" ");
        System.out.println(sb);
    }

    private static boolean dfs(int root, int from, int node) {
        cycle[node] = true;
        for (int to : lists[node]) {
            if (!cycle[to]) {
                if (dfs(root, node, to)) return cycle[to] = true;
            } else if (root == to && from != to) return cycle[to] = true;
        }
        return cycle[node] = false;
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int to : lists[node]) {
                if (dist[to] != -1) continue;
                dist[to] = dist[node] + 1;
                queue.offer(to);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
