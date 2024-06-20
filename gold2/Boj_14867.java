package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    물통
    https://www.acmicpc.net/problem/14867
*/
public class Boj_14867 {
    static Node capacity, goal;
    static final long INF = 1_000_000;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacity = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        goal = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(bfs());
    }

    private static int bfs() {
        int time = 0;
        Queue<Node> queue = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();

        queue.offer(new Node(0, 0));
        visited.add(0L);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.equals(goal)) return time;
                for (int d = 0; d < 6; d++) {
                    int na = node.a;
                    int nb = node.b;
                    switch (d) {
                        // Fill
                        case 0:
                            if (na == capacity.a) continue;
                            na = capacity.a;
                            break;
                        case 1:
                            if (nb == capacity.b) continue;
                            nb = capacity.b;
                            break;

                        // Empty
                        case 2:
                            if (na == 0) continue;
                            na = 0;
                            break;
                        case 3:
                            if (nb == 0) continue;
                            nb = 0;
                            break;

                        // Move
                        case 4:
                            if (capacity.b - nb >= na) {
                                nb += na;
                                na = 0;
                            } else {
                                na -= (capacity.b - nb);
                                nb = capacity.b;
                            }
                            break;
                        case 5:
                            if (capacity.a - na >= nb) {
                                na += nb;
                                nb = 0;
                            } else {
                                nb -= (capacity.a - na);
                                na = capacity.a;
                            }
                            break;
                    }
                    if (visited.contains(na * INF + nb)) continue;
                    queue.offer(new Node(na, nb));
                    visited.add(na * INF + nb);
                }
            }
            time++;
        }
        return -1;
    }

    private static class Node {
        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.a, this.b);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            return Objects.equals(this.a, node.a) && this.b == node.b;
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
