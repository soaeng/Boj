package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    숫자 카드
    https://www.acmicpc.net/problem/10815
*/
public class Boj_10815_2 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        boolean[] numbers = new boolean[20000001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (N-- > 0) numbers[Integer.parseInt(st.nextToken()) + 10000000] = true;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            if (numbers[Integer.parseInt(st.nextToken()) + 10000000]) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
