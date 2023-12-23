package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    맥주 마시면서 걸어가기
    https://www.acmicpc.net/problem/9205
*/
public class Boj_9205 {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Node> store = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[n + 1];
            Node end = null;
            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (i == 0) queue.offer(new Node(x, y));
                else {
                    store.add(new Node(x, y));
                    if (i == n + 1) end = new Node(x, y);
                }
            }
            boolean flag = false;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.x == end.x && node.y == end.y) {
                    flag = true;
                    break;
                }
                for (int i = 0; i < store.size(); i++) {
                    if (visited[i]) continue;
                    Node next = store.get(i);
                    if (canGo(node, next)) {
                        queue.offer(new Node(next.x, next.y));
                        visited[i] = true;
                    }
                }
            }
            sb.append(flag ? "happy" : "sad").append("\n");
        }
        System.out.println(sb);
    }

    static boolean canGo(Node n1, Node n2) {
        return (Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y)) <= 1000;
    }


    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_9205().solution();
    }
}
