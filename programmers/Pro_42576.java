package programmers;

import java.util.HashMap;
import java.util.Map;

/*
    완주하기 못한 선수
    https://school.programmers.co.kr/learn/courses/30/lessons/42576
*/
public class Pro_42576 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for (String name : participant) map.put(name, map.getOrDefault(name, 0) + 1);
        for (String name : completion) {
            if (map.get(name) > 1) map.put(name, map.get(name) - 1);
            else map.remove(name);
        }
        return map.keySet().iterator().next();
    }

    public static void main(String[] args) {
//        String[] participant = {"leo", "kiki", "eden"};
//        String[] completion = {"eden", "kiki"};
//        String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//        String[] completion = {"josipa", "filipa", "marina", "nikola"};
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};


        System.out.println(new Pro_42576().solution(participant, completion));
    }
}
