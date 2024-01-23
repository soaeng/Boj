package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    최댓값
    https://www.acmicpc.net/problem/2566
*/
public class Boj_2566 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        int r = -1, c = -1;
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (max < num) {
                    max = num;
                    r = i;
                    c = j;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n");
        sb.append(r + 1).append(" ").append(c + 1);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2566().solution();
    }
}
