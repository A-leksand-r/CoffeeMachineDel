package test.task.del.coffeemachinedel.coffeeMachine.feeders;

import lombok.Getter;

@Getter
public class MilkTank {

    private static final short minLevel = 5;

    private static short currentLevel = 2500;

    public static boolean isEnoughMilk(short requiredAmount) {
        return currentLevel - requiredAmount >= minLevel;
    }

    public static void makeMilk(short volume) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        updateCurrentLevel(volume);
    }

    public static void makeWhippedMilk(short volume) {
        frothingMilk(volume);
        updateCurrentLevel(volume);
    }

    private static void frothingMilk(short volume) {
//        try {
//            Thread.sleep(40000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void updateCurrentLevel(short volume) {
        currentLevel -= volume;
    }


}
