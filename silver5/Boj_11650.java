package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    좌표 정렬하기
    https://www.acmicpc.net/problem/11650
*/
public class Boj_11650 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
//        int[][] positions = new int[N][2];
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            positions[i][0] = Integer.parseInt(st.nextToken());
//            positions[i][1] = Integer.parseInt(st.nextToken());
//        }
//        Arrays.sort(positions, (o1, o2) -> {
//            if (o1[0] == o2[0]) return o1[1] - o2[1];
//            return o1[0] - o2[0];
//        });
//        for (int[] nn : positions) {
//            for (int n : nn) sb.append(n).append(" ");
//            sb.append("\n");
//        }

//        Node[] nodes = new Node[N];
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//        }
//        Arrays.sort(nodes);
//        for (Node node : nodes){
//            sb.append(node.x).append(" ").append(node.y).append("\n");
//        }

//        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
//            if (o1[0] == o2[0]) return o1[1] - o2[1];
//            return o1[0] - o2[0];
//        });
//        for (int i = 0; i < N; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
//        }
//        while (!pq.isEmpty()) {
//            int[] arr = pq.poll();
//            sb.append(arr[0]).append(" ").append(arr[1]).append("\n");
//        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        while (!pq.isEmpty()) {
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
        public int compareTo(Node o) {
            if (this.x == o.x) return this.y - o.y;
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
