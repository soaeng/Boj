package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    DNA 비밀번호
    https://www.acmicpc.net/problem/12891
*/
public class Boj_12891 {
    static int[] condition, check;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String dna = br.readLine();
        condition = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            condition[i] = Integer.parseInt(st.nextToken());
        }
        check = new int[5];
        for (int i = 0; i < P; i++) check[getIndex(dna.charAt(i))]++;
        int count = isPossible() ? 1 : 0;
        for (int i = 0; i < S - P; i++) {
            check[getIndex(dna.charAt(i))]--;
            check[getIndex(dna.charAt((i + P) % S))]++;
            if (isPossible()) count++;
        }
        System.out.println(count);
    }

    private static int getIndex(char ch) {
        switch (ch) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return 4;
        }
    }

    private static boolean isPossible() {
        for (int i = 0; i < 4; i++) {
            if (condition[i] > check[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
