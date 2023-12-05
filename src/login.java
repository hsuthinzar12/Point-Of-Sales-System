import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField txtUsr;
    private JTextField txtPsw;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 153, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(760, 10, 1, 1);
        panel.setLayout(null);
        panel.setBackground(new Color(255, 204, 102));
        contentPane.add(panel);
        
        JLabel lblNewLabel_2 = new JLabel("User Name : ");
        lblNewLabel_2.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 25));
        lblNewLabel_2.setBounds(66, 141, 164, 56);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Password : ");
        lblNewLabel_2_1.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 25));
        lblNewLabel_2_1.setBounds(77, 249, 141, 56);
        panel.add(lblNewLabel_2_1);
        
        textField = new JTextField();
        textField.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 20));
        textField.setColumns(10);
        textField.setBackground(new Color(255, 255, 153));
        textField.setBounds(312, 148, 210, 34);
        panel.add(textField);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("UD Digi Kyokasho N-R", Font.BOLD, 20));
        textField_1.setColumns(10);
        textField_1.setBackground(new Color(255, 255, 153));
        textField_1.setBounds(312, 257, 210, 34);
        panel.add(textField_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Have a good day !");
        lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblNewLabel_1_1.setBounds(166, 10, 248, 76);
        panel.add(lblNewLabel_1_1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 204, 102));
        panel_1.setBounds(508, 264, 530, 421);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel usrlbl = new JLabel("User ID : ");
        usrlbl.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 25));
        usrlbl.setBounds(100, 101, 189, 50);
        panel_1.add(usrlbl);
        
        JLabel pswlbl = new JLabel("Password  : ");
        pswlbl.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 25));
        pswlbl.setBounds(64, 220, 167, 50);
        panel_1.add(pswlbl);
        
        txtUsr = new JTextField();
        txtUsr.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 23));
        txtUsr.setBounds(284, 110, 189, 34);
        panel_1.add(txtUsr);
        txtUsr.setColumns(10);
        
        txtPsw = new JTextField();
        txtPsw.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 23));
        txtPsw.setColumns(10);
        txtPsw.setBounds(284, 220, 189, 34);
        panel_1.add(txtPsw);
        
        JLabel lblHaveAGood = new JLabel("Have a good day ! ");
        lblHaveAGood.setFont(new Font("BIZ UDPGothic", Font.PLAIN, 25));
        lblHaveAGood.setBounds(151, 10, 273, 50);
        panel_1.add(lblHaveAGood);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 25));
        btnNewButton.setBounds(173, 344, 194, 50);
        panel_1.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Hsu\\eclipse-workspace\\JavaBasics\\images\\CatLogo.png"));
        lblNewLabel.setBounds(746, 57, 72, 80);
        contentPane.add(lblNewLabel);
        
        JLabel lblHeyU = new JLabel("Hey U ");
        lblHeyU.setFont(new Font("UD Digi Kyokasho N-B", Font.BOLD, 45));
        lblHeyU.setBounds(724, 132, 143, 65);
        contentPane.add(lblHeyU);
        
        JLabel lblBubbleMilkTea = new JLabel("Bubble Milk Tea");
        lblBubbleMilkTea.setFont(new Font("BIZ UDPGothic", Font.PLAIN, 25));
        lblBubbleMilkTea.setBounds(679, 204, 235, 50);
        contentPane.add(lblBubbleMilkTea);
    }
}
