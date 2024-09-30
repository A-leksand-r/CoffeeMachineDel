package test.task.del.coffeemachinedel.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.task.del.coffeemachinedel.entities.Drink;

import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<Drink, Long> {

    Optional<Drink> findByDrinkName(String drinkName);

    boolean existsByDrinkName(String drinkName);

    @Query(value = "SELECT d.* FROM drink AS d JOIN prepared_drinks AS pd ON pd.drink_id = d.id \n" +
            "GROUP BY d.id ORDER BY COUNT(pd.drink_id) DESC LIMIT 1", nativeQuery = true)
    Optional<Drink> findPopularDrink();
}
