package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    색종이
    https://www.acmicpc.net/problem/2563
*/
public class Boj_2563 {
    public void solution() throws Exception {
        boolean[][] map = new boolean[101][101];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    map[i][j] = true;
                }
            }
        }
        int area = 0;
        for (boolean[] bb : map) {
            for (boolean b : bb) {
                if (b) area++;
            }
        }
        System.out.println(area);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2563().solution();
    }
}
