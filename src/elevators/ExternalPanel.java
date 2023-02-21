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

            // do nothing if whichever elevator already visits given floor
            if (car.floorsQueue.contains(floor) || car.destinationFloor == floor) {
                return;
            }

            // if elevator goes in the same direction
//            if (car.direction == direction) {
//                int sameDir = ElevatorStops.get().calculateStopsForDirection(car, floor, direction);
//
//                if (sameDir < stopsNum) {
//                    stopsNum = sameDir;
//                }
//            } else if (floor - car.destinationFloor > 0 && car.direction == 1){
//                int sameDir = Math.abs(car.destinationFloor - floor);
//
//                if (sameDir < stopsNum) {
//                    stopsNum = sameDir;
//                }
//            }

            // if elevator has nothing to do
            if (car.direction == 0 || car.floorsQueue.isEmpty()) {
                int freeEl = Math.abs(car.currentFloor - floor);

                if (freeEl < stopsNum) {
                    stopsNum = freeEl;
                }
            }
            int sameDir = ElevatorStops.get().calculateStopsForDirection(car, floor, direction);

            if (sameDir < stopsNum) {
                stopsNum = sameDir;
            }

//            // if elevator goes in the same direction
//            if (car.direction == direction) {
//                stopsNum = ElevatorStops.get().calculateStopsForDirection(car, floor, direction);
//            } else if(!car.floorsQueue.isEmpty()){
//                stopsNum = Math.abs(car.floorsQueue.get(0) - floor);
//            }
//
//            // if elevator has nothing to do
//            else if (car.direction == 0) {
//                stopsNum = Math.abs(car.currentFloor - floor);
//            }
//            if (car.currentFloor == car.destinationFloor && car.floorsQueue.isEmpty()) {
//                stopsNum = Math.abs(car.currentFloor - floor);
//            } else {
//                stopsNum = ElevatorStops.get().calculateStopsForDirection(car, floor, direction);
//            }

            // choose the elevator with the closest distance to given floor
            if (stopsNum < minStops) {
                minStops = stopsNum;
                chosenCar = car;
            }
        }

        if (chosenCar != null) {
            chosenCar.addStopAtCorrectPlace(floor);
        }

    }
}
