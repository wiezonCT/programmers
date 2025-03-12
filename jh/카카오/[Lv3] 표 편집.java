package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {

    static final String DOWN = "D";
    static final String UP = "U";
    static final String DELETE = "C";
    static final String REVERT = "Z";
    static final String CIRCLE = "O";
    static final String CROSS = "X";

    static List<Node> nodes = new ArrayList<Node>();

    public static void main(String[] args) {
        System.out.print(solution(5, 2, new String[]{"U 5", "C", "C", "C", "C", "C", "C", "Z", "Z", "Z", "Z", "Z", "Z"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        makeNodes(n);

        nodes = playCommand(nodes, n, k, cmd);

        StringBuilder answer = new StringBuilder();

        for (Node node : nodes) {
            if (node.isDeleted) {
                answer.append(CROSS);
                continue;
            }
            answer.append(CIRCLE);
        }

        return answer.toString();
    }

    private static void makeNodes(int n) {
        nodes.add(new Node(-1, 1, 0, false));
        for (int i = 1; i < n - 1; i++) {
            nodes.add(new Node(i - 1, i + 1, i, false));
        }
        nodes.add(new Node(n - 2, -1, n - 1, false));
    }

    private static List<Node> playCommand(List<Node> nodes, int n, int k, String[] cmd) {
        Node cur = nodes.get(k);
        Stack<Integer> deleteStack = new Stack<>();

        for (String c : cmd) {
            String[] split = c.split(" ");

            switch (split[0]) {
                case DOWN:
                    for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                        if (cur.next == -1) {
                            break;
                        }

                        cur = nodes.get(cur.next);
                    }
                    break;

                case UP:
                    for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                        if (cur.previous == -1) {
                            break;
                        }

                        cur = nodes.get(cur.previous);
                    }
                    break;

                case DELETE:
                    if (deleteStack.size() == n) {
                        break;
                    }

                    cur.isDeleted = true;
                    deleteStack.push(cur.idx);

                    if (cur.next == -1) {
                        Node previous = cur;

                        while (previous.isDeleted && previous.idx != 0 && previous.previous != -1) {
                            previous = nodes.get(previous.previous);
                        }

                        previous.next = cur.next;
                        cur = previous;
                        break;
                    }

                    Node next = cur;
                    Node previous = cur;

                    while (next.isDeleted && next.idx != n - 1 && next.next != -1) {
                        next = nodes.get(nodes.get(next.next).idx);
                    }

                    while (previous.isDeleted && previous.idx != 0 && previous.previous != -1) {
                        previous = nodes.get(nodes.get(previous.previous).idx);
                    }

                    previous.next = cur.next;
                    next.previous = cur.previous;
                    cur = next;
                    break;

                case REVERT:
                    if (deleteStack.isEmpty()) {
                        continue;
                    }

                    Node revertNode = nodes.get(deleteStack.pop());
                    revertNode.isDeleted = false;
                    if (revertNode.previous != -1) {
                        Node previousNode = nodes.get(revertNode.previous);
                        previousNode.next = revertNode.idx;
                    }

                    if (revertNode.next != -1) {
                        Node nextNode = nodes.get(revertNode.next);
                        nextNode.previous = revertNode.idx;
                    }
            }

            System.out.println(c);
            cur.print();
            for (Node node : nodes) {
                node.print();
            }
        }

        return nodes;
    }

    private static class Node {
        int previous, next;
        int idx;
        boolean isDeleted;

        Node(int previous, int next, int idx, boolean isDeleted) {
            this.previous = previous;
            this.next = next;
            this.idx = idx;
            this.isDeleted = isDeleted;
        }

        void print() {
            System.out.printf("%d %d %d %b\n", previous, next, idx, isDeleted);
        }
    }
}