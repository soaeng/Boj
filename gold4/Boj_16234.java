package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    인구 이동
    https://www.acmicpc.net/problem/16234
*/
public class Boj_16234 {
    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    static ArrayList<ArrayList<Nation>> unions;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        unions = new ArrayList<>();
        int ans = 0;
        if (N == 1) {
            System.out.println(ans);
            System.exit(0);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (unions.size() != 1) {
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) openBorders(i, j);
                }
            }
            if (unions.size() == N * N || unions.size() == 0) break;
            for (ArrayList<Nation> union : unions) {
                populationMove(union);
            }
            ans++;
            unions.clear();
        }
        System.out.println(ans);
    }

    private static void populationMove(ArrayList<Nation> union) {
        int total = 0;
        for (Nation n : union) total += A[n.r][n.c];
        int average = total / union.size();
        for (Nation n : union) {
            A[n.r][n.c] = average;
        }
    }

    private static void openBorders(int r, int c) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Nation> queue = new LinkedList<>();
        ArrayList<Nation> union = new ArrayList<>();
        queue.offer(new Nation(r, c));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Nation nation = queue.poll();
            union.add(nation);
            for (int d = 0; d < 4; d++) {
                int nr = nation.r + deltas[d][0];
                int nc = nation.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && checkConditions(A[nation.r][nation.c], A[nr][nc])) {
                    queue.offer(new Nation(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        unions.add(union);
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static boolean checkConditions(int a, int b) {
        int difference = Math.abs(a - b);
        return difference >= L && difference <= R;
    }

    private static class Nation {
        int r, c;

        public Nation(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_16234().solution();
    }
}
