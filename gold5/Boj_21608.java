package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    상어 초등학교
    https://www.acmicpc.net/problem/21608
*/
public class Boj_21608 {
    static int N;
    static ArrayList<Integer>[] sharks;
    static int[][] seats;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[] students;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int size = N * N;
        sharks = new ArrayList[size + 1];
        students = new int[size + 1];
        seats = new int[N][N];
        for (int i = 1; i <= size; i++) sharks[i] = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            students[i] = s;
            for (int j = 0; j < 4; j++) {
                sharks[s].add(Integer.parseInt(st.nextToken()));
            }
        }
        seats[1][1] = students[1];
        for (int i = 2; i <= size; i++) {
            getSeat(i);
        }
        getScore();
    }

    private static void getScore() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + deltas[d][0];
                    int nc = j + deltas[d][1];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && sharks[seats[i][j]].contains(seats[nr][nc])) count++;
                }
                switch (count) {
                    case 1:
                        total += 1;
                        break;
                    case 2:
                        total += 10;
                        break;
                    case 3:
                        total += 100;
                        break;
                    case 4:
                        total += 1000;
                        break;
                }
            }
        }
        System.out.println(total);
    }

    private static void getSeat(int s) {
        PriorityQueue<Node> pqueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.like == o2.like) {
                if (o1.blank == o2.blank) {
                    if (o1.r == o2.r) return o1.c - o2.c;
                    return o1.r - o2.r;
                }
                return o2.blank - o1.blank;
            }
            return o2.like - o1.like;
        });
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seats[i][j] == 0) pqueue.add(getStudent(i, j, s));
            }
        }
        assert pqueue.peek() != null;
        int r = pqueue.peek().r;
        int c = pqueue.peek().c;
        seats[r][c] = students[s];
    }

    private static Node getStudent(int r, int c, int s) {
        int blank = 0;
        int like = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (sharks[students[s]].contains(seats[nr][nc])) like++;
                if (seats[nr][nc] == 0) blank++;
            }
        }
        Node node = new Node(r, c);
        node.blank = blank;
        node.like = like;
        return node;
    }

    public static class Node {
        int r, c, blank, like;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
            this.like = 0;
            this.blank = 0;
        }
    }


    public static void main(String[] args) throws Exception {
        new Boj_21608().solution();
    }
}
