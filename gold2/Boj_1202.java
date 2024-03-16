package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    보석 도둑
    https://www.acmicpc.net/problem/1202
*/
public class Boj_1202 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Node[] jewel = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewel[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewel);
        Arrays.sort(bag);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long price = 0;
        for (int i = 0, idx = 0; i < K; i++) {
            while (idx < N && jewel[idx].m <= bag[i]) pq.offer(jewel[idx++].v);
            if (!pq.isEmpty()) price += pq.poll();
        }
        System.out.println(price);
    }

    private static class Node implements Comparable<Node> {
        int m, v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Node node) {
            if (this.m == node.m) return node.v - this.v;
            return this.m - node.m;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
