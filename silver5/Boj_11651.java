package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    좌표 정렬하기 2
    https://www.acmicpc.net/problem/11651
*/
public class Boj_11651 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < N; i++) {
            Node node = pq.poll();
            sb.append(node.x).append(" ").append(node.y).append("\n");
        }
        System.out.println(sb);
    }

    private static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node node) {
            if (this.y == node.y) return this.x - node.x;
            return this.y - node.y;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
