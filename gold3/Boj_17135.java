package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    캐슬 디펜스
    https://www.acmicpc.net/problem/17135
*/
public class Boj_17135 {
    static int N, M, D, max;
    static int[][] map;
    static int[] archer;
    static ArrayList<Node> enemy;
    static ArrayList<Node> copyEnemy;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        archer = new int[3];
        enemy = new ArrayList<>();
        max = 0;
        // 0은 빈 칸, 1은 적이 있는 칸
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) enemy.add(new Node(i, j));
            }
        }
        comb(0, 0);
        System.out.println(max);
    }

    private static void comb(int start, int cnt) {
        if (cnt == 3) {
            simulation();
            return;
        }
        for (int i = start; i < M; i++) {
            archer[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    private static int[][] copyMap() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], M);
        }
        return copy;
    }

    private static void simulation() {
        copyEnemy = getEnemy();
        int count = 0;
        while (true) {
            for (int i = 0; i < 3; i++) {
                attack(archer[i]);
            }
            Iterator<Node> iter = copyEnemy.iterator();
            while (iter.hasNext()) {
                Node node = iter.next();
                if (node.dead) {
                    iter.remove();
                    count++;
                } else if (node.r + 1 == N) iter.remove();
                else node.r++;
            }
            if (copyEnemy.size() == 0) break;
        }
        max = Math.max(max, count);
    }

    private static void attack(int archer) {
        pq.clear();
        for (Node node : copyEnemy) {
            node.d = Math.abs(N - node.r) + Math.abs(archer - node.c);
            if (node.d <= D) pq.offer(node);
        }
        if (!pq.isEmpty()) pq.poll().dead = true;
    }

    private static ArrayList<Node> getEnemy() {
        ArrayList<Node> copy = new ArrayList<>();
        for (Node node : enemy) {
            copy.add(new Node(node.r, node.c));
        }
        return copy;
    }

    private static class Node implements Comparable<Node> {
        int r, c, d;
        boolean dead;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + d + ", " + dead + ")";
        }

        @Override
        public int compareTo(Node node) {
            if (this.d == node.d) return this.c - node.c;
            return this.d - node.d;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_17135().solution();
    }
}
