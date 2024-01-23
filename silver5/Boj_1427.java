package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    소트인사이드
    https://www.acmicpc.net/problem/1427
*/
public class Boj_1427 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        char[] arr = br.readLine().toCharArray();
//        Arrays.sort(arr);
//        StringBuilder sb = new StringBuilder(new String(arr)).reverse();
        int[] arr = new int[10];
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            arr[input.charAt(i) - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < arr[i]; j++) {
                sb.append(i);
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
