package test.task.del.coffeemachinedel.coffeeMachine.feeders;

import lombok.Getter;

public class CoffeeHopper {

    @Getter
    private static final short minLevel = 5;

    @Getter
    private static short currentLevel = 500;

    public static boolean isEnoughCoffee(short requiredAmount) {
        return currentLevel - requiredAmount >= minLevel;
    }

    public static void makeCoffee(short volume) {
        short quantityCoffee = (short) (volume / 30 * 7);
        grinding(quantityCoffee);
        pressing();
        extraction();
        updateCurrentLevel(quantityCoffee);
    }

    private static void updateCurrentLevel(short quantity) {
        currentLevel -= quantity;
    }

    private static void grinding(short quantity) {
        //Измельчение кофейных зерен
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void pressing() {
        //Прессовка помола
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void extraction() {
        //Экстракция
//        try {
//            Thread.sleep(25000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
