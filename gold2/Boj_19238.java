package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    스타트 택시
    https://www.acmicpc.net/problem/19238
*/
public class Boj_19238 {
    static int N, M, fuel;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Taxi taxi;
    static ArrayList<Passenger> passengers;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        passengers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            int ec = Integer.parseInt(st.nextToken()) - 1;
            Passenger passenger = new Passenger(sr, sc, er, ec, 0);
            passenger.f = getGoalDistance(passenger);
            if (passenger.f == -1) {
                System.out.println(-1);
                System.exit(0);
            }
            passengers.add(passenger);
        }
        while (!passengers.isEmpty()) {
            Passenger p = getNearest(taxi);
            if (p == null || !pickUpPassenger(p)) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(fuel);
    }

    private static boolean pickUpPassenger(Passenger passenger) {
        fuel -= passenger.d;
        if (fuel - passenger.f < 0) return false;
        taxi.r = passenger.er;
        taxi.c = passenger.ec;
        fuel += passenger.f;
        passengers.remove(passenger);
        return true;
    }

    private static Passenger getNearest(Taxi start) {
        Queue<Taxi> queue = new LinkedList<>();
        Queue<Passenger> psgQueue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(start);
        visited[start.r][start.c] = true;
        while (!queue.isEmpty()) {
            Taxi taxi = queue.poll();
            if (!enoughFuel(taxi.d)) return null;
            if (!psgQueue.isEmpty() && psgQueue.peek().d != taxi.d) break;
            for (Passenger p : passengers) {
                if (p.sr == taxi.r && p.sc == taxi.c) {
                    p.d = taxi.d;
                    psgQueue.offer(p);
                }
            }
            for (int d = 0; d < 4; d++) {
                int nr = taxi.r + deltas[d][0];
                int nc = taxi.c + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    queue.offer(new Taxi(nr, nc, taxi.d + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        if (psgQueue.isEmpty()) return null;
        Passenger nearest = psgQueue.poll();
        while (!psgQueue.isEmpty()) {
            Passenger passenger = psgQueue.poll();
            if (nearest.sr > passenger.sr) nearest = passenger;
            else if (nearest.sr == passenger.sr && nearest.sc > passenger.sc) nearest = passenger;
        }
        return nearest;
    }

    private static boolean enoughFuel(int d) {
        return fuel - d >= 0;
    }

    private static int getGoalDistance(Passenger passenger) {
        Queue<Passenger> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(passenger);
        visited[passenger.sr][passenger.sc] = true;
        while (!queue.isEmpty()) {
            Passenger p = queue.poll();
            if (p.sr == p.er && p.sc == p.ec) return p.f;

            for (int d = 0; d < 4; d++) {
                int nr = p.sr + deltas[d][0];
                int nc = p.sc + deltas[d][1];
                if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    queue.offer(new Passenger(nr, nc, p.er, p.ec, p.f + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static class Taxi {
        int r, c, d;

        public Taxi(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    private static class Passenger {
        int sr, sc, er, ec, f, d;

        public Passenger(int sr, int sc, int er, int ec, int f) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
            this.f = f;
        }

        @Override
        public String toString() {
            return "(" + sr + ", " + sc + ", " + d + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_19238().solution();
    }
}
