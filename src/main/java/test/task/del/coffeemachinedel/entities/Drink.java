package test.task.del.coffeemachinedel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String drinkName;

    @OneToMany(mappedBy = "drink", fetch = FetchType.LAZY)
    private List<IngredientsDrink> ingredientsDrinks;

    @OneToMany(mappedBy = "drink", fetch = FetchType.LAZY)
    private List<PreparedDrinks> preparedDrinks;
}
