package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    단어 정렬
    https://www.acmicpc.net/problem/1181
*/
public class Boj_1181_array {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length()) return o1.compareTo(o2);
            return o1.length() - o2.length();
        });
        sb.append(words[0]).append("\n");
        for (int i = 1; i < N; i++) {
            if (words[i].equals(words[i - 1])) continue;
            sb.append(words[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
