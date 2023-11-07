package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class UserForm {

	protected JFrame frame;
	private JTextField usernameInput;
	private JPasswordField Inputpassword;

	
    protected Map<String, String> userDatabase = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm window = new UserForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Productivity Manager Login");
		frame.setBounds(100, 100, 739, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserName.setBounds(134, 102, 139, 44);
		panel.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPassword.setBounds(134, 178, 139, 44);
		panel.add(lblPassword);
		
		usernameInput = new JTextField();
		usernameInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameInput.setBounds(269, 102, 283, 34);
		panel.add(usernameInput);
		usernameInput.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

				try {
					// Open the RegistrationFrame and hide the Signin frame(frame)
					RegistrationForm window = new RegistrationForm(frame, userDatabase);             
                window.Registration.setVisible(true);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setBackground(Color.CYAN);
		btnSignUp.setForeground(new Color(0, 0, 153));
		btnSignUp.setFont(new Font("Cambria", Font.PLAIN, 17));
		btnSignUp.setBounds(416, 294, 172, 49);
		panel.add(btnSignUp);
		
		Inputpassword = new JPasswordField();
		Inputpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Inputpassword.setBounds(265, 185, 287, 34);
		panel.add(Inputpassword);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = new String(Inputpassword.getPassword());
                
                if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Logged in successfully!");
                    // add the code to open the main app window.
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please try again.");
                }
			}
		});
		btnSignIn.setForeground(new Color(0, 0, 153));
		btnSignIn.setFont(new Font("Cambria", Font.PLAIN, 17));
		btnSignIn.setBackground(Color.CYAN);
		btnSignIn.setBounds(149, 294, 172, 49);
		panel.add(btnSignIn);
		
		JLabel lblSignUp = new JLabel("Don't have an account? \r\n");
		lblSignUp.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblSignUp.setBounds(430, 250, 172, 31);
		panel.add(lblSignUp);
	}
}
