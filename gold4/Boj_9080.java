package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    PC방 요금
    https://www.acmicpc.net/problem/9080
*/
public class Boj_9080 {

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ": ");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int cost = 0;
            while (d > 0) {
                // 야간 정액권 유리한 경우 (22~08)
                if ((hour >= 22 || hour < 3) && d > 300) {
                    int temp = (hour >= 22) ? hour - 22 : (hour + 24) - 22;
                    d -= 600 - temp * 60 - min;
                    hour = 8;
                    min = 0;
                    cost += 5000;
                } else {
                    hour = (hour + 1) % 24;
                    d -= 60;
                    cost += 1000;
                }
            }
            sb.append(cost).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
