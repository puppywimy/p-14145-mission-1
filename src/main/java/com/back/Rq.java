package com.back;

import java.util.HashMap;

public class Rq {
    private final String actionName;
    private final HashMap<String, String> queryMap = new HashMap<>();

    public Rq(String url) {
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

    public String getActionName() {
        return this.actionName;
    }

    public String getParam(String key, String fallbackValue) {
        return queryMap.getOrDefault(key, fallbackValue);
    }

    public int getParamAsInt(String key, int fallbackValue) {
        String value = getParam(key, "");
        if (value.isBlank()) return fallbackValue;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return fallbackValue;
        }
    }
}
