import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class menuDetail extends JFrame implements ActionListener, ItemListener {
	int flag = 0;
	DataBaseConnection db = new DataBaseConnection();
	Connection con;
	PreparedStatement ps;
	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private ButtonGroup buttonGroup3 = new ButtonGroup();
	JRadioButton Ssize, Msize, Lsize, iceLess, iceMore, iceRegular, rdbtnNoSugar, rdbtnNoSugar100, rdbtnNoSugar50,
			rdbtnNoSugar25;
	JCheckBox chkBubble, chkmilk, chkStraw, chkJelly;
	JLabel sugar, size, toppin, ice;
	String toppings = "", Sugar, Size, Ice, top1 = "", top2 = "", top3 = "", top4 = "";

	JButton btnOrder;
	String Orderimg, lbl, user;
	Integer totalPrice = 0, qty = 0;
	JSpinner qtySpinner;
	ArrayList<OrderData> orderDatArrayList;
	Date dt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuDetail frame = new menuDetail(null, null, 0, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public menuDetail(String img, String menuLbl, Integer price, ArrayList<OrderData> orderList, String username) {
		this.Orderimg = img;
		this.lbl = menuLbl;
		this.totalPrice += price;
		this.user = username;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setLocationRelativeTo(null);
			}
		});
		if (orderList == null) {
			orderDatArrayList = new ArrayList<>();
		} else {
			orderDatArrayList = orderList;
			System.out.println("Size : " + orderDatArrayList.size());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		setBounds(0, 0, screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel menuimg = new JLabel("");
		menuimg.setBackground(new Color(255, 153, 0));
		menuimg.setBounds(89, 44, 165, 136);
		ImageIcon icon = new ImageIcon(img);
		Image Img = icon.getImage();
		Image imgScale = Img.getScaledInstance(menuimg.getWidth(), menuimg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgScale);
		menuimg.setIcon(scaledIcon);
		contentPane.add(menuimg);

		JLabel orderlbl = new JLabel(menuLbl);
		orderlbl.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 27));
		orderlbl.setBounds(89, 200, 194, 49);
		contentPane.add(orderlbl);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(10, 280, 275, 353);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 275, 70);
		panel.add(panel_2);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Cup Size");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 35));
		lblNewLabel_1.setBounds(52, 10, 189, 51);
		panel_2.add(lblNewLabel_1);

		Ssize = new JRadioButton("Small");
		buttonGroup1.add(Ssize);
		Ssize.setBackground(new Color(255, 204, 102));
		Ssize.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		Ssize.setBounds(37, 106, 156, 37);
		panel.add(Ssize);

		Msize = new JRadioButton("Medium");
		buttonGroup1.add(Msize);
		Msize.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		Msize.setBackground(new Color(255, 204, 102));
		Msize.setBounds(37, 183, 178, 37);
		panel.add(Msize);

		Lsize = new JRadioButton("Large");
		buttonGroup1.add(Lsize);
		Lsize.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		Lsize.setBackground(new Color(255, 204, 102));
		Lsize.setBounds(37, 258, 156, 37);
		panel.add(Lsize);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 204, 102));
		panel_1_1.setBounds(1117, 280, 368, 353);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(0, 0, 344, 67);
		panel_1_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Toppings");
		lblNewLabel_1_1_1_2.setFont(new Font("Verdana", Font.BOLD, 35));
		lblNewLabel_1_1_1_2.setBounds(84, 10, 212, 47);
		panel_2_1_1.add(lblNewLabel_1_1_1_2);

		chkBubble = new JCheckBox("Bubble");
		chkBubble.setBackground(new Color(255, 204, 102));
		chkBubble.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		chkBubble.setBounds(6, 86, 224, 36);
		panel_1_1.add(chkBubble);

		chkmilk = new JCheckBox("Milk Form");
		chkmilk.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		chkmilk.setBackground(new Color(255, 204, 102));
		chkmilk.setBounds(6, 149, 224, 36);
		panel_1_1.add(chkmilk);

		chkStraw = new JCheckBox("Strawberry Pop Balls");
		chkStraw.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		chkStraw.setBackground(new Color(255, 204, 102));
		chkStraw.setBounds(6, 213, 362, 36);
		panel_1_1.add(chkStraw);

		chkJelly = new JCheckBox("Jelly");
		chkJelly.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		chkJelly.setBackground(new Color(255, 204, 102));
		chkJelly.setBounds(6, 272, 224, 45);
		panel_1_1.add(chkJelly);

		btnOrder = new JButton("Add");
		btnOrder.setBackground(new Color(255, 204, 102));
		btnOrder.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 35));
		btnOrder.setBounds(510, 690, 194, 61);
		contentPane.add(btnOrder);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(390, 73, 1095, 197);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// Item Show
		size = new JLabel();
		size.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 20));
		size.setBounds(27, 11, 112, 45);
		panel_1.add(size);

		sugar = new JLabel();
		sugar.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 20));
		sugar.setBounds(357, 11, 112, 45);
		panel_1.add(sugar);

		ice = new JLabel();
		ice.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 20));
		ice.setBounds(673, 11, 112, 45);
		panel_1.add(ice);

		toppin = new JLabel();
		toppin.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 20));
		toppin.setBounds(159, 105, 534, 45);
		panel_1.add(toppin);

		// Radio Button Action
		Ssize.addActionListener(this);
		Msize.addActionListener(this);
		Lsize.addActionListener(this);
		// check box Item State change chkBubble.addItemListener(this);
		chkJelly.addItemListener(this);
		chkmilk.addItemListener(this);
		chkStraw.addItemListener(this);
		chkBubble.addItemListener(this);

		// Order Button Action
		btnOrder.addActionListener(this);

		// Action on Radio Button
		Ssize.setActionCommand("Small");
		Msize.setActionCommand("Medium");
		Lsize.setActionCommand("Large");

		qtySpinner = new JSpinner();
		qtySpinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		qtySpinner.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		qtySpinner.setBounds(737, 692, 157, 61);
		contentPane.add(qtySpinner);

		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setBounds(390, 280, 275, 353);
		contentPane.add(panel_1_2_1);
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(255, 204, 102));

		JPanel panel_2_1_2_1 = new JPanel();
		panel_2_1_2_1.setLayout(null);
		panel_2_1_2_1.setBackground(Color.WHITE);
		panel_2_1_2_1.setBounds(0, 0, 275, 70);
		panel_1_2_1.add(panel_2_1_2_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sugar Level");
		lblNewLabel_1_1_1_1.setFont(new Font("Verdana", Font.BOLD, 35));
		lblNewLabel_1_1_1_1.setBounds(23, 10, 242, 50);
		panel_2_1_2_1.add(lblNewLabel_1_1_1_1);

		rdbtnNoSugar = new JRadioButton("No Sugar");
		buttonGroup3.add(rdbtnNoSugar);
		rdbtnNoSugar.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		rdbtnNoSugar.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar.setBounds(36, 85, 205, 37);
		panel_1_2_1.add(rdbtnNoSugar);

		rdbtnNoSugar100 = new JRadioButton("100%");
		buttonGroup3.add(rdbtnNoSugar100);
		rdbtnNoSugar100.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		rdbtnNoSugar100.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar100.setBounds(36, 152, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar100);

		rdbtnNoSugar50 = new JRadioButton("50%");
		buttonGroup3.add(rdbtnNoSugar50);
		rdbtnNoSugar50.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		rdbtnNoSugar50.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar50.setBounds(35, 220, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar50);

		rdbtnNoSugar25 = new JRadioButton("25%");
		buttonGroup3.add(rdbtnNoSugar25);
		rdbtnNoSugar25.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		rdbtnNoSugar25.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar25.setBounds(36, 287, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar25);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(767, 280, 275, 353);
		contentPane.add(panel_1_2);
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(255, 204, 102));

		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.setLayout(null);
		panel_2_1_2.setBackground(Color.WHITE);
		panel_2_1_2.setBounds(0, 0, 275, 70);
		panel_1_2.add(panel_2_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Ice Level");
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.BOLD, 35));
		lblNewLabel_1_1_1.setBounds(47, 11, 202, 49);
		panel_2_1_2.add(lblNewLabel_1_1_1);

		iceLess = new JRadioButton("Less");
		buttonGroup.add(iceLess);
		iceLess.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		iceLess.setBackground(new Color(255, 204, 102));
		iceLess.setBounds(42, 98, 103, 37);
		panel_1_2.add(iceLess);

		iceMore = new JRadioButton("More");
		buttonGroup.add(iceMore);
		iceMore.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		iceMore.setBackground(new Color(255, 204, 102));
		iceMore.setBounds(42, 170, 103, 37);
		panel_1_2.add(iceMore);

		iceRegular = new JRadioButton("Regular");
		buttonGroup.add(iceRegular);
		iceRegular.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		iceRegular.setBackground(new Color(255, 204, 102));
		iceRegular.setBounds(43, 244, 179, 37);
		panel_1_2.add(iceRegular);

		JButton backBtn = new JButton("");
		backBtn.setBackground(new Color(255, 153, 51));
		backBtn.setIcon(new ImageIcon("C:\\Users\\Hsu\\Downloads\\icons8-back-button-64.png"));
		backBtn.setBounds(1416, 10, 72, 61);
		contentPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				order_view orderViewPage = new order_view(orderList, username);
				orderViewPage.setVisible(true);
				dispose();
			}
		});

		iceLess.addActionListener(this);
		iceMore.addActionListener(this);
		iceRegular.addActionListener(this);
		rdbtnNoSugar.addActionListener(this);
		rdbtnNoSugar100.addActionListener(this);
		rdbtnNoSugar50.addActionListener(this);
		rdbtnNoSugar25.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		qty = (int) qtySpinner.getValue();

		if (e.getSource().equals(Ssize)) {
			size.setText(Ssize.getText());
			Size = Ssize.getText();
		}
		if (e.getSource().equals(Msize)) {
			size.setText(Msize.getText());
			Size = Msize.getText();
		}
		if (e.getSource().equals(Lsize)) {
			size.setText(Lsize.getText());
			Size = Lsize.getText();
		}
		if (e.getSource().equals(iceLess)) {
			ice.setText(iceLess.getText());
			Ice = iceLess.getText();
		}
		if (e.getSource().equals(iceMore)) {
			ice.setText(iceMore.getText());
			Ice = iceMore.getText();
		}
		if (e.getSource().equals(iceRegular)) {
			ice.setText(iceRegular.getText());
			Ice = iceRegular.getText();
		}
		if (e.getSource().equals(rdbtnNoSugar)) {
			sugar.setText(rdbtnNoSugar.getText());
			Sugar = rdbtnNoSugar.getText();
		}
		if (e.getSource().equals(rdbtnNoSugar100)) {
			sugar.setText(rdbtnNoSugar100.getText());
			Sugar = rdbtnNoSugar100.getText();
		}
		if (e.getSource().equals(rdbtnNoSugar50)) {
			sugar.setText(rdbtnNoSugar50.getText());
			Sugar = rdbtnNoSugar50.getText();
		}
		if (e.getSource().equals(rdbtnNoSugar25)) {
			sugar.setText(rdbtnNoSugar25.getText());
			Sugar = rdbtnNoSugar25.getText();
		}

