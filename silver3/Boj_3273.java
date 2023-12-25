package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    두 수의 합
    https://www.acmicpc.net/problem/3273
*/
public class Boj_3273 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        // 배열 생성
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 배열 정렬
        Arrays.sort(arr);
        int startPoint = 0;
        int endPoint = n - 1;
        int count = 0;
        int temp = 0;
        while (startPoint < endPoint) {
            temp = arr[startPoint] + arr[endPoint];
            if (temp > x) {
                endPoint--;
            } else if (temp < x) {
                startPoint++;
            } else {
                startPoint++;
                endPoint--;
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Boj_3273().solution();
    }
}
