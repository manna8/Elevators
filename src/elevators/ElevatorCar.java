package elevators;

import java.util.ArrayList;
import java.util.Collections;

public class ElevatorCar {
    protected int elevatorId;
    protected int currentFloor;
    protected int destinationFloor;
    protected int direction;

    protected static final int UP = 1;
    protected static final int DOWN = -1;
    protected static final int NO_MOVE = 0;


    protected ArrayList<Integer> floorsQueueUp = new ArrayList<>();
    protected ArrayList<Integer> floorsQueueDown = new ArrayList<>();

    public ElevatorCar(int id) {
        this.elevatorId = id;
        this.currentFloor = 0;
        this.destinationFloor = 0;
        this.direction = UP;
    }

    public void decideWhichWay(int direction, int floor) {
        if (direction == UP) {
            addStopUp(floor);
        } else {
            addStopDown(floor);
        }
    }

    private void addStopUp(int floor) {
        this.floorsQueueUp.add(floor);
        this.floorsQueueUp.sort(null);
    }

    private void addStopDown(int floor) {
        this.floorsQueueDown.add(floor);
        this.floorsQueueDown.sort(Collections.reverseOrder());
    }


    // updates the elevator's current and destination floor
    public void update() {
        this.currentFloor = this.destinationFloor;

        if (!this.floorsQueueUp.isEmpty()) {
            this.destinationFloor = this.floorsQueueUp.remove(0);
            this.direction = this.destinationFloor - this.currentFloor >= 0 ? UP : DOWN;

        } else if (!this.floorsQueueDown.isEmpty()) {
            this.destinationFloor = this.floorsQueueDown.remove(0);
            this.direction = this.destinationFloor - this.currentFloor >= 0 ? UP : DOWN;

        } else if (this.currentFloor == 0) {
            this.direction = UP;

        } else if (this.currentFloor == ElevatorStops.get().getMaxFloor()) {
            this.direction = DOWN;

        } else {
            this.direction = NO_MOVE;
        }
    }
}
