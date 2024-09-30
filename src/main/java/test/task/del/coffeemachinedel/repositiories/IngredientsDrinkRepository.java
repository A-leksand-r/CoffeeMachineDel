package test.task.del.coffeemachinedel.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.entities.IngredientsDrink;

import java.util.List;

@Repository
public interface IngredientsDrinkRepository extends JpaRepository<IngredientsDrink, Long> {

    List<IngredientsDrink> findAllByDrink(Drink drink);
}
