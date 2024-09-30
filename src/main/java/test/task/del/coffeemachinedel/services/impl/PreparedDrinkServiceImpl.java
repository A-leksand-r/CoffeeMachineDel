package test.task.del.coffeemachinedel.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.entities.PreparedDrinks;
import test.task.del.coffeemachinedel.repositiories.PreparedDrinksRepository;
import test.task.del.coffeemachinedel.services.PreparedDrinkService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PreparedDrinkServiceImpl implements PreparedDrinkService {

    private final PreparedDrinksRepository preparedDrinksRepository;

    public void save(Drink drink) {
        preparedDrinksRepository.save(new PreparedDrinks(null, drink, LocalDateTime.now()));
    }
}
