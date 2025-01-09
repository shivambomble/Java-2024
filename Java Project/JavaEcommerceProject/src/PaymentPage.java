/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import javax.swing.*;

public class PaymentPage extends JFrame {
    public PaymentPage() {
        setTitle("Payment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Payment Page");
        lblTitle.setBounds(150, 30, 100, 30);
        add(lblTitle);

        JLabel lblCardNumber = new JLabel("Card Number:");
        lblCardNumber.setBounds(50, 80, 100, 30);
        add(lblCardNumber);

        JTextField txtCardNumber = new JTextField();
        txtCardNumber.setBounds(150, 80, 200, 30);
        add(txtCardNumber);

        JLabel lblExpiry = new JLabel("Expiry Date:");
        lblExpiry.setBounds(50, 130, 100, 30);
        add(lblExpiry);

        JTextField txtExpiry = new JTextField();
        txtExpiry.setBounds(150, 130, 200, 30);
        add(txtExpiry);

        JLabel lblCVC = new JLabel("CVC:");
        lblCVC.setBounds(50, 180, 100, 30);
        add(lblCVC);

        JTextField txtCVC = new JTextField();
        txtCVC.setBounds(150, 180, 200, 30);
        add(txtCVC);

        JButton btnPay = new JButton("Make Payment");
        btnPay.setBounds(150, 230, 150, 30);
        add(btnPay);

        JButton btnDashboard = new JButton("Back to Dashboard");
        btnDashboard.setBounds(20, 230, 150, 30);
        add(btnDashboard);

        btnPay.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Payment Successful!");
        });

        btnDashboard.addActionListener(e -> {
            new DashboardPage().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PaymentPage().setVisible(true);
        });
    }
}

