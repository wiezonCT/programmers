package org.example;

import java.util.*;

class Solution {

    static List<Character> operations = Arrays.asList('+', '-', '*');
    static List<Character> useOperations = new ArrayList<>();
    static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print(solution("50*6-3*2"));
    }

    public static long solution(String expression) {
        init(expression);
        return playPriorityCalculate();
    }

    private static void init(String expression) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (operations.contains(expression.charAt(i))) {
                useOperations.add(expression.charAt(i));
                numbers.add(Integer.parseInt(tmp.toString()));
                tmp = new StringBuilder();
                continue;
            }

            tmp.append(expression.charAt(i));
        }

        numbers.add(Integer.parseInt(tmp.toString()));
    }

    private static long playPriorityCalculate() {
        long result = -1;

        for (Character op1 : operations) {
            for (Character op2 : operations) {
                if (op1.equals(op2)) {
                    continue;
                }
                for (Character op3 : operations) {
                    if (op1.equals(op3)) {
                        continue;
                    }
                    if (op2.equals(op3)) {
                        continue;
                    }

                    result = Math.max(result ,Math.abs(priorityCalculate(op1, op2, op3)));
                }
            }
        }

        return result;
    }

    private static long priorityCalculate(Character first, Character second, Character third) {
        int n = useOperations.size();

        // 첫 번째 우선순위로 계산
        Stack<Long> firstCalculation = new Stack<>();
        List<Character> firstOperations = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            Character operation = useOperations.get(i);

            if (operation == first) {
                // 연속된 경우
                if (i - 1 >= 0 && visited[i]) {
                    firstCalculation.add(calculate(firstCalculation.pop(), numbers.get(i + 1), operation));
                    visited[i] = visited[i + 1] = true;
                    continue;
                }

                firstCalculation.add(calculate(numbers.get(i), numbers.get(i + 1), operation));
                visited[i + 1] = true;
                continue;
            }

            firstOperations.add(operation);
            if (visited[i]) {
                continue;
            }
            firstCalculation.add((long) numbers.get(i));
        }

        if (!visited[n]) {
            firstCalculation.add((long) numbers.get(n));
        }

        Stack<Long> secondCalculation = new Stack<>();
        List<Character> secondOperations = new ArrayList<>();
        visited = new boolean[firstCalculation.size()];

        for (int i = 0; i < firstOperations.size(); i++) {
            Character operation = firstOperations.get(i);

            if (operation == second) {
                // 연속된 경우
                if (i - 1 >= 0 && visited[i]) {
                    secondCalculation.add(calculate(secondCalculation.pop(), firstCalculation.get(i + 1), operation));
                    visited[i + 1] = true;
                    continue;
                }

                secondCalculation.add(calculate(firstCalculation.get(i), firstCalculation.get(i + 1), operation));
                visited[i] = visited[i + 1] = true;
                continue;
            }

            secondOperations.add(operation);

            if (visited[i]) {
                continue;
            }

            secondCalculation.add(firstCalculation.get(i));
        }

        if (!visited[firstCalculation.size() - 1]) {
            secondCalculation.add((long) firstCalculation.get(firstCalculation.size() - 1));
        }

        long result = secondCalculation.get(0);

        for (int i = 1; i <= secondOperations.size(); i++) {
            result = calculate(result, secondCalculation.get(i), third);
        }

        return result;
    }

    private static Long calculate(long num1, long num2, Character op) {
        return switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            default -> 0L;
        };
    }
}