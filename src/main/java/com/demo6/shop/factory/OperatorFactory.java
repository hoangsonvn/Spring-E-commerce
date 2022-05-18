package com.demo6.shop.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    static Map<String, Operator> operatorMap = new HashMap<>();

    static {
        operatorMap.put("default", new Default());
        operatorMap.put("under50", new Under50());
        operatorMap.put("50to70", new Under50To70());
        operatorMap.put("greaterthan70", new To70());
    }

    public static Optional<Operator> getOperation(String operation) {
        return Optional.ofNullable(operatorMap.get(operation));
    }
}
