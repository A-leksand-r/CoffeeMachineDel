package test.task.del.coffeemachinedel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.del.coffeemachinedel.dtos.IngredientDrinkDto;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.entities.IngredientsDrink;
import test.task.del.coffeemachinedel.exceptions.BusinessException;
import test.task.del.coffeemachinedel.exceptions.enums.IngredientServiceFailCode;
import test.task.del.coffeemachinedel.repositiories.DrinksRepository;
import test.task.del.coffeemachinedel.repositiories.IngredientsDrinkRepository;
import test.task.del.coffeemachinedel.services.IngredientsDrinkService;
import test.task.del.coffeemachinedel.utils.ValidationOfIngredients;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientsDrinkServiceImpl implements IngredientsDrinkService {

    private final IngredientsDrinkRepository ingredientsDrinkRepository;

    private final DrinksRepository drinksRepository;

    @Override
    public void saveRecipe(String drinkName, List<IngredientDrinkDto> ingredientDrinkDto) {
        List<String> errors = ValidationOfIngredients.validateIngredients(ingredientDrinkDto);
        if (!errors.isEmpty()) {
            throw new BusinessException("Ошибка валидации", IngredientServiceFailCode.VALIDATE_FAILED, String.join("\n", errors));
        }

        Optional<Drink> drink = drinksRepository.findByDrinkName(drinkName);
        if (drink.isPresent()) {
            for (IngredientDrinkDto ingredient : ingredientDrinkDto) {
                ingredientsDrinkRepository.save(new IngredientsDrink(null, drink.get(), ingredient.getPriority(),
                        ingredient.getIngredient(), ingredient.getQuantity()));
            }
        }
    }
}
