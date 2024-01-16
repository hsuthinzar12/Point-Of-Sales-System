import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class adminpanel extends JFrame {

	private JPanel contentPane;
	private JLabel incomelbl, totalincomelbl;
	private JTextField serctxtarea;
	private JTable itemTable, userTable;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bubbleteapos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "root";
	String currentButton = "";
	public JScrollPane scrollPane;
	
	private void handleSidebarButtonClick(String buttonClicked) {
		switch (buttonClicked) {
		case "Menu":
			refreshItemList();
			scrollPane.setViewportView(itemTable);
			break;
		case "Orders":
			refreshOrdersList();
			
			break;
		case "Staffs":
			refreshStaffList();
			scrollPane.setViewportView(null);
			scrollPane.setViewportView(userTable);
			break;
		case "Admin":
			refreshAdmList();
			scrollPane.setViewportView(null);
			scrollPane.setViewportView(userTable);
			break;
		case "Today's Income":
			refreshTodayIncomeList();
			break;
		case "Total Income":
			refreshTotalIncome();
			break;

		default:
			break;
		}
	}

	private JButton createSidebarButton(String buttonText) {
		JButton button = new JButton(buttonText);
		button.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 18));
		button.setBackground(new Color(255, 204, 102));
		button.addActionListener(e -> handleSidebarButtonClick(buttonText));
		return button;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				adminpanel frame = new adminpanel();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private double calculateTodayIncome() throws SQLException {
		double todayIncome = 0.0;

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			String query = "SELECT SUM(total_amount) AS totalIncome FROM orders WHERE DATE(sale_date) = CURDATE()";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					todayIncome = resultSet.getDouble("totalIncome");
				}
			}
		}
		return todayIncome;
	}

	private double calculateTotalIncome() throws SQLException {
		double totalIncome = 0.0;

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			String query = "SELECT SUM(total_amount) AS totalIncome FROM orders";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					totalIncome = resultSet.getDouble("totalIncome");
				}
			}
		}

		return totalIncome;
	}

	/*
	 * private void editItemFromTable() { int selectedRow =
	 * itemTable.getSelectedRow(); //System.out.println(selectedRow); if
	 * (selectedRow == -1) { JOptionPane.showMessageDialog(null,
	 * "Please select an item to edit."); return; }
	 * 
	 * int itemId = (int) itemTable.getValueAt(selectedRow, 0); String itemCategory
	 * = (String) itemTable.getValueAt(selectedRow, 5); String itemName = (String)
	 * itemTable.getValueAt(selectedRow, 1); double itemPrice = (double)
	 * itemTable.getValueAt(selectedRow, 2); int itemQuantity = (int)
	 * itemTable.getValueAt(selectedRow, 3); String itemImage = (String)
	 * itemTable.getValueAt(selectedRow, 4);
	 * 
	 * EditItemDialog editItemDialog = new EditItemDialog(itemId, itemCategory,
	 * itemName, itemPrice, itemQuantity, itemImage);
	 * editItemDialog.setVisible(true);
	 * 
	 * // Refresh the item list after editing refreshItemList(); }
	 */

	private class EditItemDialog extends JDialog {

		// Existing fields
		private JTextField itemCategoryField;
		private JTextField itemNameField;
		private JTextField itemPriceField;
		private JTextField itemQuantityField;
		private JLabel itemImageLabel;
		private JButton chooseImageButton;

		private int itemId;

		public EditItemDialog(int itemId, String itemCategory, String itemName, double itemPrice, int itemQuantity,
				String itemImage) {
			this.itemId = itemId;
			setTitle("Edit Item");
			setSize(500, 250); // Increased height to accommodate image display
			setLocationRelativeTo(null);
			setLayout(new GridLayout(3, 2)); // Increased rows for image display

			add(new JLabel("Item ID:"));
			add(new JLabel(String.valueOf(itemId)));

			add(new JLabel("Item Category:"));
			itemCategoryField = new JTextField(itemCategory);
			add(itemCategoryField);

			add(new JLabel("Item Name:"));
			itemNameField = new JTextField(itemName);
			add(itemNameField);

			add(new JLabel("Item Price:"));
			itemPriceField = new JTextField(String.valueOf(itemPrice));
			add(itemPriceField);

			add(new JLabel("Item Quantity:"));
			itemQuantityField = new JTextField(String.valueOf(itemQuantity));
			add(itemQuantityField);

			// Adding the image display
			add(new JLabel("Item Image:"));
			JPanel imagePanel = new JPanel(new BorderLayout());
			itemImageLabel = new JLabel();

			if (itemImage != null && !itemImage.isEmpty()) {
				ImageIcon imageIcon = new ImageIcon(itemImage);
				itemImageLabel.setIcon(imageIcon);
			}

			imagePanel.add(itemImageLabel, BorderLayout.CENTER);

			chooseImageButton = new JButton("Choose Image");
			chooseImageButton.addActionListener(e -> selectImage());
			imagePanel.add(chooseImageButton, BorderLayout.SOUTH);

			add(imagePanel);

			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(e -> saveChanges());
			add(saveButton);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(e -> dispose());
			add(cancelButton);
		}

		private void selectImage() {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
				itemImageLabel.setIcon(imageIcon);
			}
		}

		private void saveChanges() {
			// Retrieve data from fields, handle numeric parsing, update database, etc.
			String newCategory = itemCategoryField.getText();
			String newName = itemNameField.getText();
			double newPrice = 0;
			int newQuantity = 0;
			String selectedImagePath = ""; // String to hold the selected image path

			if (itemImageLabel.getIcon() != null) {
				// Retrieve the image path if an image is selected
				selectedImagePath = ((ImageIcon) itemImageLabel.getIcon()).getDescription();
			}

			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				// Your database update logic using selectedImagePath
				String query = "UPDATE items SET category_name = ?, name = ?, price = ?, quantity = ?, image = ? WHERE id = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, newCategory);
					preparedStatement.setString(2, newName);
					preparedStatement.setDouble(3, newPrice);
					preparedStatement.setInt(4, newQuantity);
					preparedStatement.setString(5, selectedImagePath);
					preparedStatement.setInt(6, itemId);

					int rowsAffected = preparedStatement.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Item updated successfully!");
						dispose(); // Close the dialog
						refreshItemList(); // Refresh the item list
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update item. Item not found.");
					}
				}

				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private class EditUserDialog extends JDialog {

		private JTextField userNameField;
		private JTextField userRoleField;

		private int userId;

		public EditUserDialog(int userId, String userName, String userRole) {

			this.userId = userId;
			setTitle("Edit User");
			setSize(400, 150);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(3, 2));

			// Populate fields with existing user information
			add(new JLabel("User ID:"));
			add(new JLabel(String.valueOf(userId)));

			add(new JLabel("User Name:"));
			userNameField = new JTextField(userName);
			add(userNameField);

			add(new JLabel("User Role:"));
			userRoleField = new JTextField(userRole);
			add(userRoleField);

			JButton saveButton = new JButton("Save");
			saveButton.addActionListener(e -> saveChanges());
			add(saveButton);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(e -> dispose());
			add(cancelButton);
		}

		private void saveChanges() {
			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				// Retrieve data from the dialog fields
				String newName = userNameField.getText();
				String newRole = userRoleField.getText();

				// Update the database
				String query = "UPDATE users SET username = ?, role = ? WHERE id = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, newName);
					preparedStatement.setString(2, newRole);
					preparedStatement.setInt(3, userId);

					int rowsAffected = preparedStatement.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "User added successfully!");
						dispose();
						// Move these lines here after successful update
						refreshAdmList();
						refreshStaffList();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update user. User not found.");
					}
				}

				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private DefaultTableModel createTableModel() {
		Vector<Vector<Object>> data = new Vector<>();
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Select");
		columnNames.add("ID");
		columnNames.add("Category");
		columnNames.add("Name");
		columnNames.add("Price");
		columnNames.add("Quantity");
		columnNames.add("Image");
		return new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 0 ? Boolean.class : super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
	}

	// order table
	private DefaultTableModel createOrdersTableModel() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Order ID");
		columnNames.add("Order Name");
		columnNames.add("Cup Size");
		columnNames.add("Toppings");
		columnNames.add("Total Amount");
		columnNames.add("Sale Date");
		columnNames.add("Sugar/Ice");
		columnNames.add("Quantity");

		return new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private DefaultTableModel createTodayIncomeTableModel() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Order ID");
		columnNames.add("Order Name");
		columnNames.add("Cup Size");
		columnNames.add("Toppings");
		columnNames.add("Total Amount");
		columnNames.add("Sale Date");
		columnNames.add("Sugar/Ice");
		columnNames.add("Quantity");

		return new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private DefaultTableModel createTotalIncomeTableModel() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Order ID");
		columnNames.add("Order Name");
		columnNames.add("Cup Size");
		columnNames.add("Toppings");
		columnNames.add("Total Amount");
		columnNames.add("Sale Date");
		columnNames.add("Sugar/Ice");
		columnNames.add("Quantity");

		return new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	// Create a DefaultTableModel for staff
	private DefaultTableModel createStaffTableModel() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Select");
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Password");
		columnNames.add("Role");

		return new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 0 ? Boolean.class : super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
	}

	// Create a DefaultTableModel for admin
	private DefaultTableModel createAdminTableModel() {
		Vector<String> columnNames = new Vector<>();
		columnNames.add("Select");
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Password");
		columnNames.add("Role");

		return new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 0 ? Boolean.class : super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
	}

	public adminpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel sidebar = new JPanel();
		sidebar.setBounds(0, 50, 200, 444);
		sidebar.setBackground(new Color(51, 51, 51));
		contentPane.add(sidebar);
		sidebar.setLayout(new GridLayout(5, 1));

		// Create sidebar buttons
		JButton btnadmMenu = createSidebarButton("Menu");
		sidebar.add(btnadmMenu);

		/*
		 * JButton btnadmOrd = createSidebarButton("Orders"); sidebar.add(btnadmOrd);
		 */

		JButton btnadmStf = createSidebarButton("Staffs");
		sidebar.add(btnadmStf);

		JButton btnadmAdm = createSidebarButton("Admin");
		sidebar.add(btnadmAdm);

		JButton btntdyIncome = createSidebarButton("Today's Income");
		sidebar.add(btntdyIncome);

		JButton btntotalIncome = createSidebarButton("Total Income");
		sidebar.add(btntotalIncome);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panel.setBounds(772, 0, 768, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		// Adding a logout button
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(599, 0, 168, 50);
		panel.add(btnLogout);
		btnLogout.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(adminpanel.this, "Are you sure you want to logout?",
						"Logout", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					dispose();
					login loginFrame = new login();
					loginFrame.setVisible(true);
				}
			}
		});

		itemTable = new JTable();
		itemTable.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 14));
		itemTable.setRowHeight(30);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		itemTable.setDefaultRenderer(Object.class, centerRenderer);

		JTableHeader tableHeader = itemTable.getTableHeader();
		tableHeader.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 16));

		scrollPane = new JScrollPane(itemTable);
		scrollPane.setBounds(200, 50, 1347, 600);
		contentPane.add(scrollPane);

		userTable = new JTable();
		userTable.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 14));
		userTable.setRowHeight(30);

		DefaultTableCellRenderer cR = new DefaultTableCellRenderer();
		cR.setHorizontalAlignment(JLabel.CENTER);
		userTable.setDefaultRenderer(Object.class, centerRenderer);

		JTableHeader tH = userTable.getTableHeader();
		tH.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 16));


		serctxtarea = new JTextField();
		serctxtarea.setBounds(200, 10, 400, 30);
		serctxtarea.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 14));
		contentPane.add(serctxtarea);
		serctxtarea.setColumns(10);

		JButton sercbtn = new JButton("Search");
		sercbtn.setBounds(610, 10, 100, 30);
		sercbtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 14));
		contentPane.add(sercbtn);

		JButton addbtn = new JButton("Add");
		addbtn.setBounds(200, 668, 175, 50);
		addbtn.setBackground(new Color(255, 204, 102));
		addbtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 14));
		contentPane.add(addbtn);

		JButton editbtn = new JButton("Edit");
		editbtn.setBounds(412, 668, 193, 50);
		editbtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 14));
		editbtn.setBackground(new Color(255, 204, 102));
		contentPane.add(editbtn);

		JButton deletebtn = new JButton("Delete");
		deletebtn.setBounds(642, 668, 184, 50);
		deletebtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 14));
		deletebtn.setBackground(new Color(255, 204, 102));
		contentPane.add(deletebtn);

		incomelbl = new JLabel("");
		incomelbl.setFont(new Font("Lucida Console", Font.BOLD, 25));
		incomelbl.setBounds(1073, 663, 404, 56);
		contentPane.add(incomelbl);

		refreshTodayIncomeList();

		totalincomelbl = new JLabel("Total Income: $0.0");
		totalincomelbl.setFont(new Font("Lucida Console", Font.BOLD, 25));
		totalincomelbl.setBounds(1073, 731, 404, 56);
		contentPane.add(totalincomelbl);

		refreshTotalIncome();

		// Action listeners for different buttons
		btnadmStf.addActionListener(e -> {
			currentButton = "Staff";
		});

		btnadmMenu.addActionListener(e -> {
			currentButton = "Menu";
		});
		btnadmAdm.addActionListener(e -> {
			currentButton = "Admin";
		});
		/*
		 * btnadmOrd.addActionListener(e -> { currentButton = "Orders"; });
		 */
		btntdyIncome.addActionListener(e -> {
			currentButton = "Today";
		});

		// Add action listeners for the buttons
		addbtn.addActionListener(e -> {
			if (currentButton.equals("Staff")) {
				addUsr();
			} else if (currentButton.equals("Menu")) {
				addItem();
			} else if (currentButton.equals("Admin")) {
				addUsr();
			}
		});
		editbtn.addActionListener(e -> {
			if (currentButton.equals("Staff")) {
				editUser();
			} else if (currentButton.equals("Menu")) {
				editItem();
			} else if (currentButton.equals("Admin")) {
				editUser();
			}
		});
		deletebtn.addActionListener(e -> {
			if (currentButton.equals("Staff")) {
				deleteUser();
			} else if (currentButton.equals("Menu")) {
				deleteItem();
			} else if (currentButton.equals("Admin")) {
				deleteAdmin();
			}
		});
		// sercbtn.addActionListener(e -> searchItem());
		serctxtarea.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchItem();
			}
		});

		/*
		 * sercbtn.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { searchItem(); } });
		 */
		sercbtn.addActionListener(e -> {
			if (currentButton.equals("Menu")) {
				searchItem();
			} else if (currentButton.equals("Staffs")) {
				/* searchStaff(null, JDBC_PASSWORD, null); */
			}
		});

		btnadmMenu.addActionListener(e -> adminpanel.this.handleSidebarButtonClick("Menu"));
		btnadmStf.addActionListener(e -> adminpanel.this.handleSidebarButtonClick("Staffs"));
		btnadmAdm.addActionListener(e -> adminpanel.this.handleSidebarButtonClick("Admin"));
		btntdyIncome.addActionListener(e -> adminpanel.this.handleSidebarButtonClick("Today's Income"));
		btntotalIncome.addActionListener(e -> adminpanel.this.handleSidebarButtonClick("Total Income"));

		// Initialize the item list
		refreshItemList();
	}

	private void addItem() {
		JPanel addPanel = new JPanel(new GridLayout(6, 2));
		JTextField categoryField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField quantityField = new JTextField();
		JLabel imageLabel = new JLabel("No Image Selected");

		addPanel.add(new JLabel("Category:"));
		addPanel.add(categoryField);
		addPanel.add(new JLabel("Name:"));
		addPanel.add(nameField);
		addPanel.add(new JLabel("Price:"));
		addPanel.add(priceField);
		addPanel.add(new JLabel("Quantity:"));
		addPanel.add(quantityField);
		addPanel.add(new JLabel("Select Image:"));
		addPanel.add(imageLabel);

		JButton uploadButton = new JButton("Upload Image");
		uploadButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				imageLabel.setText(selectedFile.getPath());
			}
		});

		addPanel.add(uploadButton);

		int result = JOptionPane.showConfirmDialog(null, addPanel, "Add Item", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				// Retrieve data from the input fields
				String category = categoryField.getText();
				String name = nameField.getText();
				double price = Double.parseDouble(priceField.getText());
				int quantity = Integer.parseInt(quantityField.getText());
				String imagePath = imageLabel.getText();

				// Find the current maximum ID
				String getMaxIdQuery = "SELECT MAX(id) FROM items";
				try (PreparedStatement getMaxIdStatement = connection.prepareStatement(getMaxIdQuery);
						ResultSet maxIdResultSet = getMaxIdStatement.executeQuery()) {

					int maxId = 0;
					if (maxIdResultSet.next()) {
						maxId = maxIdResultSet.getInt(1);
					}

					// Insert into the database with the next ID
					String query = "INSERT INTO items (id, category_name, name, price, quantity, image) VALUES (?, ?, ?, ?, ?, ?)";
					try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
						preparedStatement.setInt(1, maxId + 1);
						preparedStatement.setString(2, category);
						preparedStatement.setString(3, name);
						preparedStatement.setDouble(4, price);
						preparedStatement.setInt(5, quantity);
						preparedStatement.setString(6, imagePath);

						preparedStatement.executeUpdate();

						JOptionPane.showMessageDialog(null, "Item added successfully!");
						refreshItemList(); // Refresh the item list
					}
				}

				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void addUsr() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			// Fetch the maximum ID from the users table
			String getMaxIdQuery = "SELECT MAX(id) FROM users";
			int maxId = 0;
			try (PreparedStatement getMaxIdStatement = connection.prepareStatement(getMaxIdQuery);
					ResultSet maxIdResultSet = getMaxIdStatement.executeQuery()) {
				if (maxIdResultSet.next()) {
					maxId = maxIdResultSet.getInt(1);
				}
			}

			// Retrieve data from UI components
			JPanel addUserPanel = new JPanel(new GridLayout(4, 2));
			JTextField userNameField = new JTextField();
			JPasswordField passwordField = new JPasswordField();
			JTextField userroleTextField = new JTextField();

			addUserPanel.add(new JLabel("User Name:"));
			addUserPanel.add(userNameField);
			addUserPanel.add(new JLabel("Password:"));
			addUserPanel.add(passwordField);
			addUserPanel.add(new JLabel("User Role (user/admin):"));
			addUserPanel.add(userroleTextField);

			int result = JOptionPane.showConfirmDialog(null, addUserPanel, "Add User", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				String usrname = userNameField.getText();
				String usrpw = new String(passwordField.getPassword());
				String usrRole = userroleTextField.getText().toLowerCase();

				if (!usrRole.equals("user") && !usrRole.equals("admin")) {
					JOptionPane.showMessageDialog(null, "Invalid role. Please enter 'user' or 'admin'.");
					return;
				}

				String query = "INSERT INTO users (id, username, password, role) VALUES (?, ?, ?, ?)";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setInt(1, maxId + 1);
					preparedStatement.setString(2, usrname);
					preparedStatement.setString(3, usrpw);
					preparedStatement.setString(4, usrRole);

					int rowsAffected = preparedStatement.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "User added successfully!");
						// Refresh the user list or perform any other necessary actions
					} else {
						JOptionPane.showMessageDialog(null, "Failed to add user.");
					}
				}
				refreshAdmList();
				refreshStaffList();
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Edit items
	private void editItem() {
		int selectedRow = itemTable.getSelectedRow();
		System.out.println(selectedRow);
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select an item to edit.");
			return;
		}

		String[] columnNames = { "ID", "Category", "Name", "Price", "Quantity", "Image" };

		// Retrieve data from the selected row
		String name = (String) itemTable.getValueAt(selectedRow, 3);
		String category = String.valueOf(itemTable.getValueAt(selectedRow, 2));
		double price = 0;
		int quantity = 0;
		int id = 0;

		// Handling data type conversion from Double to String for category
		try {
			id = Integer.parseInt(itemTable.getValueAt(selectedRow, 1).toString());
			price = Double.parseDouble(itemTable.getValueAt(selectedRow, 4).toString());
			quantity = Integer.parseInt(itemTable.getValueAt(selectedRow, 5).toString());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid data format in Price or Quantity.");
			return;
		}

		// Text fields for editing
		JPanel editPanel = new JPanel(new GridLayout(6, 2));
		JTextField categoryField = new JTextField(category);
		JTextField nameField = new JTextField(name);
		JTextField priceField = new JTextField(Double.toString(price));
		JTextField quantityField = new JTextField(Integer.toString(quantity));
		JLabel imageLabel = new JLabel();
		JButton chooseImageButton = new JButton("Choose Image");

		// Handling image selection
		chooseImageButton.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();
				String imagePath = selectedFile.getPath();
				imageLabel.setText(imagePath);

			}
		});

		editPanel.add(new JLabel("Category:"));
		editPanel.add(categoryField);
		editPanel.add(new JLabel("Name:"));
		editPanel.add(nameField);
		editPanel.add(new JLabel("Price:"));
		editPanel.add(priceField);
		editPanel.add(new JLabel("Quantity:"));
		editPanel.add(quantityField);
		editPanel.add(new JLabel("Image:"));
		editPanel.add(chooseImageButton);
		editPanel.add(new JLabel("Selected Image Path:"));
		editPanel.add(imageLabel);

		int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Item", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				String query = "UPDATE items SET category_name = ?, name = ?, price = ?, quantity = ?, image = ? WHERE id = ?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, categoryField.getText());
					preparedStatement.setString(2, nameField.getText());
					preparedStatement.setDouble(3, Double.parseDouble(priceField.getText()));
					preparedStatement.setInt(4, Integer.parseInt(quantityField.getText()));
					preparedStatement.setString(5, imageLabel.getText());
					preparedStatement.setInt(6, id);

					int rowsAffected = preparedStatement.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "Item updated successfully!");
						refreshItemList(); // Refresh the item list
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update item. Item not found.");
					}
				}

				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Edit users
	private void editUser() {
		int selectedRow = userTable.getSelectedRow();

		System.out.println(selectedRow);
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a user to edit.");
			return;
		}
		// Retrieve data from the selected row
		int ID =(int) userTable.getValueAt(selectedRow, 1);
		String username = (String) userTable.getValueAt(selectedRow, 2);
		String password = "";
		String role = (String) userTable.getValueAt(selectedRow, 4);

		// text fields for editing
		JPanel editPanel = new JPanel(new GridLayout(3, 2));
		JTextField usernameField = new JTextField(username);
		JPasswordField passwordField = new JPasswordField();
		JTextField roleField = new JTextField(role);

		editPanel.add(new JLabel("Username:"));
		editPanel.add(usernameField);
		editPanel.add(new JLabel("Password:"));
		editPanel.add(passwordField);
		editPanel.add(new JLabel("Role:"));
		editPanel.add(roleField);

		int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit User", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			// Update the table and database with the new values
			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				String query = "UPDATE users SET username=?, password=?, role=? WHERE id=?";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1,new String( usernameField.getText()));
					preparedStatement.setString(2, new String(passwordField.getPassword()));
					preparedStatement.setString(3, roleField.getText());
					preparedStatement.setInt(4, ID);

					int rowsAffected = preparedStatement.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(null, "User updated successfully!");
						refreshStaffList();
						refreshAdmList(); // Refresh the user list
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update user. User not found.");
					}
				}

				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Delete Items
	private void deleteItem() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			// Retrieve data from UI components
			int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter item ID to delete:"));

			// Retrieve the current highest ID
			String getMaxIdQuery = "SELECT MAX(id) FROM items";
			try (PreparedStatement getMaxIdStatement = connection.prepareStatement(getMaxIdQuery);
					ResultSet maxIdResultSet = getMaxIdStatement.executeQuery()) {

				int maxId = 0;
				if (maxIdResultSet.next()) {
					maxId = maxIdResultSet.getInt(1);
				}

				// Delete from the database
				String deleteQuery = "DELETE FROM items WHERE id=?";
				try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
					deleteStatement.setInt(1, idToDelete);

					int rowsAffected = deleteStatement.executeUpdate();

					// If deletion is successful, renumber the IDs
					if (rowsAffected > 0) {
						for (int i = idToDelete + 1; i <= maxId; i++) {
							String updateIdQuery = "UPDATE items SET id=? WHERE id=?";
							try (PreparedStatement updateIdStatement = connection.prepareStatement(updateIdQuery)) {
								updateIdStatement.setInt(1, i - 1);
								updateIdStatement.setInt(2, i);
								updateIdStatement.executeUpdate();
							}
						}

						JOptionPane.showMessageDialog(null, "Item deleted successfully!");
						refreshItemList(); // Refresh the item list
					} else {
						JOptionPane.showMessageDialog(null, "Failed to delete item. Item not found.");
					}
				}
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void refreshItemList() {
		refreshItemList(true); // Show items by default
	}

	// For items list <side bar>
	private void refreshItemList(boolean showItems) {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			DefaultTableModel model = createTableModel();

			if (showItems) {
				String query = "SELECT * FROM items";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						Vector<Object> row = new Vector<>();
						row.add(false);
						row.add(resultSet.getInt("id"));
						row.add(resultSet.getString("category_name"));
						row.add(resultSet.getString("name"));
						row.add(resultSet.getDouble("price"));
						row.add(resultSet.getInt("quantity"));
						row.add(resultSet.getString("image"));
						model.addRow(row);
					}
				}
			}

			itemTable.setModel(model);
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Orders

	private void refreshOrdersList() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			DefaultTableModel model = createOrdersTableModel();

			String query = "SELECT * FROM orders";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(resultSet.getInt("order_id"));
					row.add(resultSet.getString("order_name"));
					row.add(resultSet.getString("cup_size"));
					row.add(resultSet.getString("toppings"));
					row.add(resultSet.getDouble("total_amount"));
					row.add(resultSet.getDate("sale_date"));
					row.add(resultSet.getString("suger_ice"));
					row.add(resultSet.getInt("quantity"));
					model.addRow(row);
				}

				itemTable.setModel(model);
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void refreshTodayIncomeList() {
		try {
			DefaultTableModel model = createTodayIncomeTableModel();

			// Calculate today's income
			double todayIncome = calculateTodayIncome();

			incomelbl.setText("Today's Income: $" + todayIncome); // Update label text

			// Retrieve and populate table data
			try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
				String query = "SELECT * FROM orders WHERE DATE(sale_date) = CURDATE()";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						Vector<Object> row = new Vector<>();
						row.add(resultSet.getInt("order_id"));
						row.add(resultSet.getString("order_name"));
						row.add(resultSet.getString("cup_size"));
						row.add(resultSet.getString("toppings"));
						row.add(resultSet.getDouble("total_amount"));
						row.add(resultSet.getDate("sale_date"));
						row.add(resultSet.getString("suger_ice"));
						row.add(resultSet.getInt("quantity"));
						model.addRow(row);
					}

					itemTable.setModel(model);
				}
			}
		} catch (Exception ex) {
		}
	}

	private void refreshTotalIncome() {
		try {
			DefaultTableModel model = createTotalIncomeTableModel();

			// Calculate total income
			double totalIncome = calculateTotalIncome();

			totalincomelbl.setText("Total Income: $" + totalIncome); // Update total income label text

			// Retrieve and populate table data for all orders
			try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
				String query = "SELECT * FROM orders";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						Vector<Object> row = new Vector<>();
						row.add(resultSet.getInt("order_id"));
						row.add(resultSet.getString("order_name"));
						row.add(resultSet.getString("cup_size"));
						row.add(resultSet.getString("toppings"));
						row.add(resultSet.getDouble("total_amount"));
						row.add(resultSet.getDate("sale_date"));
						row.add(resultSet.getString("suger_ice"));
						row.add(resultSet.getInt("quantity"));
						model.addRow(row);
					}

					itemTable.setModel(model); // Update total item table with all orders
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// For staff list <side bar>
	private void refreshStaffList() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			DefaultTableModel model = createStaffTableModel();

			String query = "SELECT * FROM users WHERE role='user'";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(false); // Initial value for the selection column
					row.add(resultSet.getInt("id"));
					row.add(resultSet.getString("username"));
					row.add(resultSet.getString("password"));
					row.add(resultSet.getString("role"));
					model.addRow(row);
				}

				userTable.setModel(model);
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// deleteuser
	private void deleteUser() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			// Retrieve data from UI components
			int usrIdtoDel = Integer.parseInt(JOptionPane.showInputDialog("Enter user ID to delete:"));

			// Delete user from the database
			String deleteQuery = "DELETE FROM users WHERE id=?";
			try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
				deleteStatement.setInt(1, usrIdtoDel);

				int rowsAffected = deleteStatement.executeUpdate();

				// If deletion is successful
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "User deleted successfully!");
					refreshStaffList(); // Refresh the user list
				} else {
					JOptionPane.showMessageDialog(null, "Failed to delete user. User not found.");
				}
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// For admin list <side bar>
	private void refreshAdmList() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			DefaultTableModel model = createAdminTableModel();

			String query = "SELECT * FROM users WHERE role='admin'";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(false); // Initial value for the selection column
					row.add(resultSet.getInt("id"));
					row.add(resultSet.getString("username"));
					row.add(resultSet.getString("password"));
					row.add(resultSet.getString("role"));
					model.addRow(row);
				}

				userTable.setModel(model);
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// deleteadmin
	private void deleteAdmin() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			// Retrieve data from UI components
			int admIdtoDel = Integer.parseInt(JOptionPane.showInputDialog("Enter admin ID to delete:"));

			// Delete user from the database
			String deleteQuery = "DELETE FROM users WHERE id=?";
			try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
				deleteStatement.setInt(1, admIdtoDel);

				int rowsAffected = deleteStatement.executeUpdate();

				// If deletion is successful
				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Admin deleted successfully!");
					refreshAdmList(); // Refresh the admin list
				} else {
					JOptionPane.showMessageDialog(null, "Failed to delete admin. Admin not found.");
				}
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Search items here
	private void searchItem() {
		try {
			Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

			String searchTerm = serctxtarea.getText();

			if (searchTerm.isEmpty()) {
				// clearTable();
			} else {
				DefaultTableModel model;

				// Search for items
				if (isAdmin(searchTerm)) {
					model = createAdminTableModel();
					searchAdmin(connection, searchTerm, model);
				} else if (isStaff(searchTerm)) {
					model = createStaffTableModel();
					searchStaff(connection, searchTerm, model);
				} else {
					model = createTableModel();
					searchItems(connection, searchTerm, model);
				}

				itemTable.setModel(model);
			}

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private boolean isAdmin(String searchTerm) {
		return searchTerm.contains("admin");
	}

	private boolean isStaff(String searchTerm) {
		return searchTerm.contains("staff");
	}

	private void searchItems(Connection connection, String searchTerm, DefaultTableModel model) throws SQLException {
		String itemQuery = "SELECT * FROM items WHERE id LIKE ? OR category_name LIKE ? OR name LIKE ?";
		try (PreparedStatement itemStatement = connection.prepareStatement(itemQuery)) {
			itemStatement.setString(1, "%" + searchTerm + "%");
			itemStatement.setString(2, "%" + searchTerm + "%");
			itemStatement.setString(3, "%" + searchTerm + "%");

			try (ResultSet itemResultSet = itemStatement.executeQuery()) {
				while (itemResultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(false);
					row.add(itemResultSet.getInt("id"));
					row.add(itemResultSet.getString("category_name"));
					row.add(itemResultSet.getString("name"));
					row.add(itemResultSet.getDouble("price"));
					row.add(itemResultSet.getInt("quantity"));
					model.addRow(row);
				}
			}
		}
	}

	private void searchStaff(Connection connection, String searchTerm, DefaultTableModel model) throws SQLException {
		String staffQuery = "SELECT id, username AS name, password, role FROM users WHERE role='user' AND (id LIKE ? OR username LIKE ?)";
		try (PreparedStatement staffStatement = connection.prepareStatement(staffQuery)) {
			staffStatement.setString(1, "%" + searchTerm + "%");
			staffStatement.setString(2, "%" + searchTerm + "%");

			try (ResultSet staffResultSet = staffStatement.executeQuery()) {
				while (staffResultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(false);
					row.add(staffResultSet.getInt("id"));
					row.add(staffResultSet.getString("name"));
					row.add(staffResultSet.getString("password"));
					row.add(staffResultSet.getString("role"));
					model.addRow(row);
				}
			}
		}
	}

	private void searchAdmin(Connection connection, String searchTerm, DefaultTableModel model) throws SQLException {
		String admQuery = "SELECT id, username AS name, password, role FROM users WHERE role='admin' AND (id LIKE ? OR username LIKE ?)";
		try (PreparedStatement adminStatement = connection.prepareStatement(admQuery)) {
			adminStatement.setString(1, "%" + searchTerm + "%");
			adminStatement.setString(2, "%" + searchTerm + "%");

			try (ResultSet admResultSet = adminStatement.executeQuery()) {
				while (admResultSet.next()) {
					Vector<Object> row = new Vector<>();
					row.add(false);
					row.add(admResultSet.getInt("id"));
					row.add(admResultSet.getString("name"));
					row.add(admResultSet.getString("password"));
					row.add(admResultSet.getString("role"));
					model.addRow(row);
				}
			}
		}
	}

	public class UserDatabaseHandler {

		private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bubbleteapos";
		private static final String JDBC_USER = "root";
		private static final String JDBC_PASSWORD = "root";

		public static void main(String[] args) {
			// Fetch user data from the database
			getUserData();
		}

		private static void getUserData() {
			try {
				Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				// Query to fetch id, username, password, and role from the user table
				String query = "SELECT id, username, password, role FROM users";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query);
						ResultSet resultSet = preparedStatement.executeQuery()) {

					// Iterate through the result set
					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						String username = resultSet.getString("username");
						String password = resultSet.getString("password");
						String role = resultSet.getString("role");

						// Process the retrieved data (You can print or use it as needed)
						System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password
								+ ", Role: " + role);
					}
				}

				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}