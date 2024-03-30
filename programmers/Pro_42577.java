package programmers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
    전화번호 목록
    https://school.programmers.co.kr/learn/courses/30/lessons/42577
*/
public class Pro_42577 {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, phone_book);
        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                if (set.contains(phone.substring(0, i))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String[] phone_book = {"119", "97674223", "1195524421"};
//        String[] phone_book = {"123", "456", "789"};
        String[] phone_book = {"12", "123", "1235", "567", "88"};
        System.out.println(new Pro_42577().solution(phone_book));
    }
}
