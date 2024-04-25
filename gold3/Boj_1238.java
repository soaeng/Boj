package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    파티
    https://www.acmicpc.net/problem/1238
*/
public class Boj_1238 {
    static int N, X;
    static ArrayList<ArrayList<Node>> graph, reverse;
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();
        reverse = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            reverse.get(v).add(new Node(u, w));
        }
        int[] dist_g = dijkstra(true);
        int[] dist_r = dijkstra(false);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dist_g[i] + dist_r[i]);
        }
        System.out.println(max);
    }

    private static int[] dijkstra(boolean flag) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        pq.offer(new Node(X, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Node next : flag ? graph.get(node.v) : reverse.get(node.v)) {
                if (dist[next.v] > dist[node.v] + next.weight) {
                    dist[next.v] = dist[node.v] + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist;
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
