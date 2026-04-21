package com.back;

import java.util.Scanner;

public class Main {
    static void main() {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명언) ");
            if (sc.nextLine().strip().equals("종료")) break;
        }
    }
}
