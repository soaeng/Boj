package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    볼링 점수 계산
    https://www.acmicpc.net/problem/17215
*/
public class Boj_17215 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pin = br.readLine();
        int round = pin.length();
        int score = 0;
        int[][] frame = new int[12][3];
        int idx = 0, time = 0;
        for (int i = 0; i < round; i++) {
            switch (pin.charAt(i)) {
                case 'S':
                    frame[idx][0] = 10;
                    if (idx < 9) frame[idx][2] = 2;
                    idx++;
                    time = 0;
                    break;
                case 'P':
                    frame[idx][1] = 10 - frame[idx][0];
                    if (idx < 9) frame[idx][2] = 1;
                    idx++;
                    time = 0;
                    break;
                case '-':
                    time++;
                    if (time == 2) {
                        idx++;
                        time = 0;
                    }
                    break;
                default:
                    frame[idx][time] = Character.getNumericValue(pin.charAt(i));
                    time++;
                    if (time == 2) {
                        idx++;
                        time = 0;
                    }
                    break;
            }
        }
        for (int i = 0; i < 12; i++) {
            score += frame[i][0] + frame[i][1];
            if (i >= 9) continue;
            if (frame[i][2] == 2) {
                if (frame[i + 1][0] == 10)
                    score += frame[i + 1][0] + frame[i + 2][0];
                else score += frame[i + 1][0] + frame[i + 1][1];
            } else if (frame[i][2] == 1) {
                score += frame[i + 1][0];
            }
        }
        System.out.println(score);
    }

    public static void main(String[] args) throws Exception {
        new Boj_17215().solution();
    }
}
