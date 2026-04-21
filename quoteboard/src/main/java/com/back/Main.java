package com.back;

import java.util.Scanner;

public class Main {
    static void main() {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);

        int index = 1;

        while (true) {
            System.out.print("명언) ");
            String command = sc.nextLine().strip();

            if (command.equals("종료")) break;
            if (command.equals("등록")) {
                System.out.print("명언 : ");
                String quote = sc.nextLine().strip();
                System.out.print("작가 : ");
                String author = sc.nextLine().strip();
                System.out.println(index + "번 명언이 등록되었습니다.");
            }
        }
    }
}
