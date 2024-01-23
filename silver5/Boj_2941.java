package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    크로아티아 알파벳
    https://www.acmicpc.net/problem/2941
*/
public class Boj_2941 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", "0");
        System.out.println(input.length());
    }

    public static void main(String[] args) throws Exception {
        new Boj_2941().solution();
    }
}
