package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    쇼핑몰
    https://www.acmicpc.net/problem/17612
*/
public class Boj_17612 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Customer> list = new ArrayList<>();
        PriorityQueue<Customer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (i < K) {
                pq.offer(new Customer(id, w, i));
            } else {
                if (pq.isEmpty()) break;
                pq.offer(new Customer(id, pq.peek().time + w, pq.peek().counter));
                list.add(pq.poll());
            }
        }
        while (!pq.isEmpty()) list.add(pq.poll());

        list.sort((o1, o2) -> {
            if (o1.time == o2.time) return o2.counter - o1.counter;
            return o1.time - o2.time;
        });

        long result = 0L;
        int idx = 1;
        for (Customer customer : list) {
            result += ((long) idx++ * customer.id);
        }
        System.out.println(result);
    }

    private static class Customer implements Comparable<Customer> {
        int id, time, counter;

        public Customer(int id, int time, int counter) {
            this.id = id;
            this.time = time;
            this.counter = counter;
        }

        @Override
        public int compareTo(Customer customer) {
            if (this.time == customer.time) return this.counter - customer.counter;
            return this.time - customer.time;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
