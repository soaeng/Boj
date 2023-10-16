package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    통학버스            
    https://www.acmicpc.net/problem/2513
*/
public class Boj_2513 {
    static int N, K, S, dist;
    static PriorityQueue<Node> left;
    static PriorityQueue<Node> right;
    static Node bus;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        bus = new Node(S, 0);
        left = new PriorityQueue<>();
        right = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            if (p < S) left.add(new Node(p, cnt));
            else right.add(new Node(p, cnt));
        }
        dist = 0;
        pickUpStudent(left);
        pickUpStudent(right);
        System.out.println(dist);
    }

    private static boolean isRemain(Node apart) {
        if (apart.cnt / K >= 1) {
            dist += Math.abs(S - apart.p) * (apart.cnt / K) * 2;
            apart.cnt %= K;
        }
        return apart.cnt != 0;
    }

    private static void pickUpStudent(PriorityQueue<Node> nodes) {
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (isRemain(node)) {
                dist += Math.abs(bus.p - node.p);
                bus.p = node.p;
                bus.cnt = node.cnt;
                while (!nodes.isEmpty()) {
                    Node next = nodes.poll();
                    dist += Math.abs(next.p - bus.p);
                    bus.p = next.p;
                    if (next.cnt <= (K - bus.cnt)) {
                        bus.cnt += next.cnt;
                    } else {
                        next.cnt -= (K - bus.cnt);
                        if (next.cnt != 0) nodes.offer(next);
                        break;
                    }
                }
                goToSchool();
            } else {
                int cnt = node.cnt / K;
                dist += node.p * 2 * cnt;
            }
        }
    }

    private static void goToSchool() {
        dist += Math.abs(bus.p - S);
        bus.p = S;
        bus.cnt = 0;
    }

    private static class Node implements Comparable<Node> {
        int p, cnt, d;

        public Node(int p, int cnt) {
            this.p = p;
            this.cnt = cnt;
            d = Math.abs(S - p);
        }

        @Override
        public int compareTo(Node node) {
            return node.d - this.d;
        }

        @Override
        public String toString() {
            return "Apartment {" + p + ", " + cnt + "}";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2513().solution();
    }
}
