package programmers;

import java.util.Arrays;

/*
    단속카메라
    https://school.programmers.co.kr/learn/courses/30/lessons/42884
*/
public class Pro_42884 {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        int camera = routes[0][1];
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] >= camera) {
                camera = routes[i][1];
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(new Pro_42884().solution(routes));
    }
}
