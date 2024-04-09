package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    잃어버린 괄호
    https://www.acmicpc.net/problem/1541
*/
public class Boj_1541 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        int result = Integer.MAX_VALUE;
        while (st.hasMoreTokens()) {
            StringTokenizer add = new StringTokenizer(st.nextToken(), "+");
            int number = 0;
            while (add.hasMoreTokens()) {
                number += Integer.parseInt(add.nextToken());
            }
            if (result == Integer.MAX_VALUE) result = number;
            else result -= number;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
