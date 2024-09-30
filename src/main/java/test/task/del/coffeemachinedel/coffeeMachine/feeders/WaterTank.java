package test.task.del.coffeemachinedel.coffeeMachine.feeders;

import lombok.Getter;

@Getter
public class WaterTank {

    private static final short minLevel = 5;

    private static short currentLevel = 5000;

    public static boolean isEnoughWater(short requiredAmount) {
        return currentLevel - requiredAmount >= minLevel;
    }

    public static void makeHotWater(short volume) {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        updateCurrentVolume(volume);
    }

    private static void updateCurrentVolume(short volumeUsed) {
        currentLevel -= volumeUsed;
    }
}
