package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    떡 돌리기
    https://www.acmicpc.net/problem/20007
*/
public class Boj_20007 {
    static int N, X;
    static int[] dist;
    static ArrayList<ArrayList<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        dist = new int[N];
        graph = new ArrayList<>();
        Arrays.fill(dist, INF);
        dist[Y] = 0;
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        dijkstra(Y);
        Arrays.sort(dist);
        if (dist[N - 1] * 2 > X) {
            System.out.println(-1);
            return;
        }
        int sum = 0;
        int day = 1;
        for (int d : dist) {
            if (sum + d * 2 <= X) sum += d * 2;
            else {
                day++;
                sum = d * 2;
            }
        }
        System.out.println(day);
    }

    private static void dijkstra(int Y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq.offer(new Node(Y, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.v]) continue;
            visited[node.v] = true;
            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > dist[node.v] + next.w) {
                    dist[next.v] = dist[node.v] + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node node) {
            return this.w - node.w;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
