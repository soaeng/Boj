package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    암호 만들기
    https://www.acmicpc.net/problem/1759
*/
public class Boj_1759 {
    static int L, C;
    static char[] src, tgt;
    static StringBuilder sb = new StringBuilder();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        src = new char[C];
        tgt = new char[L];
        String input = br.readLine();
        for (int i = 0, idx = 0; i < C; i++, idx += 2) {
            src[i] = input.charAt(idx);
        }
        Arrays.sort(src);
        comb(0, 0, 0, 0);
        System.out.println(sb);
    }

    private static void comb(int cnt, int start, int c, int v) {
        if (cnt == L) {
            if (c < 2 || v < 1) return;
            for (char ch : tgt) sb.append(ch);
            sb.append("\n");
            return;
        }
        for (int i = start; i < C; i++) {
            tgt[cnt] = src[i];
            if (isVowel(src[i])) comb(cnt + 1, i + 1, c, v + 1);
            else comb(cnt + 1, i + 1, c + 1, v);
        }
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) throws Exception {
        new Boj_1759().solution();
    }
}
