package test.task.del.coffeemachinedel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparedDrinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Drink.class)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(nullable = false)
    private LocalDateTime dateOfPreparation;
}
