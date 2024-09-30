package test.task.del.coffeemachinedel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.del.coffeemachinedel.dtos.IngredientDrinkDto;
import test.task.del.coffeemachinedel.services.DrinksService;
import test.task.del.coffeemachinedel.services.IngredientsDrinkService;
import test.task.del.coffeemachinedel.services.RecipeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final DrinksService drinksService;
    private final IngredientsDrinkService ingredientsDrinkService;

    @Override
    @Transactional
    public void saveRecipe(String drinkName, List<IngredientDrinkDto> ingredientsDrink) {
        drinksService.save(drinkName);
        ingredientsDrinkService.saveRecipe(drinkName, ingredientsDrink);
    }
}
