package silver5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    덩치
    https://www.acmicpc.net/problem/7568
*/
public class Boj_7568 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            people[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if (people[i].height < people[j].height && people[i].weight < people[j].weight)
                    rank++;
            }
            sb.append(rank).append(" ");
        }
        System.out.println(sb);
    }

    private static class Person {
        int height, weight;

        public Person(int h, int w) {
            height = h;
            weight = w;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
