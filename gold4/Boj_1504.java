package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    특정한 최단 경로
    https://www.acmicpc.net/problem/1504
*/
public class Boj_1504 {
    static ArrayList<ArrayList<Node>> graph;
    static int N, INF;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        INF = 200_000_001;
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()) - 1;
        int v2 = Integer.parseInt(st.nextToken()) - 1;
        int ans = INF;
        int a = dijkstra(0, v1) + dijkstra(v1, v2) + dijkstra(v2, N - 1);
        int b = dijkstra(0, v2) + dijkstra(v2, v1) + dijkstra(v1, N - 1);
        ans = Math.min(ans, Math.min(a, b));
        System.out.println(ans >= INF ? -1 : ans);
    }

    private static int dijkstra(int start, int tgt) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > dist[node.v] + next.weight) {
                    dist[next.v] = dist[node.v] + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist[tgt];
    }

    private static class Node implements Comparable<Node> {
        int v, weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
