package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/*
    LCS 2
    https://www.acmicpc.net/problem/9252
*/
public class Boj_9252 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        if (dp[len1][len2] == 0) {
            System.out.println(0);
            return;
        }

        sb.append(dp[len1][len2]).append("\n");

        while (len1 > 0 && len2 > 0) {
            if (dp[len1][len2] == dp[len1 - 1][len2]) len1--;
            else if (dp[len1][len2] == dp[len1][len2 - 1]) len2--;
            else {
                stack.push(str1[len1-- - 1]);
                len2--;
            }
        }

        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
