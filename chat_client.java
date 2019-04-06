import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class chat_client extends JFrame {

	private JPanel contentPane;
	private static JTextField msg_area;
	private JTextField msg_text;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_client frame = new chat_client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			s = new Socket("localhost",1201);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			while(!msgin.equals("exit")) {
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim()+" \n Server:\t"+msgin + "\n");
				
			}
		}catch(Exception e) {
			
		}
	}

	/**
	 * Create the frame.
	 */
	public chat_client() {
		super("CLIENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msg_area = new JTextField();
		msg_area.setBackground(new Color(255, 255, 255));
		msg_area.setBounds(10, 11, 280, 396);
		contentPane.add(msg_area);
		msg_area.setColumns(10);
		msg_area.setEditable(false);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 432, 216, 20);
		contentPane.add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("SEND");
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			try {
				String msgout = "";
				msgout = msg_text.getText().trim();
				dout.writeUTF(msgout);
				msg_text.setText("");
				}
			catch(Exception e) {
				
			}
			
			}
		});
		msg_send.setBounds(230, 431, 60, 23);
		contentPane.add(msg_send);
	}

}
