package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    퇴사 2
    https://www.acmicpc.net/problem/15486
*/
public class Boj_15486 {
    static int N;
    static int[][] schedule;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        schedule = new int[N + 1][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = N - 1; i >= 0; i--) {
            if (i + schedule[i][0] > N) schedule[i][2] = schedule[i + 1][2];
            else schedule[i][2] = Math.max(schedule[i + 1][2], schedule[i][1] + schedule[i + schedule[i][0]][2]);
        }
        System.out.println(schedule[0][2]);
    }

    public static void main(String[] args) throws Exception {
        new Boj_15486().solution();
    }
}
