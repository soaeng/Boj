package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    독서실 거리두기
    https://www.acmicpc.net/problem/20665
*/
public class Boj_20665 {
    /* N: 좌석 수
     * T: 예약자 수
     * P: 선호 좌석 번호
     * isOccupied: 좌석 이용 여부
     */
    static int N, T, P;
    static boolean[] isOccupied;
    static ArrayList<User> users;
    static PriorityQueue<User> pq;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        users = new ArrayList<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(u -> u.end));
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;
        isOccupied = new boolean[N];
        int time = (21 - 9) * 60;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            users.add(new User(start, end));
        }

        // 입실 순서로 정렬
        Collections.sort(users);

        for (User user : users) {
            // 퇴실시간이 되면 자리에서 일어남
            while (!pq.isEmpty() && pq.peek().end <= user.start) {
                isOccupied[pq.poll().seat] = false;
            }
            user.seat = pq.isEmpty() ? 0 : findSeat();
            if (user.seat == P) time -= user.minutes;
            isOccupied[user.seat] = true;
            pq.offer(user);
        }

        System.out.println(time);
    }

    private static int findSeat() {
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        int idx = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // 차지된 자리면 거리 계산
            if (isOccupied[i]) {
                distance[i] = 0;
                // 왼쪽
                for (int j = i - 1; j >= 0; j--) {
                    if (isOccupied[j]) break;
                    distance[j] = Math.min(distance[j], i - j);
                }
                // 오른쪽
                for (int j = i + 1; j < N; j++) {
                    if (isOccupied[j]) break;
                    distance[j] = Math.min(distance[j], j - i);
                }
            }
        }
        for (int j = 0; j < N; j++) {
            if (max < distance[j]) {
                idx = j;
                max = distance[j];
            }
        }
        return idx;
    }

    private static class User implements Comparable<User> {
        int start, end, minutes, seat;

        public User(int start, int end) {
            this.start = start;
            this.end = end;
            this.minutes = getMinutes();
        }

        public int getMinutes() {
            int hh = end / 100 - start / 100;
            int mm = end % 100 - start % 100;
            if (mm < 0) {
                hh -= 1;
                mm += 60;
            }
            return (hh * 60) + mm;
        }

        @Override
        public int compareTo(User user) {
            if (this.start == user.start) return this.end - user.end;
            return this.start - user.start;
        }

        @Override
        public String toString() {
            return "User{" +
                    ", start=" + start +
                    ", end=" + end +
                    ", minutes=" + minutes +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_20665().solution();
    }
}
