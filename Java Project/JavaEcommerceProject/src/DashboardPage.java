/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import javax.swing.*;

public class DashboardPage extends JFrame {
    public DashboardPage() {
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Welcome to the Dashboard!");
        lblTitle.setBounds(100, 50, 200, 30);
        add(lblTitle);

        JButton btnBilling = new JButton("Go to Billing");
        btnBilling.setBounds(100, 100, 200, 30);
        add(btnBilling);

        JButton btnPayment = new JButton("Go to Payment");
        btnPayment.setBounds(100, 150, 200, 30);
        add(btnPayment);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(100, 200, 200, 30);
        add(btnLogout);

        btnBilling.addActionListener(e -> {
            new BillingPage().setVisible(true);
            dispose();
        });

        btnPayment.addActionListener(e -> {
            new PaymentPage().setVisible(true);
            dispose();
        });

        btnLogout.addActionListener(e -> {
            new LoginPage().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardPage().setVisible(true);
        });
    }
}
