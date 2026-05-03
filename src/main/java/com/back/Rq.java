package com.back;

class Rq {
    String actionName = "";

    Rq(String url) {
        String[] actionNameAndQueryString = url.split("\\?", 2);
        actionName = actionNameAndQueryString[0];
    }

    String getActionName() {
        return this.actionName;
    }
}
