package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    두 로봇
    https://www.acmicpc.net/problem/15971
*/
public class Boj_15971 {
    static int N;
    static ArrayList<ArrayList<int[]>> graph;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int R1 = Integer.parseInt(st.nextToken()) - 1;
        int R2 = Integer.parseInt(st.nextToken()) - 1;
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            graph.get(u).add(new int[]{v, d});
            graph.get(v).add(new int[]{u, d});
        }
        System.out.println(bfs(R1, R2));
    }

    private static int bfs(int start, int end) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, 0, 0));
        boolean[] visited = new boolean[N];
        visited[start] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.v == end) return node.distance - node.max;
            for (int[] next : graph.get(node.v)) {
                if (visited[next[0]]) continue;
                queue.offer(new Node(next[0], node.distance + next[1], Math.max(node.max, next[1])));
                visited[next[0]] = true;
            }
        }
        return -1;
    }

    private static class Node implements Comparable<Node> {
        int v, distance, max;

        public Node(int v, int distance, int max) {
            this.v = v;
            this.distance = distance;
            this.max = max;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
