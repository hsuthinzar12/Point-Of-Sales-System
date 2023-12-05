import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class menuData extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuData frame = new menuData();
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
	public menuData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuTabs = new JPanel();
		menuTabs.setBounds(127, 78, 215, 202);
		contentPane.add(menuTabs);
		menuTabs.setLayout(null);
		
		JButton menuBtn = new JButton("");
		menuBtn.setBounds(10, 10, 195, 135);
		menuTabs.add(menuBtn);
		
		JLabel menulbl = new JLabel("");
		menulbl.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
		menulbl.setBounds(10, 155, 195, 37);
		menuTabs.add(menulbl);
	}
}
