package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer st;
    static int n;
    static int answer;
    static PriorityQueue<Meeting> meetings = new PriorityQueue<Meeting>();

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        init();
        startMeeting();
        System.out.print(answer);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    private static void startMeeting() {
        Meeting cur = meetings.poll();
        answer++;

        while (!meetings.isEmpty()) {
            Meeting temp = meetings.peek();

            if (temp.start < cur.end) {
                meetings.poll();
                continue;
            }

            cur = meetings.poll();
            answer++;
        }
    }

    static class Meeting implements Comparable<Meeting> {

        int start, end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }

            return this.end - o.end;
        }
    }
}