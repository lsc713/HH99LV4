package com.example.mission04.domain.lecture.strategy;

public class SortStrategyFactory {

    public static SortStrategy getSortStrategy(String sortBy) {
        return switch (sortBy) {
            case "price" -> new PriceSortStrategy();
            case "date" -> new DateSortStrategy();
            default -> new NameSortStrategy();
        };
    }
}
