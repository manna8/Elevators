package elevators;

import java.util.Scanner;

public class InternalPanel {
    Scanner scanner = new Scanner(System.in);

    private void internalCall(int elevatorId, int toFloor) {
        ElevatorCar car = ElevatorSystem.get().elevatorCars.get(elevatorId);
        car.decideWhichWay(car.direction, toFloor);
    }

    public void handleInternalCall() {
        System.out.println("Specify the id of an elevator.");

        if(!scanner.hasNextInt()) {
            System.out.println("Provide an integer!");
            scanner.next();
            return;
        }

        int id = scanner.nextInt();

        if(!HelperMethods.checkIfValidId(id)) {
            System.out.printf("Provide a valid id number (from %d and %d).%n", 0, ElevatorSystem.get().getElevatorsNum() - 1);
            return;
        }

        System.out.println("What is your destination floor?");

        if(!scanner.hasNextInt()) {
            System.out.println("Provide an integer!");
            scanner.next();
            return;
        }

        int floor = scanner.nextInt();

        if(!HelperMethods.checkIfValidFloor(floor)) {
            System.out.printf("Provide a valid floor number (from %d and %d)",
                    ElevatorStops.get().getMinFloor(),
                    ElevatorStops.get().getMaxFloor());
            System.out.println();
            return;
        }

        internalCall(id, floor);

    }
}
