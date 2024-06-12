package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연예인은 힘들어
    https://www.acmicpc.net/problem/17270
*/
public class Boj_17270 {
    static int V;
    static int[][] dist;

    static ArrayList<ArrayList<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        dist = new int[2][V];
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int J = Integer.parseInt(st.nextToken()) - 1;
        int S = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], INF);
            dijkstra(i, i == 0 ? J : S);
        }
        ArrayList<int[]> list = new ArrayList<>();
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < V; i++) {
            if (i == J) continue;
            if (i == S) continue;
            if (dist[0][i] == INF) continue;
            if (dist[1][i] == INF) continue;
            int time = dist[0][i] + dist[1][i];
            if (minTime < time) continue;
            if (minTime == time) {
                list.add(new int[]{i, dist[0][i]});
            } else {
                minTime = time;
                list.clear();
                list.add(new int[]{i, dist[0][i]});
            }
        }
        System.out.println(Arrays.toString(dist[0]));
        System.out.println(Arrays.toString(dist[1]));
        System.out.println(Arrays.toString(list.get(0)));
        if (list.isEmpty()) System.out.println(-1);
        else {
            list.sort((o1, o2) -> {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            });
            for (int[] candidate : list) {
                if (candidate[1] <= dist[1][candidate[0]]) {
                    System.out.println(list.get(0)[0] + 1);
                    return;
                }
            }
            System.out.println(-1);
        }
    }

    private static void dijkstra(int i, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[i][start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[i][node.v] < node.weight) continue;
            for (Node next : graph.get(node.v)) {
                if (dist[i][next.v] > dist[i][node.v] + next.weight) {
                    dist[i][next.v] = dist[i][node.v] + next.weight;
                    pq.offer(new Node(next.v, dist[i][next.v]));
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
