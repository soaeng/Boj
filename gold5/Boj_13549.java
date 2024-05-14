package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    숨바꼭질 3
    https://www.acmicpc.net/problem/13549
*/
public class Boj_13549 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        final int INF = 100001;
        boolean[] visited = new boolean[INF];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(N, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.n] = true;
            if (node.n == K) {
                System.out.println(node.t);
                return;
            }
            int nx = node.n * 2;
            if (nx < INF && !visited[nx]) pq.offer(new Node(nx, node.t));
            nx = node.n + 1;
            if (nx < INF && !visited[nx]) pq.offer(new Node(nx, node.t + 1));
            nx = node.n - 1;
            if (nx >= 0 && !visited[nx]) pq.offer(new Node(nx, node.t + 1));
        }
    }

    private static class Node implements Comparable<Node> {
        int n, t;

        public Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        @Override
        public int compareTo(Node node) {
            return this.t - node.t;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
