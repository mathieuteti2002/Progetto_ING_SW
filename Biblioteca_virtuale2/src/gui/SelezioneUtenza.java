package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelezioneUtenza extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelezioneUtenza frame = new SelezioneUtenza();
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
	public SelezioneUtenza() {
		String pass="abc";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scegliere il tipo di Utenza");
		lblNewLabel.setBounds(138, 50, 368, 14);
		contentPane.add(lblNewLabel);

		JButton bottoneUtente = new JButton("Utente");
		bottoneUtente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUtente.main(null);
				dispose();
			}
		});
		bottoneUtente.setBounds(138, 150, 89, 23);
		contentPane.add(bottoneUtente);
		
		JButton bottoneAdmin = new JButton("Admin");
		bottoneAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlloPassAdmin.main(null);
				dispose();
			}
		});
		bottoneAdmin.setBounds(332, 150, 89, 23);
		contentPane.add(bottoneAdmin);
	}
}
