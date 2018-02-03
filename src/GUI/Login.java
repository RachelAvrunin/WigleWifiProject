package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import SQL.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IPtxt;
	private JTextField dbNametxt;
	private JTextField Usertxt;
	private JTextField Passtxt;
	private JTextField Porttxt;
	private JTextField Tabletxt;
	private String ip, port, user, pass, tableName, dbName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 478);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIp = new JLabel("IP :");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIp.setBounds(75, 35, 37, 44);
		contentPane.add(lblIp);

		IPtxt = new JTextField();
		IPtxt.setBounds(124, 42, 193, 32);
		contentPane.add(IPtxt);
		IPtxt.setColumns(10);

		JLabel lblUrl = new JLabel("dbName :");
		lblUrl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUrl.setBounds(31, 264, 108, 44);
		contentPane.add(lblUrl);

		dbNametxt = new JTextField();
		dbNametxt.setColumns(10);
		dbNametxt.setBounds(124, 271, 193, 32);
		contentPane.add(dbNametxt);

		JLabel lblUser = new JLabel("  User :");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(49, 153, 83, 44);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(21, 210, 91, 44);
		contentPane.add(lblPassword);

		Usertxt = new JTextField();
		Usertxt.setColumns(10);
		Usertxt.setBounds(124, 160, 193, 32);
		contentPane.add(Usertxt);

		Passtxt = new JTextField();
		Passtxt.setColumns(10);
		Passtxt.setBounds(124, 217, 193, 32);
		contentPane.add(Passtxt);

		JLabel lblPort = new JLabel("Port : ");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPort.setBounds(61, 92, 69, 44);
		contentPane.add(lblPort);

		Porttxt = new JTextField();
		Porttxt.setColumns(10);
		Porttxt.setBounds(124, 100, 193, 32);
		contentPane.add(Porttxt);

		JLabel lblTablename = new JLabel("  TableName :");
		lblTablename.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTablename.setBounds(0, 320, 112, 44);
		contentPane.add(lblTablename);

		Tabletxt = new JTextField();
		Tabletxt.setColumns(10);
		Tabletxt.setBounds(124, 327, 193, 32);
		contentPane.add(Tabletxt);

		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!IPtxt.getText().isEmpty() && !Porttxt.getText().isEmpty() && !dbNametxt.getText().isEmpty()
						&& !Usertxt.getText().isEmpty() && !Passtxt.getText().isEmpty()
						&& !Tabletxt.getText().isEmpty()) {
					ip = IPtxt.getText();
					port = Porttxt.getText();
					dbName = dbNametxt.getText();
					user = Usertxt.getText();
					pass = Passtxt.getText();
					tableName = Tabletxt.getText();

					Connecting con = new Connecting(ip, port, user, pass, tableName, dbName);
					mySQL sql = new mySQL();
					GUI.L1.DataBase.Lines = sql.ReadTable(con);

					if (!GUI.L1.DataBase.Lines.isEmpty()) {
						GUI.ClearButton.setEnabled(true);
						GUI.SaveCSV.setEnabled(true);
						GUI.SaveKMLButton.setEnabled(true);
						GUI.textArea.append("Path: SQL " + "\n");
						GUI.textArea.append(" Data Size : " + GUI.L1.DataBase.Lines.size() + "\n");
						GUI.textArea.append(" # of Mac : " + GUI.MacCount(GUI.L1.DataBase.Lines) + "\n");
						GUI.EnterSQLButton.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Done !");
						dispose();

					}

					else
						GUI.textArea.append("No data in SQL" + "\n");

				}

				else
					JOptionPane.showMessageDialog(null, "Invalid Input !");
			}

		});
		LoginButton.setBackground(new Color(211, 211, 211));
		LoginButton.setBounds(174, 382, 97, 25);
		LoginButton.setEnabled(true);
		contentPane.add(LoginButton);
		setLocationRelativeTo(null);
	}
}
