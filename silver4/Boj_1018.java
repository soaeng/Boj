package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    체스판 다시 칠하기
    https://www.acmicpc.net/problem/1018
*/
public class Boj_1018 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'W') board[i][j] = true;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[r + i][c + j] != ((i + j) % 2 == 0)) {
                            count++;
                        }
                    }
                }
                min = Math.min(min, Math.min(count, 64 - count));
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
