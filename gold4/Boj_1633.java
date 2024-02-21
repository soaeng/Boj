package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    최고의 팀 만들기
    https://www.acmicpc.net/problem/1633
*/

public class Boj_1633 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Player> players = new ArrayList<>();
        String input = "";
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            players.add(new Player(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int[][] dp = new int[16][16];

        for (Player player : players) {
            for (int w = 15; w >= 0; w--) {
                for (int b = 15; b >= 0; b--) {
                    if (w > 0) dp[w][b] = Math.max(dp[w][b], dp[w - 1][b] + player.w);
                    if (b > 0) dp[w][b] = Math.max(dp[w][b], dp[w][b - 1] + player.b);
                }
            }
//            printDp(dp);
        }

        System.out.println(dp[15][15]);
    }

    private static void printDp(int[][] dp) {
        for (int[] dd : dp) {
            for (int d : dd) {
                System.out.printf("%5d", d);
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }

    private static class Player {
        int w, b;

        public Player(int w, int b) {
            this.w = w;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