//
//		// For Price (Topppings)
//		

		if (e.getSource() == btnOrder) {
			boolean Selected = false;

			// Check the first button group
			if (buttonGroup != null) {
				if (buttonGroup.getSelection() == null) {
					Selected = true;
				}
			}

			// Check the second button group
			if (buttonGroup1 != null) {
				if (buttonGroup1.getSelection() == null) {
					Selected = true;
				} else {
					String selectedSize = buttonGroup1.getSelection().getActionCommand();
					if (selectedSize == "Small") {
						totalPrice += 0;
					}
					if (selectedSize == "Medium") {
						totalPrice += 500;
					}
					if (selectedSize == "Large") {
						totalPrice += 1000;
					}
				}
			}

			// Check the fourth button group
			if (buttonGroup3 != null) {
				if (buttonGroup3.getSelection() == null) {
					Selected = true;
				}
			}

			if (Selected) {
				JOptionPane.showMessageDialog(this, "Choose at least one option in each group", "Alert",
						JOptionPane.INFORMATION_MESSAGE);
			}

			else {
				int option = JOptionPane.showConfirmDialog(menuDetail.this, "Are you sure to add ?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					OrderData orderData = new OrderData(lbl, Ice, Sugar, Size, totalPrice * qty, qty, toppings, user);
					orderDatArrayList.add(orderData);
					order_view ov = new order_view(orderDatArrayList, null);
					ov.setVisible(true);
					dispose();

				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(chkJelly)) {
			if (chkJelly.isSelected()) {
				top4 = chkJelly.getText() + " | ";
				flag = 1;

			} else {
				flag = 0;
				top4 = "";
			}

		}
		if (e.getSource().equals(chkmilk)) {
			if (chkmilk.isSelected()) {
				top2 = chkmilk.getText() + " | ";
				flag = 1;
			} else {
				flag = 0;
				top2 = "";

			}

		}
		if (e.getSource().equals(chkBubble)) {
			if (chkBubble.isSelected()) {
				top1 = chkBubble.getText() + " | ";
				flag = 1;
			} else {
				flag = 0;
				top1 = "";

			}

		}
		if (e.getSource().equals(chkStraw)) {
			if (chkStraw.isSelected()) {
				top3 = chkStraw.getText() + " | ";
				flag = 1;
			} else {
				flag = 0;
				top3 = "";
			}

		}
		if (flag == 1) {
			totalPrice += 700;
		}
		if (flag == 0) {
			totalPrice -= 700;
		}
		toppings = top1 + top2 + top3 + top4;
		toppin.setText(toppings);

	}
}