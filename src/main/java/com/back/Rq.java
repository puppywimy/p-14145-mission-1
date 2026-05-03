package com.back;

import java.util.HashMap;

class Rq {
    private final String actionName;
    private final HashMap<String, String> queryMap = new HashMap<>();

    Rq(String url) {
        String[] actionNameAndQueriesString = url.split("\\?", 2);
        this.actionName = actionNameAndQueriesString[0];
        String queriesString = actionNameAndQueriesString.length > 1 ? actionNameAndQueriesString[1] : "";
        String[] queryStrings = queriesString.split("&");
        for (String queryString : queryStrings) {
            String[] keyAndValue = queryString.split("=", 2);
            String key = keyAndValue[0].strip();
            String value = keyAndValue.length > 1 ? keyAndValue[1].strip() : "";
            if (key.isBlank() || value.isBlank()) continue;
            queryMap.put(key, value);
        }
    }

    String getActionName() {
        return this.actionName;
    }

    String getParam(String key, String fallbackValue) {
        String value = queryMap.get(key);
        return value == null ? fallbackValue : value;
    }

    int getParamAsInt(String key, int fallbackValue) {
        String value = getParam(key, "");
        if (value.isBlank()) return fallbackValue;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return fallbackValue;
        }
    }
}
