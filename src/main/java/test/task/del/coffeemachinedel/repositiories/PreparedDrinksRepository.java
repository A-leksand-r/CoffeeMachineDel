package test.task.del.coffeemachinedel.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.del.coffeemachinedel.entities.PreparedDrinks;

@Repository
public interface PreparedDrinksRepository extends JpaRepository<PreparedDrinks, Long> {

}
