package elevators;

import java.util.ArrayList;
import java.util.Collections;

public class ElevatorCar {
    protected int elevatorId;
    protected int currentFloor;
    protected int destinationFloor;
    protected int direction; // 1 -> up, -1 -> down, 0 -> does nothing

    protected ArrayList<Integer> floorsQueue = new ArrayList<>();


    public ElevatorCar(int id) {
        this.elevatorId = id;
        this.currentFloor = 0;
        this.destinationFloor = 0;
        this.direction = 0;
    }

    // adds a new stop so that the next stop is always first in the queue
    public void addStopAtCorrectPlace(int floor) {

        if (floorsQueue.contains(floor)) {
            return;
        }

        this.floorsQueue.add(floor);
        this.floorsQueue.sort(this.direction == -1 ? Collections.reverseOrder() : null);
    }

    // updates the elevator's current and destination floor
    public void update() {
        this.currentFloor = this.destinationFloor;

        if (!this.floorsQueue.isEmpty()) {
            this.destinationFloor = this.floorsQueue.remove(0);
            this.direction = this.destinationFloor - this.currentFloor >= 0 ? 1 : -1;

        } else if (this.currentFloor == 0){
            this.direction = 1;

        } else if (this.currentFloor == ElevatorStops.get().getMaxFloor()) {
            this.direction = -1;
        } else if (this.currentFloor == this.destinationFloor) {
            this.direction = 0;
        }
    }
}
