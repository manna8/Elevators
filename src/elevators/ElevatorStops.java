package elevators;

public class ElevatorStops {
    private int FLOORS;
    private int MIN_FLOOR;
    private int MAX_FLOOR;

    private static ElevatorStops instance = null;

    private ElevatorStops() {}

    static ElevatorStops get(){
        if (instance == null) {
            instance = new ElevatorStops();
        }

        return instance;
    }

    void setMinFloor(int number) {
        this.MIN_FLOOR = number;
    }

    void setMaxFloor(int number) {
        this.MAX_FLOOR = number;
    }

    void setFloorsNum() {
        this.FLOORS = this.MIN_FLOOR + this.MAX_FLOOR + 1;
    }

    int getMinFloor() {
        return MIN_FLOOR;
    }

    int getMaxFloor() {
        return MAX_FLOOR;
    }

    // checks is the floor is higher than the highest elevator's stop and if distance is the smallest
    boolean smallestAfterLastStopUp(int floor, int destFloor, int minStops) {
        return (floor > destFloor && floor - destFloor < minStops);
    }

    // checks if the floor is between two stops of an elevator while it's going up and if that distance is the smallest
    boolean smallestBetweenStopsUp(int floor, int currFloor, int destFloor, int minStops) {
        return (floor > currFloor && floor < destFloor && floor - currFloor < minStops);
    }

    // checks is the floor is lower than the lowest elevator's stop and if distance is the smallest
    boolean smallestAfterLastStopDown(int floor, int destFloor, int minStops) {
        return (floor < destFloor && destFloor - floor < minStops);
    }

    // checks if the floor is between two stops of an elevator while it's going up and if that distance is the smallest
    boolean smallestBetweenStopsDown(int floor, int currFloor, int destFloor, int minStops) {
        return (floor < currFloor && floor > destFloor && currFloor - floor < minStops);
    }

    int calculateStopsForDirection(ElevatorCar car, int floor, int minStops) {
        if (car.direction == 1) {
            if (smallestAfterLastStopUp(floor, car.destinationFloor, minStops)) {
                minStops = floor - car.destinationFloor;

            } else if (smallestBetweenStopsUp(floor, car.currentFloor, car.destinationFloor, minStops)) {
                minStops = floor - car.currentFloor;
            }

        } else if (car.direction == -1){
            if (smallestBetweenStopsDown(floor, car.currentFloor, car.destinationFloor, minStops)) {
                minStops = car.currentFloor - floor;

            } else if (smallestAfterLastStopDown(floor, car.destinationFloor, minStops)) {
                minStops = car.destinationFloor - floor;
            }
        }

        return minStops;
    }

    int calculateStopsForContraryDirection(ElevatorCar car, int floor, int minStops) {
        switch (car.direction) {
            case 1:
                if (car.floorsQueue.get(car.floorsQueue.size() - 1) < floor) {
                    minStops = floor - car.floorsQueue.get(car.floorsQueue.size() - 1);
                }
            case -1:
                if (car.floorsQueue.get(car.floorsQueue.size() - 1) > floor) {
                    minStops = car.floorsQueue.get(car.floorsQueue.size() - 1) - floor;
                }
        }

        return minStops;
    }


}
