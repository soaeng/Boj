package bronze2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    백설 공주와 일곱 난쟁이
    https://www.acmicpc.net/problem/3040
*/
public class Boj_3040_np {
    static int[] arr = new int[9];
    static int[] index = new int[9];

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 2; i < 9; i++) index[i] = 1;
        while (true) {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                if (index[i] == 1) sum += arr[i];
            }
            if (sum == 100) {
                for (int i = 0; i < 9; i++) {
                    if (index[i] == 1) sb.append(arr[i]).append("\n");
                }
                break;
            }
            if (!np()) break;
        }
        System.out.println(sb);
    }

    private static boolean np() {
        int i = index.length - 1;
        while (i > 0 && index[i - 1] >= index[i]) i--;
        if (i == 0) return false;
        int j = index.length - 1;
        while (index[i - 1] >= index[j]) j--;
        swap(i - 1, j);

        int k = index.length - 1;
        while (i < k) swap(i++, k--);
        return true;
    }

    private static void swap(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
