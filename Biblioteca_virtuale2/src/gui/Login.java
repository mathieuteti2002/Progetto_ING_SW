package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import databasecredenziali.Database;
import databasecredenziali.UpdateIsLogged;
import databasecredenziali.UpdateLibriPrenotati;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField textFieldPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					//frame.setUndecorated(true);
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
	
	private Connection connect() {
        // SQLite connection string
		
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public boolean ricercaPassword(String utente, String password)
	{
		boolean flag=true;
		int i=0;
		int j=0;
		String sql= "SELECT utente,password,livello FROM Account";
		
		try(Connection conn = this.connect(); Statement stmt  = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next())
			{
				if((rs.getString("utente").equals(utente))&&(rs.getString("password").equals(password))&& rs.getInt("livello")==0)
				{
					i++;
				}
				else if((rs.getString("utente").equals(utente))&&(rs.getString("password").equals(password))&& rs.getInt("livello")==1)
				{
					j++;
				}
				
				if(i==1)
					flag=true; //Utente normale
				else if(j==1)
					flag=false; //Admin
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return flag;
	}
	
	
	
	
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 397);
		

		// Inizializza il contentPane
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);  // Imposta il colore di sfondo
        contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));  // Imposta il bordo
        contentPane.setLayout(null);  // Imposta il layout
        
     // Imposta il contentPane come il pannello principale del JFrame
        setContentPane(contentPane);

        contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		contentPane.setBackground(new Color(222, 184, 135));
        
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Utente");
		lblNewLabel.setBounds(10, 58, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 158, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(81, 53, 313, 26);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		UpdateIsLogged islog=new UpdateIsLogged();
		JButton BottoneLogin = new JButton("Login");
		BottoneLogin.setBounds(81, 259, 117, 29);
		BottoneLogin.setForeground(Color.WHITE);
		BottoneLogin.setBackground(new Color(241,57,83));
		BottoneLogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
				if(ricercaPassword(textFieldUser.getText(),textFieldPass.getText()))
				{
					islog.update(textFieldUser.getText(),1);
					JOptionPane.showMessageDialog(null, "Benvenuto "+textFieldUser.getText()+"!");
					dispose();
					GuiBibliotecaUtente.main(null);
				}
				else if(ricercaPassword(textFieldUser.getText(),textFieldPass.getText())==false)
				{
					islog.update(textFieldUser.getText(),1);
					JOptionPane.showMessageDialog(null, "Benvenuto Admin");
					dispose();
					GuiBibliotecaAdmin.main(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Accesso non riuscito","Login Error",JOptionPane.ERROR_MESSAGE);
					textFieldPass.setText(null);
					textFieldUser.setText(null);
				}

				
			}
		});
		contentPane.add(BottoneLogin);
		
		JButton BottoneReset = new JButton("Reset");
		BottoneReset.setForeground(Color.WHITE);
		BottoneReset.setBounds(208, 259, 117, 29);
		BottoneReset.setBackground(new Color(241,57,83));
		BottoneReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldPass.setText("");
				textFieldUser.setText("");;
				
			}
		});
		contentPane.add(BottoneReset);
		
		textFieldPass = new JPasswordField();
		textFieldPass.setBounds(81, 153, 313, 26);
		contentPane.add(textFieldPass);
		
		JLabel labelLink = new JLabel("Se non hai un account, clicca qui per registrarti.");
		labelLink.setBounds(10, 354, 351, 16);
		labelLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelezioneUtenza.main(null);
				
				dispose();
			}
		});
		contentPane.add(labelLink);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(409, 0, 400, 441);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, -39, 485, 480);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("src/Immagine/immagineLibroLogin.jpg"));
	}
}
