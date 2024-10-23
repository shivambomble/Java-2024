import java.util.Arrays;

public class FrequencyAnalysis {

    static int[] numbersArray;
    
    public static void findTopKNumbers(int K) {
        
        int maxNumber = findMax(numbersArray); 
        int[] frequency = new int[maxNumber + 1]; 
        
        for (int number : numbersArray) {
            frequency[number]++;
        }

        
        int[][] freqWithNumbers = new int[maxNumber + 1][2];
        for (int i = 0; i <= maxNumber; i++) {
            freqWithNumbers[i][0] = i;           
            freqWithNumbers[i][1] = frequency[i]; 
        }

        
        Arrays.sort(freqWithNumbers, (a, b) -> {
            if (b[1] != a[1]) {
                return b[1] - a[1]; 
            } else {
                return b[0] - a[0]; 
            }
        });

        
        System.out.println("Top " + K + " numbers with highest frequency:");
        for (int i = 0; i < K; i++) {
            if (freqWithNumbers[i][1] > 0) { 
                System.out.print(freqWithNumbers[i][0] + " ");
            }
        }
        System.out.println();
    }

    
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        
        numbersArray = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        int K = 2;
        findTopKNumbers(K); 

        numbersArray = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        K = 4;
        findTopKNumbers(K); 
    }
}