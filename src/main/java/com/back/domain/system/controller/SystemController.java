package com.back.domain.system.controller;

import java.util.Scanner;

public class SystemController {
    public void actionQuit(Scanner scanner) {
        System.out.println("프로그램이 종료합니다.");
        scanner.close();
    }
}
