package com.back;

public class Main {
    static void main() {
        App app = new App();
        app.run();

//        testRq0();
//        System.out.println();
//        testRq1();
//        System.out.println();
//        testRq2();
//        System.out.println();
//        testRq3();
//        System.out.println();
//        testRq4();
//        System.out.println();
//        testRq5();
//        System.out.println();
//        testRq6();
//        System.out.println();
//        testRq7();
//        System.out.println();
//        testRq8();
//        System.out.println();
//        testRq9();
    }

    private static void testRq0() {
        System.out.printf("url: %s\n", "삭제?id=1");
        Rq rq = new Rq("삭제?id=1");
        System.out.printf("actionName: %s\n", rq.getActionName());
    }

    private static void testRq1() {
        System.out.printf("url: %s\n", "삭제?id=1");
        Rq rq = new Rq("삭제?id=1");
        System.out.printf("id: %d\n", rq.getParamAsInt("id", -1));
    }

    private static void testRq2() {
        System.out.printf("url: %s\n", "삭제");
        Rq rq = new Rq("삭제");
        System.out.printf("id: %d\n", rq.getParamAsInt("id", -1));
    }

    private static void testRq3() {
        System.out.printf("url: %s\n", "삭제?id=");
        Rq rq = new Rq("삭제?id=");
        System.out.printf("id: %d\n", rq.getParamAsInt("id", -1));
    }

    private static void testRq4() {
        System.out.printf("url: %s\n", "삭제?id=일번");
        Rq rq = new Rq("삭제?id=일번");
        System.out.printf("id: %d\n", rq.getParamAsInt("id", -1));
    }

    private static void testRq5() {
        System.out.printf("url: %s\n", "목록?searchKeyword=영광");
        Rq rq = new Rq("목록?searchKeyword=영광");
        System.out.printf("searchKeyword: %s\n", rq.getParam("searchKeyword", ""));
    }

    private static void testRq6() {
        System.out.printf("url: %s\n", "목록");
        Rq rq = new Rq("목록");
        System.out.printf("searchKeyword: %s\n", rq.getParam("searchKeyword", ""));
    }

    private static void testRq7() {
        System.out.printf("url: %s\n", "목록?searchKeyword=");
        Rq rq = new Rq("목록?searchKeyword=");
        System.out.printf("searchKeyword: %s\n", rq.getParam("searchKeyword", ""));
    }

    private static void testRq8() {
        System.out.printf("url: %s\n", "목록?page=5&searchKeyword=영광");
        Rq rq = new Rq("목록?page=5&searchKeyword=영광");
        System.out.printf("page: %d\n", rq.getParamAsInt("page", -1));
        System.out.printf("searchKeyword: %s\n", rq.getParam("searchKeyword", ""));
    }

    private static void testRq9() {
        System.out.printf("url: %s\n", "목록?searchKeyword=영광&page=5");
        Rq rq = new Rq("목록?page=5&searchKeyword=영광");
        System.out.printf("page: %d\n", rq.getParamAsInt("page", -1));
        System.out.printf("searchKeyword: %s\n", rq.getParam("searchKeyword", ""));
    }
}