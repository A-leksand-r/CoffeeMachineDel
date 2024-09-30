package test.task.del.coffeemachinedel.coffeeMachine;

import lombok.Getter;
import lombok.Setter;
import test.task.del.coffeemachinedel.enums.Status;

public class MachineStatus {

    @Getter
    @Setter
    private static Status status = Status.READY;
}
