package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    나무 재테크
    https://www.acmicpc.net/problem/16235
*/
public class Boj_16235 {
    static int N, M, K;
    static int[][] map, A;
    static int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static PriorityQueue<Tree> trees;
    static Queue<Tree> dead;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        trees = new PriorityQueue<>();
        map = new int[N][N];
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            trees.offer(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            dead = new LinkedList<>();
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(trees.size());
    }

    private static void spring() {
        Queue<Tree> queue = new LinkedList<>();
        while (!trees.isEmpty()) {
            Tree tree = trees.poll();
            if (map[tree.r][tree.c] >= tree.age) {
                map[tree.r][tree.c] -= tree.age;
                tree.age++;
                queue.offer(tree);
            } else dead.offer(tree);
        }
        trees.addAll(queue);
    }

    private static void summer() {
        for (Tree t : dead) {
            map[t.r][t.c] += t.age / 2;
        }
    }

    private static void fall() {
        Queue<Tree> queue = new LinkedList<>();
        for (Tree t : trees) {
            if (t.age % 5 == 0) queue.offer(t);
        }
        while (!queue.isEmpty()) {
            Tree tree = queue.poll();
            for (int d = 0; d < 8; d++) {
                int nr = tree.r + deltas[d][0];
                int nc = tree.c + deltas[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) trees.offer(new Tree(nr, nc, 1));
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    private static class Tree implements Comparable<Tree> {
        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16235().solution();
    }
}
