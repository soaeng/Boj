package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    팀원 모집
    https://www.acmicpc.net/problem/11578
*/
public class Boj_11578 {
    static int M, max, result;
    static int[] arr;
    static boolean[] selected;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = (int) Math.pow(2, N);
        result = 11;
        int temp = 0;
        arr = new int[M];
        selected = new boolean[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            while (count-- > 0) {
                arr[i] |= (1 << (Integer.parseInt(st.nextToken()) - 1));
            }
            temp |= arr[i];
        }
        if (temp < max - 1) {
            System.out.println(-1);
            return;
        }
        subset(0);
        System.out.println(result);
    }

    private static void subset(int depth) {
        if (depth == M) {
            int problem = 0;
            int count = 0;
            for (int i = 0; i < M; i++) {
                if (selected[i]) {
                    problem |= arr[i];
                    count++;
                }
            }
            if (problem == max - 1) result = Math.min(result, count);
            return;
        }
        selected[depth] = true;
        subset(depth + 1);
        selected[depth] = false;
        subset(depth + 1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
