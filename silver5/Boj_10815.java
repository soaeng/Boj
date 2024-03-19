package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    숫자 카드
    https://www.acmicpc.net/problem/10815
*/
public class Boj_10815 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Set<Integer> src = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (N-- > 0) src.add(Integer.parseInt(st.nextToken()));
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> tgt = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) tgt.add(Integer.parseInt(st.nextToken()));
        for (int n : tgt) {
            if (src.contains(n)) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
