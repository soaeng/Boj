package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    백설 공주와 일곱 난쟁이
    https://www.acmicpc.net/problem/3040
*/
public class Boj_3040 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        for (int i = 0, flag = 0; i < 8 && flag == 0; i++) {
            for (int j = 1; j < 9; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) continue;
                        sb.append(arr[k]).append("\n");
                    }
                    flag = 1;
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
