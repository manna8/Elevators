package elevators;

import java.util.ArrayList;
import java.util.Scanner;

public class ElevatorSystem {
    private final int maxElevatorsNum = 16;
    private final int minElevatorsNum = 0;
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
        return this.minElevatorsNum;
    }

    int getMaxElevatorsNum() {
        return this.maxElevatorsNum;
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
        SystemController.get().InitialiseSystem();
    }

}