package elevators;

import java.util.ArrayList;
import java.util.Scanner;

public class ExternalPanel {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Tuple> externalCalls = new ArrayList<>();

    HelperMethods helper = new HelperMethods();

    void externalCall(int atFloor, int direction) {
        Tuple call = new Tuple(atFloor, direction);
        this.externalCalls.add(call);
    }

     void handleExternalCall() {
        System.out.println("On which floor are you?");

         if(!scanner.hasNextInt()) {
             System.out.println("Provide an integer!");
             scanner.next();
             return;
         }

        int floor = scanner.nextInt();

        if (!helper.checkIfValidFloor(floor)) {
            System.out.printf("Provide a valid floor number (from %d to %d).", ElevatorStops.get().getMinFloor(), ElevatorStops.get().getMaxFloor());
            System.out.println();
            return;
        }

        int direction = helper.returnValidDirection(floor);

        if (direction == 0) {
            System.out.println("Provide a valid direction (letter u or d).");
            return;
        }

        externalCall(floor, direction);
    }

    // decides which elevator should take this call and assigns given floor to an elevator
    void pickup(int floor, int direction) {
        ElevatorCar chosenCar = null;
        int minStops = Integer.MAX_VALUE;

        for (ElevatorCar car : ElevatorSystem.get().elevatorCars) {
            int stopsNum = Integer.MAX_VALUE;

            if (car.floorsQueueUp.contains(floor) || car.destinationFloor == floor || car.floorsQueueDown.contains(floor)) {
                return;
            }

            if (direction == -1) {
                if (!car.floorsQueueUp.isEmpty()) {
                    continue;
                } else if (car.currentFloor == 0 || car.direction == 0 || car.currentFloor == ElevatorStops.get().getMaxFloor()) {
                    if (Math.abs(floor - car.currentFloor) < minStops) {
                        stopsNum = Math.abs(floor - car.currentFloor);
                    }
                }

                if (car.floorsQueueDown.isEmpty()) {
                    if (Math.abs(floor - car.currentFloor) < stopsNum) {
                        stopsNum = Math.abs(floor - car.currentFloor);
                    }
                } else if (floor > car.floorsQueueDown.get(0)) {
                    if (floor - car.floorsQueueDown.get(0) < stopsNum) {
                        stopsNum = floor - car.floorsQueueDown.get(0);
                    }
                }

            } else if (direction == 1) {
                if (!car.floorsQueueDown.isEmpty()) {
                    continue;
                } else if (car.currentFloor == 0 || car.direction == 0 || car.currentFloor == ElevatorStops.get().getMinFloor()) {
                    if (Math.abs(floor - car.currentFloor) < minStops) {
                        stopsNum = Math.abs(floor - car.currentFloor);
                    }
                }

                if (car.floorsQueueUp.isEmpty()) {
                    if (Math.abs(floor - car.currentFloor) < stopsNum) {
                        stopsNum = Math.abs(floor - car.currentFloor);
                    }
                } else if (floor > car.floorsQueueUp.get(0)) {
                    if (floor - car.floorsQueueUp.get(0) < stopsNum) {
                        stopsNum = floor - car.floorsQueueUp.get(0);
                    }

                }
            }

            // choose the elevator with the closest distance to given floor
            if (stopsNum < minStops) {
                minStops = stopsNum;
                chosenCar = car;
            }
        }

        if (chosenCar != null) {
            chosenCar.decideWhichWay(direction, floor);
        }

    }
}
