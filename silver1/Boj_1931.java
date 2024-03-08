package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    회의실 배정
    https://www.acmicpc.net/problem/1931
*/
public class Boj_1931 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(schedule, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        int count = 1;
        int end = schedule[0][1];
        for (int i = 1; i < N; i++) {
            if (schedule[i][0] >= end) {
                end = schedule[i][1];
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
