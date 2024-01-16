import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;

public class order_view extends JFrame implements ActionListener {

	DataBaseConnection db = new DataBaseConnection();
	Connection con;
	Statement st;
	PreparedStatement pst;
	private JPanel contentPane;
	private JPanel CPanel;
	private JPanel IPanel;
	private JLabel lblDate;
	private JLabel lblLoginUsr;
	public String img, lbl, itemName;
	private String username;
	private JTable orderViewTable = new JTable();
	private JButton btnOrder, btnReset, btnRemove;
	private DefaultTableModel model = (DefaultTableModel) orderViewTable.getModel();
	ArrayList<OrderData> orderArrayList;
	Object oData;
	private JPanel panel_1;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order_view frame = new order_view(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public order_view(ArrayList<OrderData> orderList, String username) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				refreshOrderTable();
			}
		});
		orderArrayList = orderList;
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel order_Panel = new JPanel();
		order_Panel.setBounds(997, 97, 525, 564);
		order_Panel.setBackground(new Color(255, 204, 102));
		contentPane.add(order_Panel);
		order_Panel.setLayout(null);

		// Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 525, 564); // Adjust the bounds
		order_Panel.add(scrollPane);

		lblLoginUsr = new JLabel("Welcome, " + username);
		lblLoginUsr.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 50));
		lblLoginUsr.setBounds(38, 12, 400, 59); // Adjust the bounds as needed
		contentPane.add(lblLoginUsr);

		scrollPane.setViewportView(orderViewTable);
		scrollPane.setPreferredSize(new Dimension(343, 452));
		orderViewTable.setRowHeight(30);
		orderViewTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 20));
		orderViewTable.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 17));
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Cup-Size", "Sugar/Ice Level", "Toppings", "Quantity", "Total " });
		orderViewTable.setModel(model);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1522, 87);
		panel.setBackground(new Color(255, 153, 0));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLoginUsr = new JLabel("");
		lblLoginUsr.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 50));
		lblLoginUsr.setBounds(38, 12, 174, 59);
		panel.add(lblLoginUsr);

		lblDate = new JLabel();
		lblDate.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		lblDate.setBounds(1008, 23, 174, 43);
		panel.add(lblDate);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		btnLogOut.setBounds(1360, 23, 152, 43);
		panel.add(btnLogOut);

		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(order_view.this, "Are you sure you want to logout?",
						"Logout", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					dispose();
					login loginFrame = new login();
					loginFrame.setVisible(true);
				}
			}
		});	
		CPanel = new JPanel();
		CPanel.setBounds(10, 97, 227, 720);
		CPanel.setBackground(new Color(255, 204, 102));
		contentPane.add(CPanel);
		CPanel.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 227, 79);
		CPanel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("Categories");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel.setBounds(27, 10, 174, 59);
		panel_4.add(lblNewLabel);

		List<String> categories = fetchCategories();

		int buttonY = 89; // Initial Y position for buttons
		for (String category : categories) {
			JButton categoryBtn = new JButton(category);
			categoryBtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
			categoryBtn.setBackground(new Color(255, 153, 0));
			categoryBtn.setBounds(0, buttonY, 227, 68);
			CPanel.add(categoryBtn);

			// Adding action listener dynamically for each category button
			categoryBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					loadCategoryItems(category, IPanel, username);
				}
			});

			buttonY += 78; // Increase Y position for the next button
		}

		IPanel = new JPanel();
		IPanel.setBounds(247, 100, 740, 717);
		IPanel.setBackground(new Color(255, 204, 102));
		contentPane.add(IPanel);
		IPanel.setLayout(new GridLayout(0, 2));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(997, 671, 525, 111);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnOrder = new JButton("Order");
		btnOrder.setBounds(10, 28, 139, 73);
		panel_1.add(btnOrder);
		btnOrder.setBackground(new Color(255, 153, 0));
		btnOrder.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));

		btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(255, 153, 0));
		btnReset.setBounds(169, 28, 151, 73);
		panel_1.add(btnReset);
		btnReset.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));

		btnRemove = new JButton("Remove");
		btnRemove.setBackground(new Color(255, 153, 0));
		btnRemove.setBounds(342, 28, 173, 73);
		panel_1.add(btnRemove);
		btnRemove.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnRemove.addActionListener(this);
		btnReset.addActionListener(this);

		btnOrder.addActionListener(this);
		setCurrentDateTime();

	}

	private void refreshOrderTable() {

		// Clear existing rows
		model.setRowCount(0);

		// Iterate through the list and add orders to the table
		if (orderArrayList != null) {
			for (OrderData order : orderArrayList) {
				Object[] rowData = new Object[orderViewTable.getColumnCount()];
				rowData[0] = order.getNameString();
				rowData[1] = order.getSizeString();
				rowData[2] = order.getSugarString() + "/" + order.getIceString();
				rowData[3] = order.getToppingString();
				rowData[4] = order.getQuantitiy();
				rowData[5] = order.getTotal();

				model.addRow(rowData);
			}
		}

	}

	public Integer getPrice() {
		int price = 0;
		String query = "SELECT price FROM items WHERE name = ?";

		try {
			con = db.getConnect();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, itemName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				price = rs.getInt("price");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return price;

	}

	private void setCurrentDateTime() {
		// Get current date and time from the device's clock
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Format date and time
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		String formattedDate = dateFormatter.format(currentDateTime);
		String formattedTime = timeFormatter.format(currentDateTime);

		lblDate.setText(formattedDate);
	}

	// Categories items fetch from database
	private List<String> fetchCategories() {
		List<String> categories = new ArrayList<>();
		try {
			Connection connection = new DataBaseConnection().getConnect();
			String query = "SELECT DISTINCT category_name FROM items";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					categories.add(resultSet.getString("category_name"));
				}
			}
			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return categories;
	}

	private void loadCategoryItems(String category, JPanel panel, String user) {

		try {
			Connection con = new DataBaseConnection().getConnect();
			PreparedStatement pst = con.prepareStatement("select image, name from items where category_name = ?");
			pst.setString(1, category);
			ResultSet rs = pst.executeQuery();
			IPanel.removeAll();
			while (rs.next()) {
				JButton button = new JButton();
				JLabel b = new JLabel(rs.getString(2));
				int newSize = 20;
				Font labelFont = b.getFont();
				b.setFont(new Font(labelFont.getFontName(), Font.BOLD, newSize));
				JPanel p = new JPanel();
				button.setIcon(resizeImage(rs.getString(1), 80, 80));
				p.setBounds(10, 20, 20, 20);
				p.setLayout(new GridLayout(3, 2));
				p.add(button);
				p.add(b);
				IPanel.add(p);
				String img1 = rs.getString(1);
				itemName = rs.getString(2);
				String itm = rs.getString(2);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						menuDetail menuDeatil = new menuDetail(img1, itm, getPrice(), orderArrayList, user);
						menuDeatil.setVisible(true);
						dispose();
					}

				});
			}
			IPanel.revalidate();
			IPanel.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ImageIcon resizeImage(String imagePath, int width, int height) {
		try {
			Image img = new ImageIcon(imagePath).getImage();
			Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImg);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("Error loading image: " + imagePath);
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnOrder) {

			if (orderViewTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(order_view.this, "Please add items to the order before proceeding.");
				return; // Stop further processing
			}
			int option = JOptionPane.showConfirmDialog(order_view.this, "Are you sure to order ?", "Confirmation",
					JOptionPane.YES_NO_OPTION);

			if (option == JOptionPane.YES_OPTION) {

				String query = "INSERT INTO orders (order_name, cup_size, suger_ice,toppings,quantity, total_amount, sale_date) VALUES (?, ?, ?, ?, ?, ?,CURDATE())";
				int rowCount = orderViewTable.getRowCount();

				try {
					con = db.getConnect();
					pst = con.prepareStatement(query);
					int total = 0;
					for (int j = 0; j < rowCount; j++) {
						pst.setString(1, (String) model.getValueAt(j, 0));
						pst.setString(2, (String) model.getValueAt(j, 1));
						pst.setString(3, (String) model.getValueAt(j, 2));
						pst.setString(4, (String) model.getValueAt(j, 3));
						pst.setInt(5, (int) model.getValueAt(j, 4));
						pst.setInt(6, (int) model.getValueAt(j, 5));
						total += (int) model.getValueAt(j, 5);
						pst.executeUpdate();
					}

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				String voucherContent = generateVoucherContent();
				Object[] options = { "OK", "Print" };
				int selection = JOptionPane.showOptionDialog(order_view.this, voucherContent, "Order Voucher",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				if (selection == 1) {
					JOptionPane.showMessageDialog(order_view.this, "Loading...");

					PrinterJob job = PrinterJob.getPrinterJob();
					if (job.printDialog()) {
						try {
							job.print();
							JOptionPane.showMessageDialog(order_view.this, "Printing completed successfully!");
						} catch (PrinterException er) {
							er.printStackTrace();
							JOptionPane.showMessageDialog(order_view.this, "Failed to print: " + er.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(order_view.this, "Printing cancelled!!!");
					}
				}

			}
			// Clear the order table after the order is completed
			clearOrderTable();
		}

		else if (e.getSource() == btnReset) {
			int delete = JOptionPane.showConfirmDialog(order_view.this, "Are you sure you want to reset all ?",
					"Confirmation", JOptionPane.YES_NO_OPTION);
			if (delete == JOptionPane.YES_OPTION) {
				if (orderViewTable.getRowCount() > 0) {
					// Clear all rows from the table model
					model.setRowCount(0);
					orderArrayList.clear();

					JOptionPane.showMessageDialog(order_view.this, "All items reset successfully!");
				} else {
					JOptionPane.showMessageDialog(order_view.this, "There are no items to reset.");
				}
			}
		} else if (e.getSource() == btnRemove) {
			int selectedRow = orderViewTable.getSelectedRow();
			if (selectedRow != -1) {
				model.removeRow(selectedRow);
				if (orderArrayList != null && orderArrayList.size() > selectedRow) {
					orderArrayList.remove(selectedRow);
				}
				JOptionPane.showMessageDialog(order_view.this, "Selected item removed successfully!");
			} else {
				JOptionPane.showMessageDialog(order_view.this, "Please select an item to remove.");
			}
		}
	}

	private void clearOrderTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0); // Clear all rows from the table model
		orderArrayList.clear(); // Optionally, clear the orderArrayList as well
	}

	private String generateVoucherContent() {
		StringBuilder voucherContent = new StringBuilder();

		voucherContent.append("HEY U\n");
		voucherContent.append("Bubble Tea And Snacks\n");
		voucherContent.append("-----------------------------\n");
		voucherContent.append("Username: ").append(orderArrayList.get(0).getUsername()).append("\n");
		voucherContent.append("Date: ").append(lblDate.getText()).append("\n");
		voucherContent.append("------------------------------\n");

		if (orderArrayList != null && !orderArrayList.isEmpty()) {
			for (OrderData order : orderArrayList) {

				voucherContent.append("Item: ").append(order.getNameString()).append("\n");
				voucherContent.append("Cup-Size: ").append(order.getSizeString()).append("\n");
				voucherContent.append("Sugar/Ice Level: ").append(order.getSugarString()).append("/")
						.append(order.getIceString()).append("\n");
				voucherContent.append("Toppings: ").append(order.getToppingString()).append("\n");
				voucherContent.append("Quantity: ").append(order.getQuantitiy()).append("\n");
				voucherContent.append("Total: ").append(order.getTotal()).append("\n");
				voucherContent.append("---------------------------\n");
			}
		}

		// Add total amount
		voucherContent.append("Total Amount: ").append(calculateTotalAmount()).append("\n");

		return voucherContent.toString();
	}

	private int generateOrderId() {
		int orderId = 1;
		return orderId;
	}

	private int calculateTotalAmount() {
		int total = 0;
		for (OrderData order : orderArrayList) {
			total += order.getTotal();
		}
		return total;
	}
}