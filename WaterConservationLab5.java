public class WaterConservationLab5 {
    public interface WaterConservationSystem {
        int calculateTrappedWater(int[] blockHeights);
    }

    static abstract class RainySeasonConservation implements WaterConservationSystem {
        // No changes needed here
    }

    static class CityBlockConservation extends RainySeasonConservation {
        @Override
        public int calculateTrappedWater(int[] blockHeights) {
            if (blockHeights == null || blockHeights.length < 3) {
                System.out.println("Insufficient blocks to trap water.");
                return 0;
            }

            int n = blockHeights.length;
            int[] leftMax = new int[n];
            int[] rightMax = new int[n];

            // leftMax
            leftMax[0] = blockHeights[0];
            for (int i = 1; i < n; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
            }

            // rightMax
            rightMax[n - 1] = blockHeights[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
            }

            int trappedWater = 0;
            for (int i = 0; i < n; i++) {
                trappedWater += Math.min(leftMax[i], rightMax[i]) - blockHeights[i];
            }

            System.out.println("Trapped water: " + trappedWater + " units");
            return trappedWater;
        }
    }

    public static void main(String[] args) {
        int[] blockHeights = {5, 0, 2, 0, 6};

        CityBlockConservation waterSystem = new CityBlockConservation();

        int trappedWater = waterSystem.calculateTrappedWater(blockHeights);
        System.out.println("Total volume of trapped water: " + trappedWater + " units");
    }
}
