package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    등산
    https://www.acmicpc.net/problem/16681
*/
public class Boj_16681 {
    static int N;
    static int[] height;
    static ArrayList<ArrayList<Node>> graph;
    static final long INF = Long.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        height = new int[N];
        for (int i = 0; i < N; i++) height[i] = Integer.parseInt(st.nextToken()) * E;

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int n = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, n));
            graph.get(b).add(new Node(a, n));
        }

        long[][] dist = new long[2][N];
        for (int i = 0; i < 2; i++) dist[i] = dijkstra(i == 0 ? i : N - 1);

        long ans = Long.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (dist[0][i] == INF) continue;
            if (dist[1][i] == INF) continue;
            long value = height[i] - (dist[0][i] + dist[1][i]) * D;
            ans = Math.max(ans, value);
        }

        System.out.println(ans == Long.MIN_VALUE ? "Impossible" : ans);
    }

    private static long[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.v] < node.weight) continue;
            for (Node next : graph.get(node.v)) {
                if (height[next.v] <= height[node.v]) continue;
                if (dist[next.v] > dist[node.v] + next.weight) {
                    dist[next.v] = dist[node.v] + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }

    private static class Node implements Comparable<Node> {
        int v;
        long weight;

        public Node(int v, long weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(weight, node.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

