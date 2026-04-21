package com.back;

import java.util.Scanner;

class Quote {
    private String content;
    private String author;

    public Quote(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.author;
    }
}

public class Main {
    static void main() {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);

        Quote[] quotes = new Quote[10 + 1];
        int lastIndex = 0;

        while (true) {
            System.out.print("명언) ");
            String command = sc.nextLine().strip();

            if (command.equals("종료")) break;
            if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine().strip();
                System.out.print("작가 : ");
                String author = sc.nextLine().strip();

                quotes[++lastIndex] = new Quote(content, author);
                System.out.println(lastIndex + "번 명언이 등록되었습니다.");
            }
            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = lastIndex; i > 0; i--) {
                    Quote currentQuote = quotes[i];
                    System.out.println(i + " / " + currentQuote.getAuthor() + " / " + currentQuote.getContent());
                }
            }
        }
    }
}
