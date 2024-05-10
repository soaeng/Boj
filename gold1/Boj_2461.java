package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    대표 선수
    https://www.acmicpc.net/problem/2461
*/
public class Boj_2461 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Student> pq = new PriorityQueue<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int max = 0;
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
            max = Math.max(max, arr[i][0]);
            pq.offer(new Student(i, 0, arr[i][0]));
        }

        while (true) {
            Student student = pq.poll();
            int min = student.value;
            result = Math.min(result, max - min);
            if (result == 0) break;
            if (student.index + 1 == M) break;
            int cid = student.cid;
            int idx = student.index + 1;
            max = Math.max(max, arr[cid][idx]);
            pq.offer(new Student(cid, idx, arr[cid][idx]));
        }
        System.out.println(result);
    }

    private static class Student implements Comparable<Student> {
        int cid, index, value;

        public Student(int cid, int index, int value) {
            this.cid = cid;
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Student student) {
            return this.value - student.value;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
