package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    달빛 여우
    https://www.acmicpc.net/problem/16118
*/
public class Boj_16118 {
    static int N;
    static int[] distFox;
    static int[][] distWolf;
    static ArrayList<ArrayList<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        int M = Integer.parseInt(st.nextToken());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) * 2;
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        dijkstraFox();
        dijkstraWolf();
        System.out.println(getCount());
    }

    private static void dijkstraFox() {
        distFox = new int[N];
        Arrays.fill(distFox, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        distFox[0] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.weight > distFox[node.v]) continue;
            for (Node next : graph.get(node.v)) {
                if (distFox[next.v] > distFox[node.v] + next.weight) {
                    distFox[next.v] = distFox[node.v] + next.weight;
                    pq.offer(new Node(next.v, distFox[next.v]));
                }
            }
        }
    }

    private static void dijkstraWolf() {
        distWolf = new int[2][N];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(distWolf[i], INF);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, true));
        distWolf[0][0] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.weight > distWolf[node.isFastMode ? 0 : 1][node.v]) continue;
            for (Node next : graph.get(node.v)) {
                int nw;
                boolean nf = !node.isFastMode;
                if (nf) nw = next.weight * 2;
                else nw = next.weight / 2;
                if (distWolf[nf ? 0 : 1][next.v] > distWolf[!nf ? 0 : 1][node.v] + nw) {
                    distWolf[nf ? 0 : 1][next.v] = distWolf[!nf ? 0 : 1][node.v] + nw;
                    pq.offer(new Node(next.v, distWolf[nf ? 0 : 1][next.v], nf));
                }
            }
        }
    }

    private static int getCount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i])) count++;
        }
        return count;
    }

    private static class Node implements Comparable<Node> {
        int v, weight;
        boolean isFastMode;

        public Node(int v, int weight) {
            this(v, weight, false);
        }

        public Node(int v, int weight, boolean isFastMode) {
            this.v = v;
            this.weight = weight;
            this.isFastMode = isFastMode;
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
