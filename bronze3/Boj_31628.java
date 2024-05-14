package bronze3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    가지 한 두름 주세요
    https://www.acmicpc.net/problem/31628
*/
public class Boj_31628 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] array = new String[10][10];
        for (int r = 0; r < 10; r++) {
            boolean flag = true;
            String str = "";
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 10; c++) {
                array[r][c] = st.nextToken();
                if (c == 0) str = array[r][c];
                else {
                    if (!array[r][c].equals(str)) flag = false;
                }
            }
            if (flag) {
                System.out.println(1);
                System.exit(0);
            }
        }
        for (int c = 0; c < 10; c++) {
            boolean flag = true;
            String str = "";
            for (int r = 0; r < 10; r++) {
                if (r == 0) str = array[r][c];
                else {
                    if (!array[r][c].equals(str)) flag = false;
                }
            }
            if (flag) {
                System.out.println(1);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
