package test.task.del.coffeemachinedel.utils;

import test.task.del.coffeemachinedel.dtos.IngredientDrinkDto;
import test.task.del.coffeemachinedel.enums.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class ValidationOfIngredients {

    public static List<String> validateIngredients(List<IngredientDrinkDto> ingredients) {
        List<String> errors = new ArrayList<>();
        for (IngredientDrinkDto ingredient : ingredients) {
            if (ingredient.getPriority() == null) {
                errors.add("Поле \"Приоритет\" не может быть пустым");
            }

            if (ingredient.getIngredient() != null && Ingredients.isPresent(ingredient.getIngredient())) {
                switch (Ingredients.valueOf(ingredient.getIngredient())) {
                    case COFFEE -> validateIngredient(ingredient, errors, Ingredients.COFFEE);
                    case MILK -> validateIngredient(ingredient, errors, Ingredients.MILK);
                    case WHIPPED_MILK -> validateIngredient(ingredient, errors, Ingredients.WHIPPED_MILK);
                    case HOT_WATER -> validateIngredient(ingredient, errors, Ingredients.HOT_WATER);
                }
            } else errors.add("Ингредиента \"" + ingredient.getIngredient() + "\" не существует");
        }
        return errors;
    }

    private static void validateIngredient(IngredientDrinkDto ingredientDrink, List<String> errors, Ingredients ingredient) {
        if (ingredientDrink.getQuantity() != null) {
            if (ingredientDrink.getQuantity() < ingredient.getMinQuantity() ||
                    ingredientDrink.getQuantity() > ingredient.getMaxQuantity()) {
                errors.add("Допустимое количество " + ingredient + " от " +
                        ingredient.getMinQuantity() + " до " +
                        ingredient.getMaxQuantity());
            }
        } else errors.add("Поле \"Количество\" не может быть пустым");
    }

}
