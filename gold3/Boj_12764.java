package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    싸지방에 간 준하
    https://www.acmicpc.net/problem/12764
*/
public class Boj_12764 {
    private static void solution() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Computer> pq = new PriorityQueue<>();
        PriorityQueue<Computer> candidate = new PriorityQueue<>((o1, o2) -> o1.id - o2.id);
        ArrayList<Node> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        int[] count = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end));
        }
        Collections.sort(list);
        int idx = 0;
        for (Node node : list) {
            while (!pq.isEmpty() && pq.peek().end < node.start) {
                candidate.offer(pq.poll());
            }
            int id = candidate.isEmpty() ? idx++ : candidate.poll().id;
            count[id]++;
            pq.offer(new Computer(id, node.end));
        }
        sb.append(idx).append("\n");
        for (int c : count) {
            if (c == 0) break;
            sb.append(c).append(" ");
        }
        System.out.println(sb);
    }

    private static class Computer implements Comparable<Computer> {
        int id, end;

        public Computer(int id, int end) {
            this.id = id;
            this.end = end;
        }

        @Override
        public int compareTo(Computer computer) {
            return this.end - computer.end;
        }
    }

    private static class Node implements Comparable<Node> {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node node) {
            return this.start - node.start;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
