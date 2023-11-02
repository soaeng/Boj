package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    컨베이어 벨트 위의 로봇
    https://www.acmicpc.net/problem/20055
*/
public class Boj_20055 {
    // 길이가 N인 컨베이어 벨트
    // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
    static int N, K;
    static int[] boxes;
    static boolean[] robots;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boxes = new int[N * 2];
        robots = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }
        int count = 0;
        do {
            count++;
            rotateBelt();
            moveRobot();
            loadRobot();
        } while (getZero() < K);
        System.out.println(count);
    }

    private static int getZero() {
        int count = 0;
        for (int durability : boxes) {
            if (durability == 0) count++;
        }
        return count;
    }

    private static void rotateBelt() {
        int temp = boxes[N * 2 - 1];
        if (N * 2 - 1 >= 0) System.arraycopy(boxes, 0, boxes, 1, N * 2 - 1);
        boxes[0] = temp;

        if (N - 1 >= 0) System.arraycopy(robots, 0, robots, 1, N - 1);
        robots[0] = false;
        robots[N - 1] = false;
    }

    private static void moveRobot() {
        for (int i = N - 1; i > 0; i--) {
            if (robots[i - 1] && !robots[i] && boxes[i] > 0) {
                robots[i - 1] = false;
                robots[i] = true;
                boxes[i]--;
                if (i == N - 1) robots[i] = false;
            }
        }
    }

    private static void loadRobot() {
        if (boxes[0] > 0) {
            robots[0] = true;
            boxes[0]--;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_20055().solution();
    }
}
