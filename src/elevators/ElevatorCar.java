package elevators;

import java.util.ArrayList;
import java.util.Collections;

public class ElevatorCar {
    protected int elevatorId;
    protected int currentFloor;
    protected int destinationFloor;
    protected int direction; // 1 -> up, -1 -> down, 0 -> does nothing

    protected ArrayList<Integer> floorsQueueUp = new ArrayList<>();
    protected ArrayList<Integer> floorsQueueDown = new ArrayList<>();

    public ElevatorCar(int id) {
        this.elevatorId = id;
        this.currentFloor = 0;
        this.destinationFloor = 0;
        this.direction = 1;
    }

    public void decideWhichWay(int direction, int floor) {
        if (direction == 1) {
            addStopUp(floor);
        } else {
            addStopDown(floor);
        }
    }

    public void addStopUp(int floor) {
        this.floorsQueueUp.add(floor);
        this.floorsQueueUp.sort(null);
    }

    public void addStopDown(int floor) {
        this.floorsQueueDown.add(floor);
        this.floorsQueueDown.sort(Collections.reverseOrder());
    }


    // updates the elevator's current and destination floor
    public void update() {
        this.currentFloor = this.destinationFloor;

        if (!this.floorsQueueUp.isEmpty()) {
            this.destinationFloor = this.floorsQueueUp.remove(0);
            this.direction = this.destinationFloor - this.currentFloor >= 0 ? 1 : -1;

        } else if (!this.floorsQueueDown.isEmpty()) {
            this.destinationFloor = this.floorsQueueDown.remove(0);
            this.direction = this.destinationFloor - this.currentFloor >= 0 ? 1 : -1;
        }

        else if (this.currentFloor == 0) {
            this.direction = 1;

        } else if (this.currentFloor == ElevatorStops.get().getMaxFloor()) {
            this.direction = -1;

        } else if (this.currentFloor == this.destinationFloor) {
            this.direction = 0;
        }

    }
}
