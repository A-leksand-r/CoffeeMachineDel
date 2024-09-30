package test.task.del.coffeemachinedel.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Ingredients {
    COFFEE(30, 90),
    MILK(10, 300),
    WHIPPED_MILK(10, 150),
    HOT_WATER(10, 500);

    private final int minQuantity;
    private final int maxQuantity;

    Ingredients(int minQuantity, int maxQuantity) {
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public static boolean isPresent(String ingredientName) {
        return Arrays.stream(values()).anyMatch(element -> element.toString().equals(ingredientName));
    }
}
