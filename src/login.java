import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class login extends JFrame {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bubbleteapos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "root";
	static String usrForOrder;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JLabel resultLabel;
	private JRadioButton userRadioButton;
	private JRadioButton adminRadioButton;

	public login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hsu\\eclipse-workspace\\BubbleTeaShopPOS\\prj_img\\CatLogo.png"));
		setTitle("Hey U");
		setSize(1430, 841);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		ButtonGroup roleGroup = new ButtonGroup();
		resultLabel = new JLabel();

		panel.setLayout(null);

		getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(546, 286, 458, 389);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(28, 51, 173, 40);
		panel_1.add(usernameLabel);
		usernameLabel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		usernameTextField = new JTextField();
		usernameTextField.setBounds(250, 50, 173, 40);
		panel_1.add(usernameTextField);
		usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(35, 128, 134, 40);
		panel_1.add(passwordLabel);
		passwordLabel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 127, 173, 40);
		panel_1.add(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		userRadioButton = new JRadioButton("User");
		userRadioButton.setBackground(new Color(255, 204, 102));
		userRadioButton.setBounds(28, 225, 173, 40);
		panel_1.add(userRadioButton);
		userRadioButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		roleGroup.add(userRadioButton);
		adminRadioButton = new JRadioButton("Admin");
		adminRadioButton.setBackground(new Color(255, 204, 102));
		adminRadioButton.setBounds(250, 225, 173, 40);
		panel_1.add(adminRadioButton);
		adminRadioButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		roleGroup.add(adminRadioButton);
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(new Color(255, 255, 255));
		loginButton.setBounds(136, 313, 173, 56);
		panel_1.add(loginButton);
		loginButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));

		JLabel lblHeyU = new JLabel("HEY U");
		lblHeyU.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		lblHeyU.setBounds(691, 119, 173, 59);
		panel.add(lblHeyU);

		JLabel lblBubbleTeaAnd = new JLabel("Bubble Tea And Snacks");
		lblBubbleTeaAnd.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
		lblBubbleTeaAnd.setBounds(516, 188, 557, 59);
		panel.add(lblBubbleTeaAnd);

		JLabel lblHeyU_1 = new JLabel("");
		lblHeyU_1.setIcon(new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\BubbleTeaShopPOS\\prj_img\\CatLogo.png"));
		lblHeyU_1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		lblHeyU_1.setBounds(738, 50, 74, 59);
		panel.add(lblHeyU_1);

		JButton poweroffBtn = new JButton("");
		poweroffBtn.setBackground(new Color(255, 153, 0));
		poweroffBtn.setIcon(
				new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\BubbleTeaShopPOS\\prj_img\\icons8-power-off-50.png"));
		poweroffBtn.setBounds(1406, 10, 68, 59);
		panel.add(poweroffBtn);

		poweroffBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure to shutdown?", "Exit Confirmation",
						JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					dispose(); // Close the current window

					// Show a loading screen
					JDialog loadingDialog = new JDialog();
					loadingDialog.setUndecorated(true);
					JLabel loadingLabel = new JLabel("Loading...");
					loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
					loadingDialog.getContentPane().add(loadingLabel);
					loadingDialog.setSize(200, 100);
					loadingDialog.setLocationRelativeTo(null);
					loadingDialog.setVisible(true);

					// Delay the application exit using Swing Timer
					Timer timer = new Timer(5000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							loadingDialog.dispose(); // Close the loading dialog
							System.exit(0); // Exit the application after 5 seconds
						}
					});
					timer.setRepeats(false); // Execute only once
					timer.start();
				}
			}
		});
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = new String(passwordField.getPassword());

				if (authenticateUser(username, password)) {
					String role = getUserRole(username);

					if ("user".equals(role)) {
						// User authenticated successfully, redirect to order_view
						dispose();
						new order_view(null, username).setVisible(true);
					} else if ("admin".equals(role)) {
						// Admin authenticated successfully, redirect to adminpanel
						dispose();
						new adminpanel().setVisible(true);
						JOptionPane.showMessageDialog(null, "Welcome " + username);
					} else {
						// Handle other roles or situations
						JOptionPane.showMessageDialog(null, "Unknown role. Contact support.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");
				}

			}
		});
		getContentPane().add(resultLabel, BorderLayout.SOUTH);
	}


	private static boolean authenticateUser(String username, String password) {
		// Check if the username or password is empty
		if (username.isEmpty() || password.isEmpty()) {
			return false; // Return false if the fields are empty
		}

		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			// usrForOrder= resultSet.getString(1);
			boolean authenticated = resultSet.next();

			resultSet.close();
			preparedStatement.close();
			connection.close();

			return authenticated;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private static String getUserRole(String username) {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			String query = "SELECT role FROM users WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			String role = null;
			if (resultSet.next()) {
				role = resultSet.getString("role");
				System.out.println("Retrieved Role: " + role); // Add this line to log the retrieved role
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();

			return role;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new login().setVisible(true);
			}
		});
	}
}