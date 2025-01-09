import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Billing1 extends JFrame {
    private JTextArea billingDetailsTextArea;
    private JTextField contactInfoField;
    private JTextField deliveryAddressField;
    private JButton orderButton;
    private double totalCost;

    // Constructor accepts the billing summary as a parameter
    public Billing1(String billingSummary) {
        // Extract total cost from the summary
        String[] lines = billingSummary.split("\n");
        String lastLine = lines[lines.length - 1];
        totalCost = Double.parseDouble(lastLine.split("₹")[1].trim());

        // Set up the JFrame
        setTitle("Billing");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Billing Information", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLUE);
        add(headerLabel, BorderLayout.NORTH);

        // Billing Details
        billingDetailsTextArea = new JTextArea(billingSummary);
        billingDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billingDetailsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // GST Calculation
        double gstAmount = totalCost * 0.18;
        double totalWithGST = totalCost + gstAmount;

        // Display total with GST
        billingDetailsTextArea.append("\n\nGST (18%): ₹" + String.format("%.2f", gstAmount));
        billingDetailsTextArea.append("\nTotal with GST: ₹" + String.format("%.2f", totalWithGST));

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Contact Info
        inputPanel.add(new JLabel("Contact Info:"));
        contactInfoField = new JTextField();
        inputPanel.add(contactInfoField);

        // Delivery Address
        inputPanel.add(new JLabel("Delivery Address:"));
        deliveryAddressField = new JTextField();
        inputPanel.add(deliveryAddressField);

        add(inputPanel, BorderLayout.SOUTH);

        // Order Button
        orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeOrder(totalWithGST);
            }
        });
        add(orderButton, BorderLayout.SOUTH);

        // Show the frame
        setVisible(true);
    }

    private void placeOrder(double totalWithGST) {
        String contactInfo = contactInfoField.getText();
        String deliveryAddress = deliveryAddressField.getText();

        // Validate input
        //if (contactInfo.isEmpty() || deliveryAddress.isEmpty()) {
        //    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        //    return;
        //}

        // Show success message
        JOptionPane.showMessageDialog(this, "Order placed successfully! Total: ₹" + String.format("%.2f", totalWithGST), "Order Successful", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the billing window
    }

    public static void main(String[] args) {
        // Example billing summary
        String billingSummary = "Checkout Summary:\nApple Charger - ₹1499.0\nTotal: ₹1499.0";
        new Billing1(billingSummary);
    }
}