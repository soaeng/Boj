package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    4연산
    https://www.acmicpc.net/problem/14395
*/
public class Boj_14395 {
    static int s, t;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        if (s == t) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs());
    }

    private static String bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();
        char[] deltas = {'*', '+', '-', '/'};
        queue.offer(new Node(s, ""));
        visited.add((long) s);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.n == t) return node.log;
            for (int d = 0; d < 4; d++) {
                long nn = node.n;
                switch (d) {
                    case 0:
                        nn *= node.n;
                        break;
                    case 1:
                        nn += node.n;
                        break;
                    case 2:
                        nn -= node.n;
                        break;
                    case 3:
                        if (nn == 0) continue;
                        nn /= node.n;
                        break;
                }
                if (nn > 1000000001) continue;
                if (visited.contains(nn)) continue;
                visited.add(nn);
                queue.offer(new Node(nn, node.log + deltas[d]));
            }
        }
        return "-1";
    }

    private static class Node {
        long n;
        String log;

        public Node(long n, String log) {
            this.n = n;
            this.log = log;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
