import java.util.Scanner;

public class CreditCardValidator {
    private long ccNumber;
    public CreditCardValidator(long ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void validateCard() {
        String ccStr = Long.toString(ccNumber);
        if (ccStr.length() < 8 || ccStr.length() > 9) {
            System.out.println("Invalid credit card number");
            return;
        }

        switch (ccStr.length()) {
            case 8:
            case 9:
                validateSteps(ccStr);
                break;
            default:
                System.out.println("Invalid credit card number");
                break;
        }
    }

    private void validateSteps(String ccStr) {
        //a
        int lastDigit = Character.getNumericValue(ccStr.charAt(ccStr.length() - 1));
        String remainingStr = ccStr.substring(0, ccStr.length() - 1);

        // b
        String reversedStr = new StringBuilder(remainingStr).reverse().toString();

        // c
        int[] doubledDigits = new int[reversedStr.length()];
        for (int i = 0; i < reversedStr.length(); i++) {
            int digit = Character.getNumericValue(reversedStr.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            doubledDigits[i] = digit;
        }

        // d
        int sum = 0;
        for (int digit : doubledDigits) {
            sum += digit;
        }

        // e
        int result = 10 - (lastDigit % 10);

        // f
        if (result == lastDigit) {
            System.out.println("Valid credit card number");
        } else {
            System.out.println("Invalid credit card number");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your credit card number: ");
        long ccNumber = scanner.nextLong();
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        validator.validateCard();
        scanner.close();
    }
}
