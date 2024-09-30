package test.task.del.coffeemachinedel.dtos;

import lombok.Data;
import test.task.del.coffeemachinedel.entities.Drink;

@Data
public class BestDrinkDto {

    private Drink drink;

    private Integer orderCount;
}
