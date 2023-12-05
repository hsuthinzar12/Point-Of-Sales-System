import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class order_view extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order_view frame = new order_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ImageIcon resizeImage(String imagePath, int width, int height) {
		try {
			Image img = ImageIO.read(new File(imagePath));
			Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImg);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null; // Return null in case of an error
		}
	}

	public order_view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height); // Set frame size dynamically
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 153, 0));
		panel.setBounds(0, 0, 1522, 87); // Adjusting width to fit screen
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblHeyU = new JLabel("HEY U ");
		lblHeyU.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 50));
		lblHeyU.setBounds(39, 10, 174, 59);
		panel.add(lblHeyU);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 102));
		panel_1.setBounds(10, 97, 227, 720);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 227, 79);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("Categories");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		lblNewLabel.setBounds(27, 10, 174, 59);
		panel_4.add(lblNewLabel);

		JButton sodaBtn = new JButton("Soda ");
		sodaBtn.setBackground(new Color(255, 153, 0));
		sodaBtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		sodaBtn.setBounds(0, 89, 227, 68);
		panel_1.add(sodaBtn);

		JButton dodoBtn = new JButton("Do Do");
		dodoBtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		dodoBtn.setBackground(new Color(255, 153, 0));
		dodoBtn.setBounds(0, 167, 227, 68);
		panel_1.add(dodoBtn);

		JButton bubbleBtn = new JButton("Bubble Tea");
		bubbleBtn.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		bubbleBtn.setBackground(new Color(255, 153, 0));
		bubbleBtn.setBounds(0, 245, 227, 68);
		panel_1.add(bubbleBtn);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 102));
		panel_2.setBounds(247, 100, 740, 717);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btn1 = new JButton("");
		btn1.setBounds(24, 10, 177, 153);
		panel_2.add(btn1);

		JButton btn2 = new JButton("");
		btn2.setBounds(285, 10, 177, 153);
		panel_2.add(btn2);

		JButton btn3 = new JButton("");
		btn3.setBounds(541, 10, 177, 153);
		panel_2.add(btn3);

		JButton btn4 = new JButton("");
		btn4.setBounds(24, 252, 177, 153);
		panel_2.add(btn4);

		JButton btn5 = new JButton("");
		btn5.setBounds(285, 252, 177, 153);
		panel_2.add(btn5);

		JButton btn6 = new JButton("");
		btn6.setBounds(541, 252, 177, 153);
		panel_2.add(btn6);

		JButton btn7 = new JButton("");
		btn7.setBounds(24, 499, 177, 153);
		panel_2.add(btn7);

		JButton btn8 = new JButton("");
		btn8.setBounds(285, 499, 177, 153);
		panel_2.add(btn8);

		JButton btn9 = new JButton("");
		btn9.setBounds(541, 499, 177, 153);
		panel_2.add(btn9);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 204, 102));
		panel_3.setBounds(997, 97, 515, 720);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("Order");
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 33));
		btnNewButton.setBounds(0, 624, 202, 68);
		panel_3.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 153, 0));
		btnCancel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnCancel.setBounds(313, 625, 202, 68);
		panel_3.add(btnCancel);

		// For Soda button
		sodaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btn1.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn2.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn3.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn4.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn5.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn6.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn7.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn8.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));
				btn9.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-espresso-64.png"));

			}
		});

		// For DoDo button
		dodoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btn1.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn2.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn3.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn4.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn5.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn6.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn7.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn8.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));
				btn9.setIcon(
						new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\icons8-latte-100.png"));

			}
		});

		// For milk tea button
		bubbleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int width = 100;
				int height = 100;
				btn1.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\brownsugar.jpg",
						width, height));
				btn2.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\caramel.jpg", width,
						height));
				btn3.setIcon(resizeImage(
						"C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\Cold-Bubble-Tea-PNG-Photos.png",
						width, height));
				btn4.setIcon(
						resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\freshmilkgreentea.png",
								width, height));
				btn5.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\brownsugar.jpg",
						width, height));
				btn6.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\caramel.jpg", width,
						height));
				btn7.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\brownsugar.jpg",
						width, height));
				btn8.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\caramel.jpg", width,
						height));
				btn9.setIcon(resizeImage("C:\\Users\\Hsu\\eclipse-workspace\\JavaProject\\prj_img\\brownsugar.jpg",
						width, height));

			}
		});

		// action listener for each button
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				menuDetail menudata = new menuDetail();
				menudata.setVisible(true);
			}
		});
		
		
	}
}
