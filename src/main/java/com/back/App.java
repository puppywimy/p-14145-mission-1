package com.back;

import com.back.domain.quote.controller.QuoteController;
import com.back.domain.system.controller.SystemController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class App {
    void run() {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        SystemController systemController = new SystemController(scanner);
        QuoteController quoteController = new QuoteController(scanner);

        while (true) {
            System.out.print("명언) ");
            Rq rq = new Rq(scanner.nextLine().strip());

            switch (rq.getActionName()) {
                case "종료" -> {
                    systemController.actionQuit();
                    return;
                }
                case "목록" -> quoteController.actionList();
                case "등록" -> quoteController.actionCreate();
                case "삭제" -> quoteController.actionDelete(rq);
                case "수정" -> quoteController.actionUpdate(rq);
            }
        }
    }
}
