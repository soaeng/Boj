package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    최단경로
    https://www.acmicpc.net/problem/1753
*/
public class Boj_1753 {
    static int V;
    static ArrayList<ArrayList<int[]>> graph;
    static int[] dist;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dist = new int[V];
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine()) - 1;
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[]{v, w});
        }
        dijkstra(start);
        for (int d : dist) sb.append(d >= 3000000 ? "INF" : d).append("\n");
        System.out.print(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, 3000000);
        dist[start] = 0;
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (visited[node[0]]) continue;
            for (int[] v : graph.get(node[0])) {
                if (dist[v[0]] > dist[node[0]] + v[1]) {
                    dist[v[0]] = dist[node[0]] + v[1];
                    pq.offer(new int[]{v[0], dist[v[0]]});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
