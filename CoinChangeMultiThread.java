public class CoinChangeMultiThread {
    static int includeResult = 0; // To store result from the include thread
    static int excludeResult = 0; // To store result from the exclude thread

    // Recursive function to count combinations
    static int countCombinations(int[] coins, int n, int sum) {
        // If sum is 0, there is one solution (no coins needed)
        if (sum == 0) return 1;

        // If sum is negative or no coins left, no solution
        if (sum < 0 || n <= 0) return 0;

        // Count combinations including the last coin and excluding the last coin
        return countCombinations(coins, n, sum - coins[n - 1]) + 
               countCombinations(coins, n - 1, sum);
    }

    // Runnable for including the last coin
    static class IncludeLastRunnable implements Runnable {
        private int[] coins;
        private int n;
        private int sum;

        public IncludeLastRunnable(int[] coins, int n, int sum) {
            this.coins = coins;
            this.n = n;
            this.sum = sum;
        }

        @Override
        public void run() {
            includeResult = countCombinations(coins, n, sum - coins[n - 1]);
        }
    }

    // Runnable for excluding the last coin
    static class ExcludeLastRunnable implements Runnable {
        private int[] coins;
        private int n;
        private int sum;

        public ExcludeLastRunnable(int[] coins, int n, int sum) {
            this.coins = coins;
            this.n = n;
            this.sum = sum;
        }

        @Override
        public void run() {
            excludeResult = countCombinations(coins, n - 1, sum);
        }
    }

    // Function to count combinations using threads
    static int count(int[] coins, int sum) throws InterruptedException {
        int n = coins.length;

        // Create threads for including and excluding the last coin
        Thread includeLast = new Thread(new IncludeLastRunnable(coins, n, sum));
        Thread excludeLast = new Thread(new ExcludeLastRunnable(coins, n, sum));

        // Start the threads
        includeLast.start();
        excludeLast.start();

        // Wait for both threads to complete
        includeLast.join();
        excludeLast.join();

        // Combine results from both threads
        return includeResult + excludeResult;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] coins = {1,2,5};
        int sum = 2;

        // Start counting combinations
        int totalCombinations = count(coins, sum);
        System.out.println("Number of combinations: " + totalCombinations);
    }
}