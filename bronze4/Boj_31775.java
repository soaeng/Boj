package bronze4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    글로벌 포닉스
    https://www.acmicpc.net/problem/31775
*/
public class Boj_31775 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] alpha = new boolean[3];
        for (int i = 0; i < 3; i++) {
            char ch = br.readLine().charAt(0);
            switch (ch) {
                case 'l':
                    alpha[0] = true;
                    break;
                case 'k':
                    alpha[1] = true;
                    break;
                case 'p':
                    alpha[2] = true;
                    break;
            }
        }
        System.out.println(alpha[0] && alpha[1] && alpha[2] ? "GLOBAL" : "PONIX");
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
