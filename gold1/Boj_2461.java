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
        int[] indexes = new int[N];
        int index = 0;
        int max = 0;
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
            max = Math.max(max, arr[i][0]);
            pq.offer(new Student(i, arr[i][0]));
        }

//        while (indexes[index] < M) {
//            int max = -1, min = Integer.MAX_VALUE;
//            for (int i = 0; i < N; i++) {
//                if (max < arr[i][indexes[i]]) max = arr[i][indexes[i]];
//                if (min > arr[i][indexes[i]]) {
//                    min = arr[i][indexes[i]];
//                    index = i;
//                }
//            }
//            result = Math.min(result, max - min);
//            if (result == 0) break;
//            indexes[index]++;
//        }

        while (indexes[index] < M) {
            Student student = pq.poll();
            index = student.index;
            int min = student.value;
            result = Math.min(result, max - min);
            if (result == 0) break;
            if (++indexes[index] == M) break;
            max = Math.max(max, arr[index][indexes[index]]);
            pq.offer(new Student(index, arr[index][indexes[index]]));
        }
        System.out.println(result);
    }

    private static class Student implements Comparable<Student> {
        int index, value;

        public Student(int index, int value) {
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
