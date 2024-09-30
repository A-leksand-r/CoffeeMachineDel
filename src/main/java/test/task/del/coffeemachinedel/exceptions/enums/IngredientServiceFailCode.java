package test.task.del.coffeemachinedel.exceptions.enums;

public enum IngredientServiceFailCode implements Error {
    VALIDATE_FAILED(400, "Ошибка валидации данных"),
    NOT_FOUND(404, "Не найдено");

    private final int httpCode;
    private final String message;

    IngredientServiceFailCode(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
