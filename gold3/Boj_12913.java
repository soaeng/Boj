package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    매직 포션
    https://www.acmicpc.net/problem/12913
*/
public class Boj_12913 {
    static int N, K;
    static int[][] distances;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        distances = new int[N][N];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < N; c++) {
                distances[r][c] = (input.charAt(c) - '0') * 2;
            }
        }
        System.out.println(dijkstra() / 2);
    }

    private static double dijkstra() {
        int[][] dist = new int[K + 1][N];
        for (int k = 0; k <= K; k++) {
            Arrays.fill(dist[k], Integer.MAX_VALUE);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (int i = 0; i < N; i++) {
                if (node.num == i) continue;
                if (dist[node.k][i] > dist[node.k][node.num] + distances[node.num][i]) {
                    dist[node.k][i] = dist[node.k][node.num] + distances[node.num][i];
                    pq.offer(new Node(i, dist[node.k][i], node.k));
                }
                if (node.k < K) {
                    int nd = distances[node.num][i] / 2;
                    if (dist[node.k + 1][i] > dist[node.k][node.num] + nd) {
                        dist[node.k + 1][i] = dist[node.k][node.num] + nd;
                        pq.offer(new Node(i, dist[node.k][i], node.k + 1));
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) {
            min = Math.min(min, dist[k][1]);
        }
        return min;
    }

    private static class Node implements Comparable<Node> {
        int num, k;
        int distance;

        public Node(int num, int distance, int k) {
            this.num = num;
            this.distance = distance;
            this.k = k;
        }

        @Override
        public int compareTo(Node node) {
//            return Double.compare(distance, node.distance);
            return distance - node.distance;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
