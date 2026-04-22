package com.back;

import java.util.ArrayList;
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

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

public class Main {
    static void main() {
        System.out.println("== 명언 앱 ==");

        Scanner sc = new Scanner(System.in);

        ArrayList<Quote> quotes = new ArrayList<>();

        while (true) {
            System.out.print("명언) ");
            String input = sc.nextLine().strip();
            String[] inputSlices = input.split("\\?id=");
            String command = inputSlices[0];
            int id = -1;
            if (inputSlices.length > 1) {
                try {
                    id = Integer.parseInt(inputSlices[1]);
                } catch (NumberFormatException exception) {
                    //
                }
            }

            if (command.equals("종료")) break;
            if (command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine().strip();
                System.out.print("작가 : ");
                String author = sc.nextLine().strip();

                quotes.add(new Quote(content, author));
                System.out.println(quotes.size() + "번 명언이 등록되었습니다.");
            }
            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = quotes.size() - 1; i >= 0; i--) {
                    Quote currentQuote = quotes.get(i);

                    if (currentQuote != null) {
                        System.out.println((i + 1) + " / " + currentQuote.getAuthor() + " / " + currentQuote.getContent());
                    }
                }
            }
            if (command.equals("삭제")) {
                if (id > 0) {
                    int index = id - 1;
                    Quote selectedQuote = index < quotes.size() ? quotes.get(index) : null;
                    boolean isQuotePresent = selectedQuote != null;
                    if (isQuotePresent) {
                        quotes.set(index, null);
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                    } else {
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                }
            }
            if (command.equals("수정")) {
                if (id > 0) {
                    int index = id - 1;
                    Quote selectedQuote = index < quotes.size() ? quotes.get(index) : null;
                    boolean isQuotePresent = selectedQuote != null;
                    if (isQuotePresent) {
                        System.out.println("명언(기존) : " + selectedQuote.getContent());
                        System.out.print("명언 : ");
                        selectedQuote.setContent(sc.nextLine().strip());
                        System.out.println("작가(기존) : " + selectedQuote.getAuthor());
                        System.out.print("작가 : ");
                        selectedQuote.setAuthor(sc.nextLine().strip());
                    } else {
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                }
            }
        }
    }
}
