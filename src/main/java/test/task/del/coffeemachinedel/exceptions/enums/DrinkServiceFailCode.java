package test.task.del.coffeemachinedel.exceptions.enums;

public enum DrinkServiceFailCode implements Error{
    ALREADY_EXIST(409, "Напиток уже существует"),
    NOT_FOUND(404, "Напитка не существует"),
    INSUFFICIENT_QUANTITY(409, "Недостаточное количество");

    private final int httpCode;
    private final String message;

    DrinkServiceFailCode(int httpCode, String message) {
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
