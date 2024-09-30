package test.task.del.coffeemachinedel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.exceptions.BusinessException;
import test.task.del.coffeemachinedel.exceptions.ServiceException;
import test.task.del.coffeemachinedel.exceptions.enums.DrinkServiceFailCode;
import test.task.del.coffeemachinedel.repositiories.DrinksRepository;
import test.task.del.coffeemachinedel.services.DrinksService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinksServiceImpl implements DrinksService {

    private final DrinksRepository drinksRepository;

    @Override
    public void save(String drinkName) {
        if (drinksRepository.existsByDrinkName(drinkName)) {
            throw new BusinessException("Напиток уже существует", DrinkServiceFailCode.ALREADY_EXIST);
        }
        drinksRepository.save(new Drink(null, drinkName, null, null));
    }

    @Override
    public Drink getPopularDrink() {
        Optional <Drink> drink = drinksRepository.findPopularDrink();
        if (drink.isEmpty()) {
            throw new ServiceException("Заказанных напитков не существует", DrinkServiceFailCode.NOT_FOUND);
        }
        return drink.get();
    }

    @Override
    public Optional<Drink> findByName(String name) {
        return drinksRepository.findByDrinkName(name);
    }


}
