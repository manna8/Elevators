package elevators;

public class ElevatorStops {
    private int minFloor;
    private int maxFloor;

    public static final int MIN_FLOOR_LIMIT_BOTTOM = -10;
    public static final int MAX_FLOOR_LIMIT_BOTTOM = 0;
    public static final int MIN_FLOOR_LIMIT_UP = 1;
    public static final int MAX_FLOOR_LIMIT_TOP = 100;

    private static ElevatorStops instance = null;

    private ElevatorStops() {}

    static ElevatorStops get(){
        if (instance == null) {
            instance = new ElevatorStops();
        }

        return instance;
    }

    public void setMinFloor(int number) {
        this.minFloor = number;
    }

    public void setMaxFloor(int number) {
        this.maxFloor = number;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    // calculates the distance for going down
    public int calculateDistanceForDown(int floor, ElevatorCar car, int stopsNum) {
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
    public int calculateDistanceForUp(int floor, ElevatorCar car, int stopsNum) {
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
