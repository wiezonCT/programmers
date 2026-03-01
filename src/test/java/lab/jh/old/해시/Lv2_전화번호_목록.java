package lab.jh.old.해시;// JJONGHYUNI

import java.util.Arrays;

class Lv2_전화번호_목록 {

    public static void main(String[] args) {
        System.out.print(solution(new String[]{"119", "97674223", "1195524421"}));
    }

    public static boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}