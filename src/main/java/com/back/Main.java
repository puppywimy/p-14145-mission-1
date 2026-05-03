package com.back;

public class Main {
    static void main() {
        testRq();
//        App app = new App();
//        app.run();
    }

    static void testRq() {
        Rq rq = new Rq("삭제?id=1");
        String actionName = rq.getActionName();
        System.out.println(actionName);
    }
}