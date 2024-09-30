package test.task.del.coffeemachinedel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.del.coffeemachinedel.coffeeMachine.MachineStatus;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.entities.IngredientsDrink;
import test.task.del.coffeemachinedel.enums.Ingredients;
import test.task.del.coffeemachinedel.enums.Status;
import test.task.del.coffeemachinedel.exceptions.BusinessException;
import test.task.del.coffeemachinedel.exceptions.ServiceException;
import test.task.del.coffeemachinedel.exceptions.enums.DrinkServiceFailCode;
import test.task.del.coffeemachinedel.coffeeMachine.feeders.CoffeeHopper;
import test.task.del.coffeemachinedel.coffeeMachine.feeders.MilkTank;
import test.task.del.coffeemachinedel.coffeeMachine.feeders.WaterTank;
import test.task.del.coffeemachinedel.exceptions.enums.IngredientServiceFailCode;
import test.task.del.coffeemachinedel.repositiories.IngredientsDrinkRepository;
import test.task.del.coffeemachinedel.services.CoffeeService;
import test.task.del.coffeemachinedel.services.DrinksService;
import test.task.del.coffeemachinedel.services.PreparedDrinkService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {

    private final DrinksService drinksService;
    private final IngredientsDrinkRepository ingredientsDrinkRepository;
    private final PreparedDrinkService preparedDrinkService;

    @Override
    public void makeDrink(String drinkName) {
        MachineStatus.setStatus(Status.BUSY);
        Optional<Drink> drink = drinksService.findByName(drinkName);

        if (drink.isEmpty()) {
            MachineStatus.setStatus(Status.READY);
            throw new BusinessException("Напиток \"" + drinkName + "\" не существует", DrinkServiceFailCode.NOT_FOUND);
        }

        List<IngredientsDrink> ingredientsForMake = ingredientsDrinkRepository.findAllByDrink(drink.get());

        if (ingredientsForMake.isEmpty()) {
            MachineStatus.setStatus(Status.READY);
            throw new ServiceException("Рецепт напитка не найден", IngredientServiceFailCode.NOT_FOUND);
        }

        List<String> errors =  checkAmountOfIngredients(ingredientsForMake);

        if (!errors.isEmpty()) {
            MachineStatus.setStatus(Status.READY);
            throw new ServiceException("Недостаточно ингредиентов", DrinkServiceFailCode.INSUFFICIENT_QUANTITY,
                    String.join("\n", errors));
        }

        ingredientsForMake.sort(Comparator.comparing(IngredientsDrink::getPriority));

        for (IngredientsDrink ingredient : ingredientsForMake) {
            if (Ingredients.isPresent(ingredient.getIngredient())) {
                switch (Ingredients.valueOf(ingredient.getIngredient())) {
                    case COFFEE -> CoffeeHopper.makeCoffee(ingredient.getQuantity());
                    case MILK -> MilkTank.makeMilk(ingredient.getQuantity());
                    case WHIPPED_MILK -> MilkTank.makeWhippedMilk(ingredient.getQuantity());
                    case HOT_WATER -> WaterTank.makeHotWater(ingredient.getQuantity());
                }
            }
        }

        preparedDrinkService.save(drink.get());

        MachineStatus.setStatus(Status.READY);
    }

    private List<String> checkAmountOfIngredients(List<IngredientsDrink> ingredientsForMake) {
        List<String> errors = new ArrayList<>();

        short requiredAmountOfCoffee = 0;
        short requiredAmountMilk = 0;
        short requiredAmountWater = 0;

        for (IngredientsDrink ingredient : ingredientsForMake) {
            if (Ingredients.isPresent(ingredient.getIngredient())) {
                switch (Ingredients.valueOf(ingredient.getIngredient())) {
                    case COFFEE -> requiredAmountOfCoffee += ingredient.getQuantity();
                    case MILK, WHIPPED_MILK -> requiredAmountMilk += ingredient.getQuantity();
                    case HOT_WATER -> requiredAmountWater += ingredient.getQuantity();
                }
            }
        }

        if (!CoffeeHopper.isEnoughCoffee(requiredAmountOfCoffee))
            errors.add("Недостаточно ингредиента - COFFEE");
        if (!MilkTank.isEnoughMilk(requiredAmountMilk))
            errors.add("Недостаточно ингредиента - MILK");
        if (!WaterTank.isEnoughWater(requiredAmountWater))
            errors.add("Недостаточно ингредиента - WATER");
        return errors;
    }
}
