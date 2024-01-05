package bronze5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    윤년
    https://www.acmicpc.net/problem/2753
*/
public class Boj_2753 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(br.readLine());
        System.out.println((year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 1 : 0);
    }

    public static void main(String[] args) throws Exception {
        new Boj_2753().solution();
    }
}
