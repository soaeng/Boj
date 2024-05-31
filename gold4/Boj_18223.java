package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    민준이와 마산 그리고 건우
    https://www.acmicpc.net/problem/18223
*/
public class Boj_18223 {
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        dist = new int[V];
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken()) - 1;
        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        int PtoV = dijkstra(P, V - 1);
        int StoV = dijkstra(0, V - 1);
        System.out.println(StoV == dist[P] + PtoV ? "SAVE HIM" : "GOOD BYE");
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
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
        return dist[end];
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
