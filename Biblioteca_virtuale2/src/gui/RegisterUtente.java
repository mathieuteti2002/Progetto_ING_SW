package gui;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classi.Utente;
import databasecredenziali.Database;
import databasecredenziali.RegistrazioneDatabase;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.print.DocFlavor.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class RegisterUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldUtente;
	private JPasswordField textFieldPassword;
	private JPasswordField textFieldRipetiPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUtente frame = new RegisterUtente();
					frame.setVisible(true);
					frame.setSize(850,400);
					
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
		String DB_REL_FILE = "db/CredenzialiAccount.db3";
		String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
		
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public boolean doppi(String email, String utente)
	{
		boolean flag=true;
		int numRighe=0;
		int i=0;
		String DB_REL_FILE = "db/CredenzialiAccount.db3";
		String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
		String sql= "SELECT email,utente FROM Account";
		
		try(Connection conn = this.connect(); Statement stmt  = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next())
			{
				if((rs.getString("email").equals(email)) || (rs.getString("utente").equals(utente)))
				{
					i++;
				}
				
				if(i==0)
					flag=true;
				else
					flag=false;
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return flag;
	}
	
	public RegisterUtente() {
		RegistrazioneDatabase rdb = new RegistrazioneDatabase();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 683);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
		});
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 400);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		contentPane.add(panel);
		
		panel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(0, 0, 397, 400);
		panel.add(lblNewLabel_4);
		
		
		//CAMBIATE CON IL VOSTRO PERCORSO
		
		
		ImageIcon imgLibri=new ImageIcon("src/Immagine/Book_shelf_silhouette.jpg");
		lblNewLabel_4.setIcon(imgLibri);

		
		Button BottoneRegistrazione = new Button("Registrazione");
		BottoneRegistrazione.setBounds(421, 291, 177, 29);
		BottoneRegistrazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email=textFieldEmail.getText();
				String utente=textFieldUtente.getText();
				String password=textFieldPassword.getText();
				String ripetiPassword=textFieldRipetiPassword.getText();
		
				Utente account=new Utente(email,utente,password,0);
				
				
				if((textFieldRipetiPassword.getText().equals(textFieldPassword.getText()))&&(doppi(textFieldEmail.getText(),textFieldUtente.getText())==true))
				{
					rdb.insCredenziali(textFieldEmail.getText(), utente, password,0,"",0);
					JOptionPane.showMessageDialog(null, "Inserimento andato bene!");
					dispose();
					Login.main(null);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Email o Utente gi� registrato!");
				}
				
			}
		});
		BottoneRegistrazione.setForeground(Color.WHITE);
		BottoneRegistrazione.setBackground(new Color(241,57,83));
		contentPane.add(BottoneRegistrazione);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(424, 52, 386, 26);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelEmail = new JLabel("E-mail");
		labelEmail.setBounds(424, 25, 61, 16);
		contentPane.add(labelEmail);
		
		JLabel labelUtente = new JLabel("Utente");
		labelUtente.setBounds(424, 89, 61, 16);
		contentPane.add(labelUtente);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setBounds(424, 142, 61, 16);
		contentPane.add(labelPassword);
		
		textFieldUtente = new JTextField();
		textFieldUtente.setBounds(424, 105, 386, 26);
		textFieldUtente.setColumns(10);
		contentPane.add(textFieldUtente);
		
		JLabel labelRipetiPassword = new JLabel("Ripeti Password");
		labelRipetiPassword.setBounds(424, 206, 133, 16);
		contentPane.add(labelRipetiPassword);
		
		JLabel labelAvviso = new JLabel("Se sei gi\u00E0 registrato. clicca qui per fare il login");
		labelAvviso.setBounds(421, 336, 268, 14);
		labelAvviso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.main(null);
				
				dispose();
			}
		});
		contentPane.add(labelAvviso);
		
		JLabel lblNewLabel = new JLabel("Utente");
		lblNewLabel.setBounds(742, 11, 68, 14);
		contentPane.add(lblNewLabel);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(424, 169, 386, 26);
		contentPane.add(textFieldPassword);
		
		textFieldRipetiPassword = new JPasswordField();
		textFieldRipetiPassword.setBounds(424, 233, 386, 26);
		contentPane.add(textFieldRipetiPassword);
		

		
		
		
		
	}
}
