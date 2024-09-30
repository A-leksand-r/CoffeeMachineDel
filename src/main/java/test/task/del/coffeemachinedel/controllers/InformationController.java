package test.task.del.coffeemachinedel.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import test.task.del.coffeemachinedel.entities.Drink;
import test.task.del.coffeemachinedel.services.DrinksService;

@Controller
@RequiredArgsConstructor
@Tag(name = "Информационный контроллер", description = "Позволяет получить информацию о кофемашине")
public class InformationController {

    private final DrinksService drinksService;

    @Operation(summary = "Получить самый популярный напиток", description = "Возвращает самый популярный напиток")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получено",
                    content = @Content(mediaType = "text/plain;charset=UTF-8")),
            @ApiResponse(responseCode = "404", description = "Нет заказанных напитков", content = @Content())
    })
    @GetMapping("/coffeeMachine/popularDrink")
    public ResponseEntity<String> getPopularDrink() {
        Drink popularDrink = drinksService.getPopularDrink();
        return new ResponseEntity<>(popularDrink.getDrinkName(), HttpStatus.OK);
    }
}
