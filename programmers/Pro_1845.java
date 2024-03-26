package programmers;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    폰켓몬
    https://school.programmers.co.kr/learn/courses/30/lessons/1845
*/
public class Pro_1845 {

    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int[] arr = {4, 4, 4, 3, 3};
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int n : arr) {
            if (!stack.isEmpty() && stack.peekLast() == n) continue;
            stack.offerLast(n);
        }
        int[] answer = stack.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(answer));
        for (int num : nums) set.add(num);
        return Math.min(nums.length / 2, set.size());
    }

    public static void main(String[] args) {
//        int[] nums = {3, 1, 2, 3};
        int[] nums = {3, 3, 3, 2, 2, 4};
//        int[] nums = {3, 3, 3, 2, 2, 2};
        System.out.println(new Pro_1845().solution(nums));
    }
}
