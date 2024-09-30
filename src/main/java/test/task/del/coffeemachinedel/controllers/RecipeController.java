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
import org.springframework.web.bind.annotation.RequestBody;
import test.task.del.coffeemachinedel.dtos.IngredientDrinkDto;
import test.task.del.coffeemachinedel.services.RecipeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Контроллер для сохранения", description = "Позволяет сохранить новый рецепт")
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(summary = "Добавить новый напиток", description = "Сохраняет новый напиток с рецептом")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новый напиток успешно добавлен",
                    content = @Content(mediaType = "text/plain;charset=UTF-8")),
            @ApiResponse(responseCode = "400", description = "Необходимо передать название напитка и ингриденты",
                    content = @Content()),
            @ApiResponse(responseCode = "409", description = "Такой напиток уже существует", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных", content = @Content())
    })
    @PostMapping("/coffeeMachine/newRecipe")
    public ResponseEntity<String> addRecipe(@RequestBody List<IngredientDrinkDto> ingredientsDrink, String drinkName) {
        if (ingredientsDrink.isEmpty() || drinkName == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        recipeService.saveRecipe(drinkName, ingredientsDrink);
        return new ResponseEntity<>("Рецепт сохранен", HttpStatus.OK);
    }
}
