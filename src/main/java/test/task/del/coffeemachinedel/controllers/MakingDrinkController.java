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
import org.springframework.web.bind.annotation.PostMapping;
import test.task.del.coffeemachinedel.coffeeMachine.MachineStatus;
import test.task.del.coffeemachinedel.enums.Status;
import test.task.del.coffeemachinedel.services.CoffeeService;

@Controller
@RequiredArgsConstructor
@Tag(name = "Контроллер для приготовления напитков", description = "Позволяет приготовить напиток")
public class MakingDrinkController {

    private final CoffeeService coffeeService;

    @Operation(summary = "Приготовить напиток", description = "Выполняет приготовление напитка переданного в метод")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Приготовление прошло успешно",
                    content = @Content(mediaType = "text/plain;charset=UTF-8")),
            @ApiResponse(responseCode = "400",
                    description = "Необходимо передать название напитка",
                    content = @Content()),
            @ApiResponse(responseCode = "409", description = "Кофемашина занята приготовлением напитка",
                    content = @Content()),
            @ApiResponse(responseCode = "404",
                    description = "Переданного в метод напитка не существует или рецепт напитка не найден",
                    content = @Content()),
            @ApiResponse(responseCode = "409", description = "Недостаточно ингредиентов для приготовления",
                    content = @Content())
    })
    @PostMapping("/coffeeMachine/make")
    public ResponseEntity<String> makeDrink(String nameCoffee) {
        if (nameCoffee == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (MachineStatus.getStatus() == Status.BUSY)
            return new ResponseEntity<>("Кофемашина занята", HttpStatus.CONFLICT);
        coffeeService.makeDrink(nameCoffee);
        return new ResponseEntity<>("Кофе готово", HttpStatus.OK);
    }
}
