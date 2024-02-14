package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    세 막대
    https://www.acmicpc.net/problem/14215
*/
public class Boj_14215 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sticks = new int[3];
        for (int i = 0; i < 3; i++) {
            sticks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sticks);
        if (sticks[2] >= sticks[0] + sticks[1])
            System.out.println((sticks[0] + sticks[1]) * 2 - 1);
        else
            System.out.println(sticks[0] + sticks[1] + sticks[2]);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
