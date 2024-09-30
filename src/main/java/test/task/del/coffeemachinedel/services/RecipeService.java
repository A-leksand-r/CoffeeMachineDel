package test.task.del.coffeemachinedel.services;

import test.task.del.coffeemachinedel.dtos.IngredientDrinkDto;

import java.util.List;

public interface RecipeService {

    void saveRecipe(String drinkName, List<IngredientDrinkDto> ingredientsDrink);
}
