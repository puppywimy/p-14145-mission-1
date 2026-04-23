package com.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Quote {
    private int id;
    private String content;
    private String author;

    public Quote(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return this.id;
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

class App {
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<Quote> quotes = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명언) ");
            String input = sc.nextLine().strip();
            String[] inputSlices = input.split("\\?id=");
            String command = inputSlices[0];
            String parameter = "";
            if (inputSlices.length > 1) parameter = inputSlices[1].strip();

            switch (command) {
                case "종료":
                    return;
                case "등록":
                    actionCreate();
                    break;
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    if (!parameter.isBlank()) actionDelete(inputSlices[1]);
                    break;
                case "수정":
                    if (!parameter.isBlank()) actionUpdate(inputSlices[1]);
                    break;
            }
        }
    }

    private Quote[] cloneQuotes() {
        Quote[] quotes = this.quotes.stream()
                .filter((quote) -> quote != null)
                .map((quote) -> new Quote(quote.getId(), quote.getContent(), quote.getAuthor()))
                .toArray(Quote[]::new);
        return quotes;
    }

    private void actionCreate() {
        System.out.print("명언 : ");
        String content = sc.nextLine().strip();
        System.out.print("작가 : ");
        String author = sc.nextLine().strip();

        int id = create(content, author);

        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        Quote[] quotes = list((a, b) -> b.getId() - a.getId());

        for (Quote quote : quotes) {
            System.out.printf("%d / %s / %s\n", quote.getId(), quote.getAuthor(), quote.getContent());
        }
    }

    private void actionDelete(String stringId) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException exception) {
            System.out.println("id에 자연수를 입력해주세요.");
            return;
        }
        if (id <= 0) {
            System.out.println("id에 자연수를 입력해주세요.");
            return;
        }

        Quote selectedQuote = null;
        try {
            selectedQuote = read(id);
        } catch (Exception exception) {
            return;
        }
        if (selectedQuote == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        try {
            delete(id);
        } catch (Exception exception) {
            //
        }
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void actionUpdate(String stringId) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException exception) {
            System.out.println("id에 자연수를 입력해주세요.");
            return;
        }
        if (id <= 0) {
            System.out.println("id에 자연수를 입력해주세요.");
            return;
        }

        Quote selectedQuote = null;
        try {
            selectedQuote = read(id);
        } catch (Exception exception) {
            return;
        }
        if (selectedQuote == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        String newContent = "";
        String newAuthor = "";
        System.out.println("명언(기존) : " + selectedQuote.getContent());
        System.out.print("명언 : ");
        newContent = sc.nextLine().strip();
        System.out.println("작가(기존) : " + selectedQuote.getAuthor());
        System.out.print("작가 : ");
        newAuthor = sc.nextLine().strip();
        try {
            update(id, newContent, newAuthor);
        } catch (Exception exception) {
            //
        }
    }

    private int create(String content, String author) {
        int id = quotes.size() + 1;
        Quote quote = new Quote(id, content, author);
        quotes.add(quote);
        return id;
    }

    private Quote[] list() {
        return cloneQuotes();
    }

    private Quote[] list(Comparator<Quote> comparator) {
        Quote[] quotes = cloneQuotes();
        Arrays.sort(quotes, comparator);
        return quotes;
    }

    private Quote read(int id) throws Exception {
        if (id <= 0) throw new Exception();

        int index = id - 1;
        Quote selectedQuote = index < quotes.size() ? quotes.get(index) : null;
        if (selectedQuote == null) return null;

        return new Quote(selectedQuote.getId(), selectedQuote.getContent(), selectedQuote.getAuthor());
    }

    private void delete(int id) throws Exception {
        if (id <= 0) throw new Exception();

        int index = id - 1;
        if (index >= quotes.size()) return;
        quotes.set(index, null);
    }

    private void update(int id, String content, String author) throws Exception {
        if (id <= 0) throw new Exception();

        int index = id - 1;
        Quote selectedQuote = index < quotes.size() ? quotes.get(index) : null;
        if (selectedQuote == null) return;

        selectedQuote.setContent(content);
        selectedQuote.setAuthor(author);
    }
}
