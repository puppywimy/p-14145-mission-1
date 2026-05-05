package com.back.domain.quote.controller;

import com.back.Quote;
import com.back.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuoteController {
    private final Scanner scanner;
    private final ArrayList<Quote> quotes = new ArrayList<>();
    private int lastId = 0;

    public QuoteController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<Quote> quotes = list();

        for (Quote quote : quotes) {
            System.out.printf("%d / %s / %s\n", quote.getId(), quote.getAuthor(), quote.getContent());
        }
    }

    public void actionCreate() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().strip();
        System.out.print("작가 : ");
        String author = scanner.nextLine().strip();

        Quote createdQuote = create(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", createdQuote.getId());
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id <= 0) {
            System.out.println("id를 자연수로 입력해주세요.");
            return;
        }

        int deletedId = delete(id);

        if (deletedId == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", deletedId);
    }

    public void actionUpdate(Rq rq) {
        int id = rq.getParamAsInt("id", 0);
        if (id <= 0) {
            System.out.println("id를 자연수로 입력해주세요.");
            return;
        }

        Quote selectedQuote = findById(id);
        if (selectedQuote == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        String newContent = "";
        String newAuthor = "";

        System.out.println("명언(기존) : " + selectedQuote.getContent());
        System.out.print("명언 : ");
        newContent = scanner.nextLine().strip();

        System.out.println("작가(기존) : " + selectedQuote.getAuthor());
        System.out.print("작가 : ");
        newAuthor = scanner.nextLine().strip();

        update(id, newContent, newAuthor);
    }

    private List<Quote> list() {
        return quotes.reversed();
    }

    private Quote create(String content, String author) {
        Quote newQuote = new Quote(++lastId, content, author);
        quotes.add(newQuote);
        return newQuote;
    }

    private int delete(int id) {
        int index = findIndexById(id);
        if (index == -1) return -1;

        Quote removedQuote = quotes.remove(index);

        return removedQuote.getId();
    }

    private void update(int id, String newContent, String newAuthor) {
        Quote selectedQuote = findById(id);
        if (selectedQuote == null) return;

        selectedQuote.setContent(newContent);
        selectedQuote.setAuthor(newAuthor);
    }

    private int findIndexById(int id) {
        int index = -1;
        for (int i = 0; i < quotes.size(); i++) {
            if (quotes.get(i).getId() != id) continue;
            index = i;
            break;
        }
        return index;
    }

    private Quote findById(int id) {
        int index = findIndexById(id);
        return index == -1 ? null : quotes.get(index);
    }
}
