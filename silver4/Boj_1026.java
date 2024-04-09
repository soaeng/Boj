package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
    보물
    https://www.acmicpc.net/problem/1026
*/
public class Boj_1026 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
//        Integer[] B = new Integer[N];
        int[] B = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        Arrays.sort(B);
//        Arrays.sort(B, new Desc());
//        Arrays.sort(B, Comparator.reverseOrder());
        int sum = 0;
        for (int i = 0; i < N; i++) {
//            sum += A[i] * B[i];
            sum += A[i] * B[N - i - 1];
        }
        System.out.println(sum);
    }

    static class Desc implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof Integer) || !(o2 instanceof Integer)) return -1;
            Comparable c1 = (Comparable) o1;
            Comparable c2 = (Comparable) o2;
            return c2.compareTo(c1);
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
