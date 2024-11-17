abstract class Robber {
    // Abstract method to be implemented by subclasses
    public abstract void RobbingClass();
    
    // Abstract methods for different house types
    public abstract int RowHouses(int[] rowHouseAmounts);
    public abstract int RoundHouses(int[] roundHouseAmounts);
    public abstract int SquareHouse(int[] squareHouseAmounts);
    public abstract int MultiHouseBuilding(int[] multiHouseAmounts);

    // Default method
    public void MachineLearning() {
        System.out.println("I love MachineLearning");
    }
}

class JAVAProfessionalRobber extends Robber {
    // Implementation of the abstract method RobbingClass
    @Override
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }

    // Helper method to calculate the maximum money that can be robbed without triggering alarms
    private int robHelper(int[] amounts, int start, int end) {
        int prevMax = 0, currMax = 0;
        for (int i = start; i <= end; i++) {
            int temp = Math.max(currMax, prevMax + amounts[i]);
            prevMax = currMax;
            currMax = temp;
        }
        return currMax;
    }

    // Implementation of RowHouses
    @Override
    public int RowHouses(int[] rowHouseAmounts) {
        return robHelper(rowHouseAmounts, 0, rowHouseAmounts.length - 1);
    }

    // Implementation of RoundHouses
    @Override
    public int RoundHouses(int[] roundHouseAmounts) {
        if (roundHouseAmounts.length == 1) return roundHouseAmounts[0];
        // Max money can be robbed either excluding the first or the last house
        return Math.max(robHelper(roundHouseAmounts, 0, roundHouseAmounts.length - 2),
                        robHelper(roundHouseAmounts, 1, roundHouseAmounts.length - 1));
    }

    // Implementation of SquareHouse
    @Override
    public int SquareHouse(int[] squareHouseAmounts) {
        return robHelper(squareHouseAmounts, 0, squareHouseAmounts.length - 1);
    }

    // Implementation of MultiHouseBuilding
    @Override
    public int MultiHouseBuilding(int[] multiHouseAmounts) {
        return robHelper(multiHouseAmounts, 0, multiHouseAmounts.length - 1);
    }
}

public class RobberPract4 {
    public static void main(String[] args) {
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();

        // Demonstrate RobbingClass method
        robber.RobbingClass();

        // Demonstrate MachineLearning default method
        robber.MachineLearning();

        // Test cases for different house types
        int[] rowHouseAmounts = {2, 7, 9, 3, 1};
        int[] roundHouseAmounts = {2, 3, 2};
        int[] squareHouseAmounts = {5, 5, 10, 40, 50, 35};
        int[] multiHouseAmounts = {10, 20, 30, 40};

        System.out.println("Max money robbed from Row Houses: " + robber.RowHouses(rowHouseAmounts));
        System.out.println("Max money robbed from Round Houses: " + robber.RoundHouses(roundHouseAmounts));
        System.out.println("Max money robbed from Square Houses: " + robber.SquareHouse(squareHouseAmounts));
        System.out.println("Max money robbed from Multi-Type Building: " + robber.MultiHouseBuilding(multiHouseAmounts));
    }
}
