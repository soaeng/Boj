package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/*
    전화번호 목록
    https://www.acmicpc.net/problem/5052
*/
public class Boj_5052 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            Set<String> set = new HashSet<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                set.add(br.readLine());
            }
            boolean flag = false;
            for (String phone : set) {
                for (int j = 1; j < phone.length(); j++) {
                    if (set.contains(phone.substring(0, j))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
            sb.append(flag ? "NO" : "YES").append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
