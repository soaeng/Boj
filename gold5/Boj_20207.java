package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    달력
    https://www.acmicpc.net/problem/20207
*/
public class Boj_20207 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] calendar = new int[367];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                calendar[j]++;
            }
        }
        int height = 0, width = 0, area = 0;
        for (int i = 1; i < 367; i++) {
            if (calendar[i] > 0) {
                width++;
                height = Math.max(height, calendar[i]);
            } else if (calendar[i] == 0) {
                area += height * width;
                height = 0;
                width = 0;
            }
        }
        System.out.println(area);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
