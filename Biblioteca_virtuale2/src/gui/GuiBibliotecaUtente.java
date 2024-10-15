package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import classi.Catalogo;
import classi.Libro;
import databasecredenziali.UpdateIsLogged;
import databasecredenziali.UpdateLibriPrenotati;
import databaselibri.Database;
import databaselibri.InserimentoDatabase;
import databaselibri.SelezionaTabella;
import databaselibri.UpdateDatabase;

import java.time.zone.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiBibliotecaUtente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_isbn;
	private JTextField txt_titolo;
	private JTextField txt_autore;
	private JTextField txt_qt_disp;
	private JTextField txt_genere;
	private JTextField txt_anno_pub;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField;
	private JLabel Label;
	private JButton BottoneCerca;
	private JTable tablePrenotati;
	private JButton BottoneEsci;
	private JLabel LabelUtente;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBibliotecaUtente frame = new GuiBibliotecaUtente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
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
	
	
	public String trovaloggato()
	{
		String sql= "SELECT utente,islogged FROM Account";
		String user="";
		try(Connection conn = this.connect(); Statement stmt  = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) 
		{
			while(rs.next())
			{
				if(Integer.parseInt(rs.getString("islogged"))==1)
				{
					user=rs.getString("utente");
				}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return user;
	}
	
	public boolean doppiaPren(int riga)
	{
		DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		DefaultTableModel tblModel2 = (DefaultTableModel)tablePrenotati.getModel();
		
		String tblISBN1=tblModel.getValueAt(riga, 0).toString();
		
		int i=0;
		boolean flag=true;
		
		while(i<tblModel2.getRowCount())
		{
			if(tblISBN1.equals(tblModel2.getValueAt(i, 0)))
			{
				flag=false;
			}
			i++;
		}
		
		return flag;
	}
	
	
	public GuiBibliotecaUtente() throws SQLException {

		Object[][] obj;
		SelezionaTabella sel=new SelezionaTabella();
		UpdateDatabase upd = new UpdateDatabase();
		UpdateIsLogged updlog=new UpdateIsLogged();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 452, 297);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Consulta Catalogo", null, panel, null);
		tabbedPane.addTab("Libri Prenotati", panel2);
		panel2.setLayout(null);
		
		tablePrenotati = new JTable();
		obj= sel.fillTablePrenotati();
		
		tablePrenotati.setModel(new DefaultTableModel(
			obj,
			new String[] {
				"ISBN", "Titolo", "Autore", "Genere", "Anno Pubblicazione", 
			}
		));
		tablePrenotati.setBounds(10, 11, 427, 247);
		panel2.add(tablePrenotati);
		panel.setLayout(null);
		

		txt_isbn = new JTextField();
		txt_isbn.setBounds(215, 9, 96, 19);
	
		txt_isbn.setColumns(10);
		
		txt_titolo = new JTextField();
		txt_titolo.setBounds(215, 42, 96, 19);

		txt_titolo.setColumns(10);
		
		txt_autore = new JTextField();
		txt_autore.setBounds(215, 74, 96, 19);

		txt_autore.setColumns(10);
		
		txt_qt_disp = new JTextField();
		txt_qt_disp.setBounds(215, 105, 96, 19);

		txt_qt_disp.setColumns(10);
		
		txt_genere = new JTextField();
		txt_genere.setBounds(215, 134, 96, 19);
		
		txt_genere.setColumns(10);
		
		txt_anno_pub = new JTextField();
		txt_anno_pub.setBounds(215, 163, 96, 19);
	
		txt_anno_pub.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(81, 13, 45, 13);
	
		
		JLabel lblNewLabel_1 = new JLabel("Titolo");
		lblNewLabel_1.setBounds(81, 43, 45, 16);
	
		
		JLabel lblNewLabel_2 = new JLabel("Autore");
		lblNewLabel_2.setBounds(81, 77, 45, 13);

		
		JLabel lblNewLabel_3 = new JLabel("Quantit\u00E0 disponibile");
		lblNewLabel_3.setBounds(81, 108, 107, 13);

		
		JLabel lblNewLabel_4 = new JLabel("Genere");
		lblNewLabel_4.setBounds(81, 137, 45, 13);
	
		
		JLabel lblNewLabel_5 = new JLabel("Anno pubblicazione");
		lblNewLabel_5.setBounds(81, 164, 107, 13);
		//tableModel = (DefaultTableModel) table.getModel();
		
		BottoneCerca = new JButton("Cerca");
		BottoneCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tblModel);
				table.setRowSorter(obj);
				obj.setRowFilter(RowFilter.regexFilter(textField.getText()));
	
			}
		});
		BottoneCerca.setBounds(520, 190, 89, 23);
		contentPane.add(BottoneCerca);
		
		

		JButton BottonePrestito = new JButton("Prenota Libro");
		BottonePrestito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateLibriPrenotati updpren=new UpdateLibriPrenotati();
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				
				if(table.getSelectedRowCount()==1)
				{
					int column1 = 0;
					int row1 = table.getSelectedRow();
					int column2 = 3;
					int row2 = table.getSelectedRow();
					String ISBN= table.getModel().getValueAt(row1, column1).toString();
					String val=table.getModel().getValueAt(row2, column2).toString();
					int qta=Integer.parseInt(val);
					
					
					if(qta<=0 )
					{
						JOptionPane.showMessageDialog(null,"Libri Finiti");
					}
					else if(doppiaPren(table.getSelectedRow())==true)
					{
						qta=qta-1;
						upd.updateQta(ISBN, qta);
						tblModel.setValueAt(qta, table.getSelectedRow(),3);

						ISBN=ISBN+",";
						updpren.update(ISBN, 1);
						
						JOptionPane.showMessageDialog(null, "Prenotato con successo!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Hai gia prenotato questo libro!");
					}

				}
				else
				{
					if(table.getRowCount()==0)
					{
						//Table vuota
						JOptionPane.showMessageDialog(null, "La table � vuota!");
					}
					else
					{
						//Table non selezionata 
						JOptionPane.showMessageDialog(null, "Seleziona una Riga!");
					}
				}
				
				
			}
		});
		BottonePrestito.setBounds(472, 37, 149, 23);
		BottonePrestito.setVisible(false);
		contentPane.add(BottonePrestito);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BottonePrestito.setVisible(true);
	
			}
		});
		obj= sel.fillTable();
		
		table.setModel(new DefaultTableModel(
			obj,
			new String[] {
				"ISBN", "Titolo", "Autore", "Quantit\u00E0 disponibile", "Genere", "Anno Pubblicazione", 
			}
		));
		table.setBounds(10, 11, 427, 250);
		panel.add(table);
		
		textField = new JTextField();
		textField.setBounds(510, 159, 174, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		Label = new JLabel("Cerca");
		Label.setBounds(472, 162, 46, 14);
		contentPane.add(Label);
		
		LabelUtente = new JLabel(updlog.utente());
		LabelUtente.setBounds(574, 16, 83, 14);
		contentPane.add(LabelUtente);
		
		BottoneEsci = new JButton("Esci");
		BottoneEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = updlog.utente();
				updlog.update(nome,0);
				LabelUtente.setText(nome);
				dispose();
				Login.main(null);
			}
		});
		BottoneEsci.setBounds(595, 284, 89, 23);
		contentPane.add(BottoneEsci);
		
		lblNewLabel_6 = new JLabel("Account:");
		lblNewLabel_6.setBounds(520, 16, 71, 14);
		contentPane.add(lblNewLabel_6);
		
		
		
		
		
	}
}
