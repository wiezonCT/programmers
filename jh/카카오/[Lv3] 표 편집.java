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

    public static void main(String[] args) {
        System.out.print(solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node(-1, 1, 0, false));
        for (int i = 1; i < n - 1; i++) {
            nodes.add(new Node(i - 1, i + 1, i, false));
        }
        nodes.add(new Node(n - 2, -1, n - 1, false));

        nodes = playCommand(nodes, k, cmd);

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

    private static List<Node> playCommand(List<Node> nodes, int k, String[] cmd) {
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
                    cur.isDeleted = true;
                    deleteStack.push(cur.idx);

                    if (cur.next == -1) {
                        Node previous = cur;

                        while (previous.isDeleted && previous.idx != 0) {
                            previous = nodes.get(previous.previous);
                        }

                        previous.next = cur.next;
                        cur = previous;
                        break;
                    }

                    Node next = cur;
                    while (next.isDeleted && next.idx != 0) {
                        next = nodes.get(next.next);
                    }

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
    }
}