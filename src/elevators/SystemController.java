package elevators;

import java.util.Scanner;

public class SystemController {
    private boolean exit = false;
    Scanner scanner = new Scanner(System.in);

    HelperMethods helper = new HelperMethods();
    InternalPanel internalPanel = new InternalPanel();
    ExternalPanel externalPanel = new ExternalPanel();

    // only one instance
    private static SystemController instance = null;

    private SystemController() {}

    static SystemController get(){
        if (instance == null) {
            instance = new SystemController();
        }

        return instance;
    }

    private boolean setElevatorNum(int number) {
        if (number > ElevatorSystem.get().getMinElevatorsNum() && number <= ElevatorSystem.get().getMaxElevatorsNum()) {
            ElevatorSystem.get().setElevatorsNum(number);
            return true;
        }

        return false;
    }

    private boolean initialiseElevators() {
        System.out.println("Enter number of elevators (max 16):");

        if(!setElevatorNum(scanner.nextInt())) {
            System.out.println("Provide a valid number of elevators.");
            return false;
        }

        for (int i = 0; i < ElevatorSystem.get().getElevatorsNum(); i++) {
            ElevatorSystem.get().elevatorCars.add(new ElevatorCar(i));
        }

        helper.drawElevators(ElevatorSystem.get().elevatorCars);

        return true;
    }



    // makes a step of a simulation
    void step() {
//        ElevatorSystem.get().elevatorCars.forEach(ElevatorCar::update);
        externalPanel.externalCalls.forEach(call -> externalPanel.pickup(call.fistVal, call.secondVal));
        ElevatorSystem.get().elevatorCars.forEach(ElevatorCar::update);
        externalPanel.externalCalls.clear();
    }

    // resets elevators to default
    void reset() {
        for (ElevatorCar car: ElevatorSystem.get().elevatorCars) {
            car.floorsQueue.clear();
            car.destinationFloor = 0;
        }
        externalPanel.externalCalls.clear();
        step();
    }

    private void readAndExecuteCommands() {
        System.out.println("Write a command:");
        String command = scanner.next();

        switch (command) {
            case "external_call", "e" -> {
                externalPanel.handleExternalCall();
            }
            case "internal_call", "i" -> {
                internalPanel.handleInternalCall();
            }
            case "status", "s" -> {
                System.out.println("Elevators' status:");
                ElevatorSystem.get().printStatus(ElevatorSystem.get().status());
            }
            case "show", "sh" -> helper.drawElevators(ElevatorSystem.get().elevatorCars);
            case "exit" -> exit = true;
            case "help", "h" -> helper.helloMessage();
            case "step" -> {
                System.out.println("Making a step...");
                step();
            }
            case "reset" -> {
                System.out.println("Resetting the elevators to default...");
                reset();
            }
            default -> System.out.println("Command not found. Make sure you've written the correct command.");
        }

    }


    public void InitialiseSystem() {
        while(!helper.setLowestFloor());
        while(!helper.setHighestFloor());
        while(!initialiseElevators());

        helper.helloMessage();

        while(!exit) {
            readAndExecuteCommands();
        }

    }
}