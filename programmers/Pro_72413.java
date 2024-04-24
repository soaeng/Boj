package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
    합승 택시 요금
    https://school.programmers.co.kr/learn/courses/30/lessons/72413
*/
public class Pro_72413 {
    static int N;
    static ArrayList<ArrayList<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        graph = new ArrayList<>();
        N = n;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            graph.get(fare[0] - 1).add(new Node(fare[1] - 1, fare[2]));
            graph.get(fare[1] - 1).add(new Node(fare[0] - 1, fare[2]));
        }
        s--;
        a--;
        b--;
        int[] dist_s = dijkstra(s);
        int[] dist_a = dijkstra(a);
        int[] dist_b = dijkstra(b);
        for (int i = 0; i < n; i++) {
            if (i != s) dijkstra(i);
            answer = Math.min(answer, dist_s[i] + dist_a[i] + dist_b[i]);
        }
        return answer;
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.cost > dist[node.v]) continue;
            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > dist[node.v] + next.cost) {
                    dist[next.v] = dist[node.v] + next.cost;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }

    private static class Node implements Comparable<Node> {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        // 82
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        // 14
//        int n = 7, s = 3, a = 4, b = 1;
//        int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        //18
//        int n = 6, s = 4, a = 5, b = 6;
//        int[][] fares = {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}};

        System.out.println(new Pro_72413().solution(n, s, a, b, fares));
    }
}
