package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    직장인 파댕이의 사회생활
    https://www.acmicpc.net/problem/30985
*/
public class Boj_30985 {
    static int N;
    static ArrayList<ArrayList<Node>> graph;
    static long[] elevator;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        elevator = new long[N];
        long[][] dist = new long[2][N];
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, c));
            graph.get(v).add(new Node(u, c));
        }
        K--;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) elevator[i] = Long.parseLong(st.nextToken()) * K;

        for (int i = 0; i < 2; i++) dist[i] = dijkstra(i == 0 ? i : N - 1);

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (elevator[i] < 0) continue;
            if (dist[0][i] == Long.MAX_VALUE) continue;
            if (dist[1][i] == Long.MAX_VALUE) continue;
            ans = Math.min(ans, dist[0][i] + dist[1][i] + elevator[i]);
        }
        System.out.println(ans == Long.MAX_VALUE ? -1 : ans);
    }

    private static long[] dijkstra(int start) {
        long[] dist = new long[N];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Long.MAX_VALUE);
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.v] < node.weight) continue;
            for (Node next : graph.get(node.v)) {
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
            return Long.compare(this.weight, node.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
