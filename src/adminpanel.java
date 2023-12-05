import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminpanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminpanel frame = new adminpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 662);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 994, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Menu");
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnNewButton.setBounds(0, 0, 146, 45);
		panel.add(btnNewButton);
		
		JButton btnStaffs = new JButton("Orders");
		btnStaffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStaffs.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnStaffs.setBackground(new Color(255, 204, 102));
		btnStaffs.setBounds(156, 0, 146, 45);
		panel.add(btnStaffs);
		
		JButton btnNewButton_1_1 = new JButton("Staffs");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnNewButton_1_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1_1.setBounds(313, 0, 146, 45);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Admin");
		btnNewButton_1_1_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 30));
		btnNewButton_1_1_1.setBackground(new Color(255, 204, 102));
		btnNewButton_1_1_1.setBounds(469, 0, 146, 45);
		panel.add(btnNewButton_1_1_1);
	}
}
