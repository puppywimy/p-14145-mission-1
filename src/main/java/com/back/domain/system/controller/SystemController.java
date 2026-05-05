package com.back.domain.system.controller;

import java.util.Scanner;

public class SystemController {
    private final Scanner scanner;

    public SystemController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionQuit() {
        System.out.println("프로그램이 종료합니다.");
        scanner.close();
    }
}
