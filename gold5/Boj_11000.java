package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    강의실 배정
    https://www.acmicpc.net/problem/11000
*/
public class Boj_11000 {
    static int N;
    static ArrayList<Node> list;
    static PriorityQueue<Node> pq;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.t));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.add(new Node(s, t));
        }
        Collections.sort(list);
        for (Node n : list) {
            if (!pq.isEmpty() && pq.peek().t <= n.s) pq.poll();
            pq.offer(n);
        }
        System.out.println(pq.size());
    }

    private static class Node implements Comparable<Node> {
        int s, t;

        public Node(int s, int t) {
            this.s = s;
            this.t = t;
        }

        @Override
        public String toString() {
            return "(" + s + ", " + t + ")";
        }

        @Override
        public int compareTo(Node o) {
            if (this.s == o.s) return this.t - o.t;
            else return this.s - o.s;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_11000().solution();
    }
}
