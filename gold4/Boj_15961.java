package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    회전 초밥
    https://www.acmicpc.net/problem/2531
*/
public class Boj_15961 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] type = new int[d + 1];
        type[c] = 3001;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int count = 1;
        for (int i = 0; i < k; i++) {
            if (type[arr[i]]++ == 0) count++;
        }
        int max = count;
        for (int i = 0; i < N; i++) {
            if (--type[arr[i]] == 0) count--;
            if (++type[arr[(i + k) % N]] == 1) count++;
            max = Math.max(max, count);
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
