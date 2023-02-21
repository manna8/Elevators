package elevators;

import java.util.Scanner;

public class InternalPanel {
    Scanner scanner = new Scanner(System.in);

    HelperMethods helper = new HelperMethods();

    void internalCall(int elevatorId, int toFloor) {
        for (ElevatorCar elevator : ElevatorSystem.get().elevatorCars) {
            if (elevator.elevatorId == elevatorId) {
                elevator.addStopAtCorrectPlace(toFloor);
            }
        }
    }

    void handleInternalCall() {
        System.out.println("Specify the id of an elevator.");

        if(!scanner.hasNextInt()) {
            System.out.println("Provide an integer!");
            scanner.next();
            return;
        }

        int id = scanner.nextInt();

        if(!helper.checkIfValidId(id)) {
            System.out.printf("Provide a valid id number (from %d and %d)", 0, ElevatorSystem.get().getElevatorsNum() - 1);
            System.out.println();
            return;
        }

        System.out.println("What is your destination floor?");

        if(!scanner.hasNextInt()) {
            System.out.println("Provide an integer!");
            scanner.next();
            return;
        }

        int floor = scanner.nextInt();

        if(!helper.checkIfValidFloor(floor)) {
            System.out.printf("Provide a valid floor number (from %d and %d)",
                    ElevatorStops.get().getMinFloor(),
                    ElevatorStops.get().getMaxFloor());
            System.out.println();
            return;
        }

        internalCall(id, floor);

    }
}
