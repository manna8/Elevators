package elevators;

public class ElevatorStops {
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

    int getMinFloor() {
        return MIN_FLOOR;
    }

    int getMaxFloor() {
        return MAX_FLOOR;
    }

    // calculates the distance for going down
    int calculateDistanceForDown(int floor, ElevatorCar car, int stopsNum) {
        if (car.floorsQueueDown.isEmpty()) {
            if (Math.abs(floor - car.currentFloor) < stopsNum) {
                stopsNum = Math.abs(floor - car.currentFloor);
            }
        } else if (Math.abs(floor - car.floorsQueueDown.get(0)) < stopsNum) {
                stopsNum = Math.abs(floor - car.floorsQueueDown.get(0));
            }

        return stopsNum;
    }

    // calculates the distance for going up
    int calculateDistanceForUp(int floor, ElevatorCar car, int stopsNum) {
        if (car.floorsQueueUp.isEmpty()) {
            if (Math.abs(floor - car.currentFloor) < stopsNum) {
                stopsNum = Math.abs(floor - car.currentFloor);
            }
        } else if (Math.abs(floor - car.floorsQueueUp.get(0)) < stopsNum) {
                stopsNum = Math.abs(floor - car.floorsQueueUp.get(0));
            }

        return stopsNum;
    }

}
