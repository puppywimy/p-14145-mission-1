package com.back;

import java.util.Scanner;

class App {
    private final Scanner scanner = new Scanner(System.in);
    private int quotesArrayMaxSize = 100;
    private Quote[] quotesArray = new Quote[quotesArrayMaxSize];
    private int quotesArraySize = 0;
    private int lastId = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명언) ");
            String input = scanner.nextLine().strip();

            String[] commandAndQueryString = input.split("\\?", 2);
            String command = commandAndQueryString[0];
            String queryString = commandAndQueryString.length > 1 ? commandAndQueryString[1] : "";

            switch (command) {
                case "종료" -> {
                    scanner.close();
                    return;
                }
                case "목록" -> actionList();
                case "등록" -> actionCreate();
                case "삭제" -> actionDelete(queryString);
                case "수정" -> actionUpdate(queryString);
            }
        }
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        Quote[] quotes = list();

        for (Quote quote : quotes) {
            System.out.printf("%d / %s / %s\n", quote.getId(), quote.getAuthor(), quote.getContent());
        }
    }

    private void actionCreate() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().strip();
        System.out.print("작가 : ");
        String author = scanner.nextLine().strip();

        Quote createdQuote = create(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", createdQuote.getId());
    }

    private void actionDelete(String queryString) {
        String[] keyAndValue = queryString.split("=", 2);
        String key = keyAndValue[0];
        String value = keyAndValue.length > 1 ? keyAndValue[1] : "";

        if (!key.equals("id") || value.isBlank()) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return;
        }

        int deletedId = delete(id);

        if (deletedId == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 명언이 삭제되었습니다.\n", deletedId);
    }

    private void actionUpdate(String queryString) {
        String[] keyAndValue = queryString.split("=", 2);
        String key = keyAndValue[0];
        String value = keyAndValue.length > 1 ? keyAndValue[1] : "";

        if (!key.equals("id") || value.isBlank()) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
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

    private Quote[] list() {
        Quote[] quotes = new Quote[quotesArraySize];
        for (int i = 0; i < quotesArraySize; i++) {
            quotes[i] = quotesArray[quotesArraySize - 1 - i];
        }
        return quotes;
    }

    private Quote create(String content, String author) {
        Quote newQuote = new Quote(++lastId, content, author);
        if (quotesArraySize >= quotesArrayMaxSize) growQuotesArray();
        quotesArray[quotesArraySize++] = newQuote;
        return newQuote;
    }

    private int delete(int id) {
        int index = findIndexById(id);
        if (index == -1) return -1;

        int tempId = quotesArray[index].getId();

        for (int i = index; i < quotesArraySize - 1; i++) {
            quotesArray[i] = quotesArray[i + 1];
        }
        quotesArray[--quotesArraySize] = null;

        return tempId;
    }

    private void update(int id, String newContent, String newAuthor) {
        Quote selectedQuote = findById(id);
        if (selectedQuote == null) return;

        selectedQuote.setContent(newContent);
        selectedQuote.setAuthor(newAuthor);
    }

    private void growQuotesArray() {
        Quote[] newQuotesArray = new Quote[quotesArrayMaxSize * 2];
        quotesArrayMaxSize *= 2;
        for (int i = 0; i < quotesArraySize; i++) {
            newQuotesArray[i] = quotesArray[i];
        }
        quotesArray = newQuotesArray;
    }

    private int findIndexById(int id) {
        if (quotesArraySize <= 0) return -1;

        int index = -1;
        for (int i = 0; i < quotesArraySize; i++) {
            if (quotesArray[i].getId() != id) continue;
            index = i;
            break;
        }

        return index;
    }

    private Quote findById(int id) {
        int index = findIndexById(id);
        return index == -1 ? null : quotesArray[index];
    }
}
