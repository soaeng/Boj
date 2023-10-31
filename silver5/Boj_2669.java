package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    직사각형 네개의 합집합의 면적 구하기
    https://www.acmicpc.net/problem/2669
*/
public class Boj_2669 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxR = 0;
        int maxC = 0;
        ArrayList<int[]> spots = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            maxR = Math.max(maxR, r2);
            maxC = Math.max(maxC, c2);
            spots.add(new int[]{r1, c1, r2, c2});
        }
        int[][] square = new int[maxR][maxC];
        for (int[] spot : spots) {
            for (int r = spot[0]; r < spot[2]; r++) {
                for (int c = spot[1]; c < spot[3]; c++) {
                    square[r][c] = 1;
                }
            }
        }
        int ans = 0;
        for (int[] nn : square) {
            for (int n : nn) {
                if (n == 1) ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2669().solution();
    }
}
