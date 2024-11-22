public class BankInterestLab5 {
    public interface BankInterface {
        void getBalance();
        void getInterestRate();
    }
    class BankA implements BankInterface {
        @Override
        public void getBalance() {
            System.out.println("Bank A: Balance is $1000");
        }
        @Override
        public void getInterestRate() {
            System.out.println("Bank A: Interest rate is 7.0%");
        }
    }
    class BankB implements BankInterface {
        @Override
        public void getBalance() {
            System.out.println("Bank B: Balance is $150000");
        }
        @Override
        public void getInterestRate() {
            System.out.println("Bank A: Interest rate is 7.4%");
        }
    }
    class BankC implements BankInterface {
        @Override
        public void getBalance() {
            System.out.println("Bank C: Balance is $200000");
        }
        @Override
        public void getInterestRate() {
            System.out.println("Bank C: Interest rate is 7.9%");
        }
    }
    public static void main(String[] args) {
        BankInterestLab5 outerClass = new BankInterestLab5();
        BankInterface bankA = outerClass.new BankA();
        BankInterface bankB = outerClass.new BankB();
        BankInterface bankC = outerClass.new BankC();
        bankA.getBalance();
        bankA.getInterestRate();
        bankB.getBalance();
        bankB.getInterestRate();
        bankC.getBalance();
        bankC.getInterestRate();
    }
}