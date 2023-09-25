package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    나룻배
    https://www.acmicpc.net/problem/2065
*/
public class Boj_2065 {
    /**
     * M: 나룻배가 태울 수 있는 최대 인원
     * t: 반대편 정박장으로 이동하는 시간
     * N: 손님의 수
     * now: 현재 시간
     * position: 현재 위치
     * arrivals: 손님별 도착 시간 배열
     * queues[0]: 왼쪽 정박장
     * queues[1]: 오른쪽 정박장
     */
    static int M, t, N, now, position;
    static int[] arrivals;
    static Queue<Passenger>[] queues;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt((st.nextToken()));
        t = Integer.parseInt((st.nextToken()));
        N = Integer.parseInt((st.nextToken()));
        arrivals = new int[N];
        queues = new Queue[2];
        queues[0] = new LinkedList<>();
        queues[1] = new LinkedList<>();
        now = 0;
        position = 0; // (제일 처음에는 나룻배가 왼쪽 정박장에 위치해 있다.)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            if (st.nextToken().equals("left")) queues[0].offer(new Passenger(i, time));
            else queues[1].offer(new Passenger(i, time));
        }

        while (N > 0) {
            if (canPickUpHere()) {
                pickUpPassenger();
                moveOpposite();
            } else {
                waitingPassenger();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : arrivals) {
            sb.append(n).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean canPickUpHere() {
        return !queues[position].isEmpty() && queues[position].peek().time <= now;
    }

    private static void pickUpPassenger() {
        int count = 0;
        while (!queues[position].isEmpty()) {
            if (count == M) return;
            Passenger psg = queues[position].peek();
            if (psg.time <= now) {
                arrivals[psg.seq] = now + t; // 손님을 태우면 손님은 t시간 뒤에 도착함
                queues[position].poll();
                count++;
                N--;
            } else break;
        }
    }

    private static void moveOpposite() {
        position = position == 1 ? 0 : 1;
        now += t;
    }

    private static void waitingPassenger() {
        int opposite = position == 0 ? 1 : 0;
        if (queues[position].isEmpty() && !queues[opposite].isEmpty()) {
            setTime(queues[opposite].peek().time);
            moveOpposite();
            return;
        } else if (!queues[position].isEmpty() && queues[opposite].isEmpty()) {
            setTime(queues[position].peek().time);
            return;
        }
        Passenger curr = queues[position].peek();
        Passenger oppo = queues[opposite].peek();
        if (curr.time <= oppo.time) setTime(curr.time);
        else {
            setTime(oppo.time);
            moveOpposite();
        }
    }

    private static void setTime(int time) {
        now += Math.max(time - now, 0);
    }

    private static class Passenger {
        int seq, time;

        public Passenger(int seq, int time) {
            this.seq = seq;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2065().solution();
    }
}
