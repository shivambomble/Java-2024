/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Dashboard1 extends JFrame {
    private JPanel productPanel;
    private JButton resetCartButton, checkoutButton;
    private Connection connection;

    public Dashboard1() {
        // Set up the JFrame
        setTitle("Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Connect to Database
        connectToDatabase();

        // Header Label
        JLabel headerLabel = new JLabel("Dashboard", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.MAGENTA);
        add(headerLabel, BorderLayout.NORTH);

        // Product Panel
        productPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        JScrollPane scrollPane = new JScrollPane(productPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add products with images
        addProduct("Apple MacBook", 86000, 5, "/images/macbook (2).jpg");
        addProduct("iPhone 16", 69000, 4, "/images/iphone (2).jpg");
        addProduct("AirPods Pro", 22000, 4, "/images/airpods (1).jpg");
        addProduct("iPad Pro M4", 98000, 5, "/images/ipadpro (1).jpg");
        addProduct("Apple Charger", 1499, 4, "/images/mobilecharger (1).jpg");
        addProduct("Zebronics PowerBank", 1500, 3, "/images/powerbank (1).jpg");
        addProduct("Apple iWatch Ultra", 84000, 4, "/images/iwatchultra (1).jpg");
        addProduct("KAFF Electric Oven", 39000, 4, "/images/Oven.jpg");

        // Footer Panel for buttons
        JPanel footerPanel = new JPanel();
        resetCartButton = new JButton("Reset Cart");
        checkoutButton = new JButton("Checkout");

        resetCartButton.setBackground(Color.PINK);
        checkoutButton.setBackground(Color.PINK);

        footerPanel.add(resetCartButton);
        footerPanel.add(checkoutButton);
        add(footerPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        resetCartButton.addActionListener(e -> resetCart());
        checkoutButton.addActionListener(e -> checkoutCart());

        // Make frame visible
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecom", "root", "Selmon_19");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProduct(String name, double price, int rating, String imagePath) {
        JPanel productCard = new JPanel(new BorderLayout());
        
        // Product Image
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Product Info
        JLabel nameLabel = new JLabel(name);
        JLabel priceLabel = new JLabel("₹" + price);
        JLabel ratingLabel = new JLabel("Rating: " + rating);
        JButton addToCartButton = new JButton("Add to Cart");

        productCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        productCard.add(imageLabel, BorderLayout.NORTH);
        productCard.add(nameLabel, BorderLayout.CENTER);
        productCard.add(priceLabel, BorderLayout.WEST);
        productCard.add(ratingLabel, BorderLayout.EAST);
        productCard.add(addToCartButton, BorderLayout.SOUTH);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(name, price, rating);
            }
        });

        productPanel.add(productCard);
    }

    private void addToCart(String name, double price, int rating) {
        try {
            String query = "INSERT INTO cart (product_name, price, rating) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setInt(3, rating);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, name + " added to cart!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetCart() {
        try {
            String query = "DELETE FROM cart";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Cart reset successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkoutCart() {
    try {
        String query = "SELECT * FROM cart";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        StringBuilder receipt = new StringBuilder("Checkout Summary:\n");
        double total = 0;

        while (resultSet.next()) {
            String productName = resultSet.getString("product_name");
            double price = resultSet.getDouble("price");
            total += price;
            receipt.append(productName).append(" - ₹").append(price).append("\n");
        }

        receipt.append("\nTotal: ₹").append(total);
        
        // Display checkout summary in a dialog
        int option = JOptionPane.showConfirmDialog(this, receipt.toString() + "\nProceed to Billing?", "Checkout Summary", JOptionPane.OK_CANCEL_OPTION);
        
        // If the user clicks OK, redirect to Billing page
        if (option == JOptionPane.OK_OPTION) {
            new Billing1(receipt.toString());
            resetCart(); // Clear cart after checkout
            this.dispose();
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        new Dashboard1();
    }
}

