package elevators;

import java.util.ArrayList;

public class ElevatorSystem {
    public static final int MAX_ELEVATORS_NUM = 16;
    public static final int MIN_ELEVATORS_NUM = 0;
    private int elevatorsNum = 0;

    ArrayList<ElevatorCar> elevatorCars = new ArrayList<>();
    ArrayList<Triple> elevatorsStatus = new ArrayList<>();

    private static ElevatorSystem instance = null;

    private ElevatorSystem() {}

    static ElevatorSystem get(){
        if (instance == null) {
            instance = new ElevatorSystem();
        }

        return instance;
    }

    void setElevatorsNum(int number) {
        this.elevatorsNum = number;
    }

    int getElevatorsNum() {
        return this.elevatorsNum;
    }

    int getMinElevatorsNum() {
        return MIN_ELEVATORS_NUM;
    }

    int getMaxElevatorsNum() {
        return MAX_ELEVATORS_NUM;
    }

    // helper method for printing status
    void printStatus(ArrayList<Triple> status) {
        status.forEach(Triple::printTriple);
        System.out.println();
    }

    // returns the status of elevators
    ArrayList<Triple> status() {
        elevatorsStatus.clear();

        for (ElevatorCar elevator : elevatorCars) {
            Triple singleStatus = new Triple(elevator.elevatorId, elevator.currentFloor, elevator.destinationFloor);
            elevatorsStatus.add(singleStatus);
        }

        return elevatorsStatus;
    }

    public static void main(String[] args) {
        SystemController.get().initialiseSystem();
    }

}