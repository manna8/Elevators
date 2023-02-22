# Elevators Control System

## Project Overview
This system is able to serve up to 16 elevators, which are controlled by one communal, external panel. It is possible to define the lowest and the highest floor in the building, in which elevators will operate.

## System Architecture
### 1. ElevatorCar.java:
It is a class with all necessary fields concerning elevator car. Each elevator has 2 queues: floorsQueueUp (for storing floors which elevator will visit while moving up) and floorsQueueDown (for storing floors which elevator will visit while moving down). Queues are always sorted, so that the nearest stop is always at the beggining. After calling update(), the elevator's current and destination floor are updated.

### 2. ElevatorStops.java:
It is a singleton class storing fields connected to floors, such as minimum and maximum floor number. There are also methods for calculating distance between stops.

### 3. ExternalPanel.java
Represents the external panel for controlling all elevators. The pickup() method decides which elevator should serve each floor. All external calls are stored in ArrayList.

### 4. InternalPanel.java
Represents the internal panel (the one which is in the elevator). 

### 5. SystemController.java
Here are methods for reading and executing user commands, making a step of a symulation, resetting to the default state, as well as initialising the elevators.

### 6. ElevatorSystem.java
It is a singleton class storing fields which are accessed by the rest of the classes, such as an ArrayList of all elevators. Here is the main() method, in which the system is initialised (by calling InitialiseSystem() method from SystemController.java).

## System Logic
Instead of implementing FCFS (first-come, first-serve) method, the elevators serve both internal, as well as the external calls in the following manner:

### External calls
At first, each call is added to externalCalls ArrayList. After calling a step() method, the method pickup() is called for every call in that ArrayList. The main idea of this algorithm is calculating and storing the minimum distance between stops of the elevator and the destination floor -> the stop will be assigned to the elevator, which has the lowest distance. There are three possibilities:

1. If one of the elevators already goes to given floor, the call is ignored
2. If elevator is going up and has to visit more than one floor, it will not serve the call for going down.
3. If elevator is going down and has to visit more than one floor, it will not serve the call for going up.
4. If elevator is on the move, but finishes its ride, the distance between elevator's current floor and the floor on which the call was made is calculated.
5. If elevator is on the, has to visit more than one floor, 'call' direction is the same as elevator's direction and the 'call' floor is between its stops, the distance between current and 'call' floor is calculated.

In the end, the elevator with the lowest value of distance is chosen, and the 'call' floor is assigned to correct queue (with respect to direction).

### Internal calls
When it comes to internal calls, they have priority over external calls. The floor number from internal call is added directly to correct queue of the elevator, in which the call happened.

## Requirements
For running a program, it is neccessary to have JAVA 16.

## How to use a program
It is a console program which can be controlled by a user using commands.
At the very begging it is necessary to specify the lowest and the highest floor number, as well as the number of elevators. Later on, the program can be controlled by using built-in commands (also in some cases using the shorter version of each command).


## Running the program
- Run the main() method from ElevatorSystem.java file
