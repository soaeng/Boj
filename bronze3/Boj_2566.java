package bronze3;

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
        int[][] numbers = new int[9][9];
        int max = Integer.MIN_VALUE;
//        int r = -1, c = -1;
        int[] position = new int[2];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
                if (numbers[i][j] > max) {
                    max = numbers[i][j];
                    position[0] = i + 1;
                    position[1] = j + 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
//        sb.append(max).append("\n").append(r + 1).append(" ").append(c + 1);
        sb.append(max).append("\n").append(position[0]).append(" ").append(position[1]);
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2566().solution();
    }
}
