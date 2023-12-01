package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    카드 섞기
    https://www.acmicpc.net/problem/1091
*/
public class Boj_1091 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 카드 현재 정보
        int[] current = new int[N];
        // 플레이어
        int[] P = new int[N];
        // 섞는 카드 순서
        int[] S = new int[N];
        int[] initial = new int[N];

        // 최종 가져야 할 플레이어별 카드 번호 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            current[i] = i;
            initial[i] = i;
        }
        // 카드 섞기
        int answer = 0;
        while (!isDone(current, P, N)) {
            if (Arrays.equals(initial, current) && answer != 0) {
                answer = -1;
                break;
            }
            current = shuffleCard(current, S, N);
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean isDone(int[] current, int[] P, int n) {
        for (int i = 0; i < n; i++) {
            if (P[i] != current[i] % 3) return false;
        }
        return true;
    }

    private static int[] shuffleCard(int[] current, int[] S, int n) {
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = current[S[i]];
        }
        return temp;
    }

    public static void main(String[] args) throws Exception {
        new Boj_1091().solution();
    }
}
