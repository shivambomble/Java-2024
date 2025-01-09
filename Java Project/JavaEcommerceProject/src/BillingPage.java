/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import javax.swing.*;

public class BillingPage extends JFrame {
    public BillingPage() {
        setTitle("Billing");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitle = new JLabel("Billing Page");
        lblTitle.setBounds(150, 30, 100, 30);
        add(lblTitle);

        JLabel lblItem = new JLabel("Item:");
        lblItem.setBounds(50, 80, 100, 30);
        add(lblItem);

        JTextField txtItem = new JTextField();
        txtItem.setBounds(150, 80, 200, 30);
        add(txtItem);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(50, 130, 100, 30);
        add(lblAmount);

        JTextField txtAmount = new JTextField();
        txtAmount.setBounds(150, 130, 200, 30);
        add(txtAmount);

        JButton btnSave = new JButton("Save Bill");
        btnSave.setBounds(150, 180, 100, 30);
        add(btnSave);

        JButton btnDashboard = new JButton("Back to Dashboard");
        btnDashboard.setBounds(50, 230, 150, 30);
        add(btnDashboard);

        JButton btnPayment = new JButton("Go to Payment");
        btnPayment.setBounds(220, 230, 150, 30);
        add(btnPayment);

        btnDashboard.addActionListener(e -> {
            new DashboardPage().setVisible(true);
            dispose();
        });

        btnPayment.addActionListener(e -> {
            new PaymentPage().setVisible(true);
            dispose();
        });

        btnSave.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Bill Saved: " + txtItem.getText() + ", Amount: " + txtAmount.getText());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BillingPage().setVisible(true);
        });
    }
}

