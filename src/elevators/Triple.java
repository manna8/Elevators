package elevators;

public class Triple {
    int fistVal;
    int secondVal;
    int thirdVal;

    Triple(int fistVal, int secondVal, int thirdVal) {
        this.fistVal = fistVal;
        this.secondVal = secondVal;
        this.thirdVal = thirdVal;
    }

    void printTriple() {
        System.out.println(this.fistVal + " " + this.secondVal + " " + this.thirdVal);
    }
}