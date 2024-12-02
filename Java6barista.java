class CounterEmptyException extends Exception {
    public CounterEmptyException(String message) {
        super(message);
    }
}

class CoffeeShop {
    private final String[] counter = new String[3]; // Fixed size array for the counter
    private int count = 0; // Current number of coffees in the counter

    public synchronized void prepareCoffee(String baristaName) throws InterruptedException {
        while (count == counter.length) {
            System.out.println(baristaName + " is waiting, counter is full.");
            wait();
        }
        counter[count] = "Coffee from " + baristaName;
        count++;
        System.out.println(baristaName + " prepared a coffee. (Counter: " + count + " coffees)");
        notifyAll();
    }

    public synchronized String pickUpCoffee(String customerName) throws InterruptedException, CounterEmptyException {
        while (count == 0) {
            System.out.println(customerName + " is waiting, counter is empty.");
            wait();
        }
        String coffee = counter[count - 1]; // Take the last coffee
        counter[count - 1] = null; // Clear the reference
        count--;
        System.out.println(customerName + " picked up a coffee. (Counter: " + count + " coffees)");
        notifyAll();
        return coffee;
    }

    public synchronized String sampleCoffee(String reviewerName) throws InterruptedException, CounterEmptyException {
        while (count == 0) {
            System.out.println(reviewerName + " is waiting, counter is empty.");
            wait();
        }
        String coffee = counter[count - 1]; // Take the last coffee
        counter[count - 1] = null; // Clear the reference
        count--;
        System.out.println(reviewerName + " sampled a coffee. (Counter: " + count + " coffees)");
        notifyAll();
        return coffee;
    }
}

class Barista extends Thread {
    private final CoffeeShop coffeeShop;
    private final String name;
    private final int coffeesToPrepare;

    public Barista(CoffeeShop coffeeShop, String name, int coffeesToPrepare) {
        this.coffeeShop = coffeeShop;
        this.name = name;
        this.coffeesToPrepare = coffeesToPrepare;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPrepare; i++) {
                coffeeShop.prepareCoffee(name);
                Thread.sleep(1000); // Simulate time taken to prepare coffee
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer extends Thread {
    private final CoffeeShop coffeeShop;
    private final String name;
    private final int coffeesToPick;

    public Customer(CoffeeShop coffeeShop, String name, int coffeesToPick) {
        this.coffeeShop = coffeeShop;
        this.name = name;
        this.coffeesToPick = coffeesToPick;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeesToPick; i++) {
                coffeeShop.pickUpCoffee(name);
                Thread.sleep(500); // Simulate time taken to pick up coffee
            }
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

class Reviewer extends Thread {
    private final CoffeeShop coffeeShop;
    private final String name;

    public Reviewer(CoffeeShop coffeeShop, String name) {
        this.coffeeShop = coffeeShop;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            coffeeShop.sampleCoffee(name);
        } catch (InterruptedException | CounterEmptyException e) {
            e.printStackTrace();
        }
    }
}

public class Java6barista {
    public static void main(String[] args) {
        CoffeeShop coffeeShop = new CoffeeShop();

        Barista barista1 = new Barista(coffeeShop, "Barista 1", 2);
        Barista barista2 = new Barista(coffeeShop, "Barista 2", 4);
        Customer customer1 = new Customer(coffeeShop, "Customer 1", 1);
        Customer customer2 = new Customer(coffeeShop, "Customer 2", 2);
        Customer customer3 = new Customer(coffeeShop, "Customer 3", 1);
        Reviewer reviewer = new Reviewer(coffeeShop, "Reviewer");

        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();
    }
}