import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class chatbot extends JFrame {
	public chatbot() {
	}
	private static JTextArea textArea;
	private static JPanel contentPane;
	private static JTextField textField;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;


	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatbot frame = new chatbot();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		new Thread(new Runnable() {
			
		public void run() {
		try {
			
		
			s = new Socket("localhost",1204);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String input = "";
			while(true) {
				input = din.readUTF();
				textArea.append(" \n Server:\t"+ input + "\n");
				textArea.getCaret().setDot(Integer.MAX_VALUE);
				
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
		
		}
	/**
	 * Create the frame.
	 */
	public chatbot() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(249, 0, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 413, 231, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("send");
		btnNewButton_1.setBounds(249, 412, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 25, 318, 377);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
	}
}
