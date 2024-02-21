package silver5;

/*
    셀프 넘버
    https://www.acmicpc.net/problem/4673
*/
public class Boj_4673 {
    private static void solution() throws Exception {
        boolean[] num = new boolean[10001];
        for (int i = 1; i < 10001; i++) {
            int n = i;
            int sum = n;
            while (n > 0 && sum < 10001) {
                sum += n % 10;
                n /= 10;
            }
            if (sum > 10000) continue;
            num[sum] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10001; i++) {
            if (!num[i]) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
