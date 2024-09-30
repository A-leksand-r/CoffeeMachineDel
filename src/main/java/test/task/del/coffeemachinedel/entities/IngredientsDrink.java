package test.task.del.coffeemachinedel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Drink.class)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private Short priority;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private Short quantity;
}
