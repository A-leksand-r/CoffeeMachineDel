package test.task.del.coffeemachinedel.services;

import test.task.del.coffeemachinedel.entities.Drink;

import java.util.Optional;

public interface DrinksService {

    Optional<Drink> findByName(String name);

    void save(String drinkName);

    Drink getPopularDrink();
}
