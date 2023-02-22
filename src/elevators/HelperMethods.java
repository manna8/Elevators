package elevators;

import java.util.ArrayList;
import java.util.Scanner;

public class HelperMethods {
    public static final Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final int WRONG_DIRECTION = -5;

    public static boolean checkIfValidFloor(int floor) {
        return (floor >= ElevatorStops.get().getMinFloor() && floor <= ElevatorStops.get().getMaxFloor());
    }

    public static boolean checkIfValidId(int id) {
        return (id >= 0 && id < ElevatorSystem.get().getElevatorsNum());
    }

    public static int returnValidDirection(int floor) {
        if (floor == ElevatorStops.get().getMaxFloor()) {
            return ElevatorCar.DOWN;
        } else if (floor == ElevatorStops.get().getMinFloor()) {
            return ElevatorCar.UP;
        }

        System.out.println("Are you going up [u] or down [d]?");
        String directionString = scanner.next();

        if (directionString.equals("u")) {
            return ElevatorCar.UP;
        }

        return directionString.equals("d") ? ElevatorCar.DOWN : WRONG_DIRECTION;
    }

    public static boolean isNextInt() {
        if (scanner.hasNextInt()) {
            return true;
        }

        System.out.println("Provide an integer!");
        scanner.next();
        return false;

    }

    public static void drawElevators(ArrayList<ElevatorCar> elevatorCars) {
        String top = "";
        String box = "";
        String ids = "";

        for (ElevatorCar car : elevatorCars) {
            top += "---  ";
            box += ("|" + car.currentFloor + "|  ");
            ids += ("id:" + car.elevatorId + " ");
        }

        System.out.println(top);
        System.out.println(box);
        System.out.println(top);
        System.out.println(ids);
        System.out.println();
    }

    public static void helloMessage() {
        System.out.printf("There are floors from %d to %d", ElevatorStops.get().getMinFloor(), ElevatorStops.get().getMaxFloor());
        System.out.println(ANSI_YELLOW);

        System.out.println("""
                For external call write: external_call [e]\s
                For internal call write: internal_call [i]\s
                For getting a status write: status [s]\s
                To show elevators write: show [sh]\s
                To show help write: help [h]\s
                To reset elevators to default write: reset\s
                To make a step write: step\s
                To exit write: exit""");

        System.out.println(ANSI_RESET);
    }

     public static boolean setLowestFloor() {
        System.out.println("Enter number of the lowest floor (from -10 to 0)");

         if (!isNextInt()) {
             return false;
         }

        int minFloor = scanner.nextInt();

        if (minFloor < ElevatorStops.MIN_FLOOR_LIMIT_BOTTOM || minFloor > ElevatorStops.MAX_FLOOR_LIMIT_BOTTOM) {
            System.out.println("The floor must be within -10 and 0!");
            return false;
        }

        ElevatorStops.get().setMinFloor(minFloor);

        return true;
    }

    public static boolean setHighestFloor() {
        System.out.println("Enter number of the highest floor (from 1 to 100)");

        if (!isNextInt()) {
            return false;
        }

        int maxFloor = scanner.nextInt();

        if (maxFloor < ElevatorStops.MIN_FLOOR_LIMIT_UP || maxFloor > ElevatorStops.MAX_FLOOR_LIMIT_TOP) {
            System.out.println("The floor must be within 1 and 100!");
            return false;
        }

        ElevatorStops.get().setMaxFloor(maxFloor);

        return true;
    }
}
