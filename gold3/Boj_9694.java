package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    무엇을 아느냐가 아니라 누구를 아느냐가 문제다
    https://www.acmicpc.net/problem/9694
*/
public class Boj_9694 {
    static int M;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static int[] route;
    static final int INF = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Integer> deque;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append("Case #").append(t).append(": ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            dist = new int[M];
            route = new int[M];
            for (int i = 0; i < M; i++) graph.add(new ArrayList<>());
            Arrays.fill(dist, INF);
            Arrays.fill(route, -1);
            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph.get(x).add(new Node(y, z));
                graph.get(y).add(new Node(x, z));
            }
            dijkstra();
            if (dist[M - 1] == INF) sb.append(-1);
            else {
                deque = new ArrayDeque<>();
                getRoute(M - 1);
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast()).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void getRoute(int from) {
        deque.offer(from);
        if (from == 0) return;
        getRoute(route[from]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;
        route[0] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > dist[node.v] + next.weight) {
                    dist[next.v] = dist[node.v] + next.weight;
                    route[next.v] = node.v;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
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
