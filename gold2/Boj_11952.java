package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    좀비
    https://www.acmicpc.net/problem/11952
*/
public class Boj_11952 {
    static int N, S, p, q;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] zombie, dangerous;
    static ArrayDeque<Integer> deque = new ArrayDeque<>();

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        graph = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        zombie = new boolean[N];
        dangerous = new boolean[N];
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        while (K-- > 0) {
            zombie[Integer.parseInt(br.readLine()) - 1] = true;
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        } // end of input
        setDangerous();
        long cost = dijkstra();
        System.out.println(dangerous[N - 1] ? cost - q : cost - p);
    }

    private static long dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N];

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (dist[node.v] < node.weight) continue;
            for (int next : graph.get(node.v)) {
                if (zombie[next]) continue;
                int cost = dangerous[next] ? q : p;
                if (dist[next] > dist[node.v] + cost) {
                    dist[next] = dist[node.v] + cost;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }
        return dist[N - 1];
    }

    private static void setDangerous() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (zombie[i]) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                for (int next : graph.get(node)) {
                    if (dangerous[next]) continue;
                    queue.offer(next);
                    dangerous[next] = true;
                }
            }
            if (--S == 0) return;
        }
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
