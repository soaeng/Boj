package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    A와 B
    https://www.acmicpc.net/problem/12904
*/
public class Boj_12904 {

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String src = br.readLine();
        String tgt = br.readLine();
        while (src.length() < tgt.length()) {
            if (tgt.endsWith("A")) {
                tgt = tgt.substring(0, tgt.length() - 1);
            } else if (tgt.endsWith("B")) {
                StringBuilder sb = new StringBuilder();
                tgt = tgt.substring(0, tgt.length() - 1);
                tgt = sb.append(tgt).reverse().toString();
            }
        }
        System.out.println(src.equals(tgt) ? 1 : 0);
    }


    public static void main(String[] args) throws Exception {
        solution();
    }
}
