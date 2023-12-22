package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    직사각형 네개의 합집합의 면적 구하기
    https://www.acmicpc.net/problem/2669
*/
public class Boj_2669 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] map = new boolean[100][100];
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            for (int r = r1; r < r2; r++) {
                for (int c = c1; c < c2; c++) {
                    map[r][c] = true;
                }
            }
        }
        for (boolean[] mm : map) {
            for (boolean m : mm) {
                if (m) ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2669().solution();
    }
}
