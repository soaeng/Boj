package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
    등산코스 정하기
    https://school.programmers.co.kr/learn/courses/30/lessons/118669
*/
public class Pro_118669 {
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] isSummit;
    static final int INF = Integer.MAX_VALUE;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {0, INF};
        isSummit = new boolean[n];
        for (int summit : summits) isSummit[summit - 1] = true;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(new Node(path[1] - 1, path[2]));
            graph.get(path[1] - 1).add(new Node(path[0] - 1, path[2]));
        }
        int[] dist = dijkstra(n, gates);
        Arrays.sort(summits);
        for (int summit : summits) {
            if (answer[1] > dist[summit - 1]) {
                answer[0] = summit;
                answer[1] = dist[summit - 1];
            }
        }
        return answer;
    }

    private static int[] dijkstra(int n, int[] gates) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        for (int gate : gates) {
            dist[gate - 1] = 0;
            pq.offer(new Node(gate - 1, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.v] < node.weight) continue;
            if (isSummit[node.v]) continue;
            for (Node next : graph.get(node.v)) {
                int d = Math.max(dist[node.v], next.weight);
                if (dist[next.v] > d) {
                    dist[next.v] = d;
                    pq.offer(new Node(next.v, d));
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

    public static void main(String[] args) {
        // {5, 3}
//        int n = 6;
//        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
//        int[] gates = {1, 3};
//        int[] summits = {5};

        // {3, 4}
//        int n = 7;
//        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
//        int[] gates = {1};
//        int[] summits = {2, 3, 4};

        // {5, 1}
//        int n = 7;
//        int[][] paths = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
//        int[] gates = {3, 7};
//        int[] summits = {1, 5};

        // {5, 6}
        int n = 5;
        int[][] paths = {{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}};
        int[] gates = {1, 2};
        int[] summits = {5};

        System.out.println(Arrays.toString(new Pro_118669().solution(n, paths, gates, summits)));
    }
}
