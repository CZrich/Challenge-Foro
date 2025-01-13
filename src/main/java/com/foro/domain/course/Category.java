package com.foro.domain.course;

import java.util.Arrays;

public enum Category {
     BACKEND,
     FRONTEND,
     DATABASE,
    OTHERS;

    public static Category fromString(String input) {
        if (input == null || input.isBlank()) {
            return OTHERS;
        }
        return Arrays.stream(Category.values())
                .filter(category -> category.name().equalsIgnoreCase(input))
                .findFirst()
                .orElse(OTHERS);
    }
}
