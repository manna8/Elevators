package elevators;

import java.util.ArrayList;
import java.util.Scanner;

public class ExternalPanel {
    private final Scanner scanner = new Scanner(System.in);
    ArrayList<Tuple> externalCalls = new ArrayList<>();

    private void externalCall(int atFloor, int direction) {
        Tuple call = new Tuple(atFloor, direction);
        this.externalCalls.add(call);
    }

    public void handleExternalCall() {
        System.out.println("On which floor are you?");

         if(!scanner.hasNextInt()) {
             System.out.println("Provide an integer!");
             scanner.next();
             return;
         }

        int floor = scanner.nextInt();

        if (!HelperMethods.checkIfValidFloor(floor)) {
            System.out.printf("Provide a valid floor number (from %d to %d).%n", ElevatorStops.get().getMinFloor(), ElevatorStops.get().getMaxFloor());
            return;
        }

        int direction = HelperMethods.returnValidDirection(floor);

        if (direction == HelperMethods.WRONG_DIRECTION) {
            System.out.println("Provide a valid direction (letter u or d).");
            return;
        }

        externalCall(floor, direction);
    }

    // decides which elevator should take this call and assigns given floor to an elevator
    public void pickup(int floor, int direction) {
        ElevatorCar chosenCar = null;
        int minStops = Integer.MAX_VALUE;

        for (ElevatorCar car : ElevatorSystem.get().elevatorCars) {
            int stopsNum = Integer.MAX_VALUE;

            if (car.floorsQueueUp.contains(floor) || car.destinationFloor == floor || car.floorsQueueDown.contains(floor)) {
                return;
            }

            if (direction == ElevatorCar.DOWN) {
                if (!car.floorsQueueUp.isEmpty()) {
                    continue;
                }

                stopsNum = ElevatorStops.get().calculateDistanceForDown(floor, car, stopsNum);

            } else if (direction == ElevatorCar.UP) {
                if (!car.floorsQueueDown.isEmpty()) {
                    continue;
                }

                stopsNum = ElevatorStops.get().calculateDistanceForUp(floor, car, stopsNum);
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
