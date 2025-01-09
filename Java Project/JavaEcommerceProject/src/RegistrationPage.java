import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class RegistrationPage extends JFrame {
    public RegistrationPage() {
        setTitle("Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create a main panel with a custom background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(173, 216, 230)); // Light blue color
        mainPanel.setBounds(0, 0, 400, 300);
        add(mainPanel);

        // Add a title label
        JLabel lblTitle = new JLabel("User Registration");
        lblTitle.setBounds(120, 10, 200, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0, 102, 204)); // Dark blue color
        mainPanel.add(lblTitle);

        // Add components with updated design
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 60, 100, 30);
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(lblUsername);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(150, 60, 200, 30);
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 110, 100, 30);
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 200, 30);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(txtPassword);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 160, 100, 30);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(lblEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(150, 160, 200, 30);
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        mainPanel.add(txtEmail);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(150, 210, 100, 30);
        btnRegister.setBackground(new Color(0, 153, 76)); // Green button
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        mainPanel.add(btnRegister);

        // Add action listener for the Register button
        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String email = txtEmail.getText();

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce", "root", "your_password")) {
                String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, email);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration successful!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationPage().setVisible(true);
        });
    }
}
