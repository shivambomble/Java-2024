import java.time.LocalDate;
import java.util.*;

// Customer Class
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "'}";
    }
}

// Product Class
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + '}';
    }

    // Equals and HashCode for uniqueness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// Product Price Comparator
class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

// Order Class with Delivery Date
class Order {
    private String id;
    private Customer customer;
    private List<Product> products;
    private LocalDate deliveryDate;

    public Order(String id, Customer customer, LocalDate deliveryDate) {
        this.id = id;
        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public LocalDate getDeliveryDate() { 
        return deliveryDate; 
    }

    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        return "Order{id='" + id + "', customer=" + customer.getName() + 
               ", deliveryDate=" + deliveryDate + ", products=" + products.size() + "}";
    }
}

// Order Delivery Date Comparator
class OrderDeliveryDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
    }
}

// Main Application
public class AmazonDataManagementSystem {
    public static void main(String[] args) {
        // HashMap for efficient retrieval
        HashMap<String, Customer> customerMap = new HashMap<>();
        HashMap<String, Product> productMap = new HashMap<>();

        // TreeSet for sorting products by price
        TreeSet<Product> productsByPrice = new TreeSet<>(new ProductPriceComparator());

        // TreeSet for sorting orders by delivery date
        TreeSet<Order> ordersByDeliveryDate = new TreeSet<>(new OrderDeliveryDateComparator());

        // Create Customers
        Customer customer1 = new Customer("C001", "Alice Johnson");
        Customer customer2 = new Customer("C002", "Bob Smith");
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);

        // Create Products
        Product product1 = new Product("P001", "Laptop", 999.99);
        Product product2 = new Product("P002", "Smartphone", 599.99);
        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);

        // Add to product price sorting
        productsByPrice.add(product1);
        productsByPrice.add(product2);

        // Create Orders with different delivery dates
        Order order1 = new Order("O001", customer1, LocalDate.now().plusDays(10));
        order1.addProduct(product1);
        
        Order order2 = new Order("O002", customer2, LocalDate.now().plusDays(5));
        order2.addProduct(product2);
        
        Order order3 = new Order("O003", customer1, LocalDate.now().plusDays(15));
        order3.addProduct(product1);
        order3.addProduct(product2);

        // Add orders to delivery date sorting
        ordersByDeliveryDate.add(order1);
        ordersByDeliveryDate.add(order2);
        ordersByDeliveryDate.add(order3);

        // Print Sorted Products (by Price)
        System.out.println("Products Sorted by Price:");
        productsByPrice.forEach(System.out::println);

        // Print Orders Sorted by Delivery Date
        System.out.println("\nOrders Sorted by Delivery Date:");
        ordersByDeliveryDate.forEach(System.out::println);
    }
}