import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;

public class menuDetail extends JFrame {

	private JPanel contentPane;
	private JTextField addMenuTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuDetail frame = new menuDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public menuDetail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 762);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel menuimg = new JLabel("");
		menuimg.setBounds(32, 57, 165, 136);
		contentPane.add(menuimg);
		
		JLabel orderlbl = new JLabel("");
		orderlbl.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 27));
		orderlbl.setBounds(10, 203, 366, 56);
		contentPane.add(orderlbl);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(10, 280, 194, 353);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 215, 52);
		panel.add(panel_2);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cup Size");
		lblNewLabel_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel_1.setBounds(22, 10, 152, 32);
		panel_2.add(lblNewLabel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Small");
		rdbtnNewRadioButton.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton.setBounds(25, 70, 103, 37);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnMedium.setBackground(new Color(255, 204, 102));
		rdbtnMedium.setBounds(25, 128, 126, 37);
		panel.add(rdbtnMedium);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Large");
		rdbtnNewRadioButton_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_1.setBounds(25, 192, 103, 37);
		panel.add(rdbtnNewRadioButton_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 204, 102));
		panel_1_1.setBounds(720, 280, 322, 353);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(Color.WHITE);
		panel_2_1_1.setBounds(0, 0, 366, 52);
		panel_1_1.add(panel_2_1_1);
		panel_2_1_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Toppings");
		lblNewLabel_1_1_1_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel_1_1_1_2.setBounds(84, 10, 212, 32);
		panel_2_1_1.add(lblNewLabel_1_1_1_2);
		
		JRadioButton rdbtnNewRadioButton_1_2_1_1 = new JRadioButton("Bubble");
		rdbtnNewRadioButton_1_2_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2_1_1.setBounds(29, 66, 139, 37);
		panel_1_1.add(rdbtnNewRadioButton_1_2_1_1);
		
		JRadioButton rdbtnNewRadioButton_1_2_1_1_1 = new JRadioButton("Milk Form");
		rdbtnNewRadioButton_1_2_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2_1_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2_1_1_1.setBounds(29, 130, 186, 37);
		panel_1_1.add(rdbtnNewRadioButton_1_2_1_1_1);
		
		JRadioButton rdbtnNewRadioButton_1_2_1_1_1_1 = new JRadioButton("Starwberry Jelly");
		rdbtnNewRadioButton_1_2_1_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2_1_1_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2_1_1_1_1.setBounds(29, 196, 280, 37);
		panel_1_1.add(rdbtnNewRadioButton_1_2_1_1_1_1);
		
		JRadioButton rdbtnNewRadioButton_1_2_1_1_1_1_1 = new JRadioButton("Stawberry Pop Balls");
		rdbtnNewRadioButton_1_2_1_1_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2_1_1_1_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2_1_1_1_1_1.setBounds(29, 267, 310, 37);
		panel_1_1.add(rdbtnNewRadioButton_1_2_1_1_1_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(255, 204, 102));
		panel_1_2.setBounds(465, 280, 245, 353);
		contentPane.add(panel_1_2);
		
		JPanel panel_2_1_2 = new JPanel();
		panel_2_1_2.setLayout(null);
		panel_2_1_2.setBackground(Color.WHITE);
		panel_2_1_2.setBounds(0, 0, 245, 52);
		panel_1_2.add(panel_2_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sugar Level");
		lblNewLabel_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel_1_1_1.setBounds(23, 10, 212, 32);
		panel_2_1_2.add(lblNewLabel_1_1_1);
		
		JRadioButton rdbtnLess = new JRadioButton("Less");
		rdbtnLess.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnLess.setBackground(new Color(255, 204, 102));
		rdbtnLess.setBounds(30, 69, 103, 37);
		panel_1_2.add(rdbtnLess);
		
		JRadioButton rdbtnNewRadioButton_1_2 = new JRadioButton("More");
		rdbtnNewRadioButton_1_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2.setBounds(30, 129, 103, 37);
		panel_1_2.add(rdbtnNewRadioButton_1_2);
		
		JRadioButton rdbtnNewRadioButton_1_2_1 = new JRadioButton("Regular");
		rdbtnNewRadioButton_1_2_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNewRadioButton_1_2_1.setBackground(new Color(255, 204, 102));
		rdbtnNewRadioButton_1_2_1.setBounds(30, 197, 139, 37);
		panel_1_2.add(rdbtnNewRadioButton_1_2_1);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(255, 204, 102));
		panel_1_2_1.setBounds(213, 280, 245, 353);
		contentPane.add(panel_1_2_1);
		
		JPanel panel_2_1_2_1 = new JPanel();
		panel_2_1_2_1.setLayout(null);
		panel_2_1_2_1.setBackground(Color.WHITE);
		panel_2_1_2_1.setBounds(0, 0, 245, 52);
		panel_1_2_1.add(panel_2_1_2_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sugar Level");
		lblNewLabel_1_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel_1_1_1_1.setBounds(23, 10, 212, 32);
		panel_2_1_2_1.add(lblNewLabel_1_1_1_1);
		
		JRadioButton rdbtnNoSugar = new JRadioButton("No Sugar");
		rdbtnNoSugar.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNoSugar.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar.setBounds(35, 69, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar);
		
		JRadioButton rdbtnNoSugar_1 = new JRadioButton("100%");
		rdbtnNoSugar_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNoSugar_1.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar_1.setBounds(35, 136, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar_1);
		
		JRadioButton rdbtnNoSugar_1_1 = new JRadioButton("50%");
		rdbtnNoSugar_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNoSugar_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar_1_1.setBounds(35, 205, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar_1_1);
		
		JRadioButton rdbtnNoSugar_1_1_1 = new JRadioButton("25%");
		rdbtnNoSugar_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		rdbtnNoSugar_1_1_1.setBackground(new Color(255, 204, 102));
		rdbtnNoSugar_1_1_1.setBounds(35, 278, 164, 37);
		panel_1_2_1.add(rdbtnNoSugar_1_1_1);
		
		JButton btnNewButton = new JButton("Order");
		btnNewButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 45));
		btnNewButton.setBounds(364, 654, 194, 61);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(465, 110, 577, 157);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		addMenuTxtField = new JTextField();
		addMenuTxtField.setBounds(0, 0, 577, 157);
		panel_1.add(addMenuTxtField);
		addMenuTxtField.setColumns(10);
	}
}
