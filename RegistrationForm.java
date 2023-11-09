package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class RegistrationForm extends UserForm{

	public JFrame Registration;
	private JTextField regInputLName;
	private JTextField textField;
	private JTextField regInputDOB;
	private JTextField regInputUserName;
	private JTextField regInputPw;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistrationForm window = new RegistrationForm();
//					window.Registration.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param userDatabase 
	 * @param frame 
	 */
	public RegistrationForm(JFrame frame, Map<String, String> userDatabase) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Registration = new JFrame();
		Registration.setBackground(SystemColor.activeCaption);
		Registration.setBounds(100, 100, 450, 300);
		Registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Registration.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your information");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(128, 11, 220, 25);
		Registration.getContentPane().add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblFirstName.setBounds(47, 60, 220, 14);
		Registration.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblLastName.setBounds(47, 96, 220, 14);
		Registration.getContentPane().add(lblLastName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(47, 132, 220, 14);
		Registration.getContentPane().add(lblDateOfBirth);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblUsername.setBounds(47, 169, 220, 14);
		Registration.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblPassword.setBounds(47, 204, 220, 14);
		Registration.getContentPane().add(lblPassword);
		
		regInputLName = new JTextField();
		regInputLName.setBounds(171, 96, 166, 20);
		Registration.getContentPane().add(regInputLName);
		regInputLName.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(171, 60, 166, 20);
		Registration.getContentPane().add(textField);
		
		regInputDOB = new JTextField();
		regInputDOB.setColumns(10);
		regInputDOB.setBounds(171, 132, 166, 20);
		Registration.getContentPane().add(regInputDOB);
		
		regInputUserName = new JTextField();
		regInputUserName.setColumns(10);
		regInputUserName.setBounds(171, 169, 166, 20);
		Registration.getContentPane().add(regInputUserName);
		
		regInputPw = new JTextField();
		regInputPw.setColumns(10);
		regInputPw.setBounds(171, 204, 166, 20);
		Registration.getContentPane().add(regInputPw);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Registration logic - Add user to the userDatabase
				// store DOB and other information as necessary, only the username and password is stored in the login page 
				String username = regInputUserName.getText();
                String password = regInputPw.getText();

                
                if (userDatabase.containsKey(username)) {
                    JOptionPane.showMessageDialog(frame, "Account already exists. Please login.");
                } else {
                    userDatabase.put(username, password);
                    JOptionPane.showMessageDialog(Registration, "Account created successfully!");
                    Registration.dispose(); // Close the registration frame
                    frame.setVisible(true); // Show the Sign in frame
                }
			}
		});
		btnNewButton.setForeground(SystemColor.activeCaption);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnNewButton.setBounds(337, 235, 89, 23);
		Registration.getContentPane().add(btnNewButton);
	}

}
