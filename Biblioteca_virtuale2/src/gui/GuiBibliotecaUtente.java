package gui;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import classi.Catalogo;
import classi.Libro;
import databaselibri.CancellaDatabase;
import databaselibri.Database;
import databaselibri.InserimentoDatabase;
import databaselibri.SelezionaTabella;
import databaselibri.UpdateDatabase;

import java.time.zone.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;


public class GuiBibliotecaUtente extends JFrame {

	private JPanel contentPane;
	//private JTextField txt_img;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField text_cerca;
	private JLabel nome_libro = new JLabel("");

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
	public SelezionaTabella sel1=new SelezionaTabella();
	public SelezionaTabella sel2=new SelezionaTabella();

	public GuiBibliotecaUtente() throws SQLException {

		Object[][] obj;
		
		Catalogo nuovocatalogo=new Catalogo();
		JScrollPane scrollPane = new JScrollPane();
		JPanel imagePanel = new JPanel();

		
		InserimentoDatabase ins = new InserimentoDatabase();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 853, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 806, 309);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Consulta Catalogo", null, panel, null);
		panel.setLayout(null);
		
		

		
		

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Libri Prenotati", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lb_img = new JLabel("");
		lb_img.setBounds(384, 42, 262, 185);
		panel_1.add(lb_img);
		
		
		Setta_button(nuovocatalogo, panel,scrollPane,imagePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(844, 363);
        setLocationRelativeTo(null);
        setVisible(true);
		
		
	
		Panel panel_2 = new Panel();
		panel_2.setBounds(429, 0, 343, 266);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton BottoneCerca = new JButton("Cerca per Titolo");
		BottoneCerca.setBounds(197, 160, 131, 23);
		panel_2.add(BottoneCerca);
        
        
        
//---------------------------INSERISCO IMMAGINI + UPDATE---------------------------------
		text_cerca = new JTextField();
		text_cerca.setBounds(10, 162, 188, 20);
		panel_2.add(text_cerca);
		text_cerca.setColumns(10);
		
		JButton btnNewButton = new JButton("Prenota Libro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(197, 75, 131, 42);
		panel_2.add(btnNewButton);
		
		nome_libro.setBounds(10, 75, 177, 27);
		panel_2.add(nome_libro);
		
		JLabel lblNewLabel_13 = new JLabel("Titolo");
		lblNewLabel_13.setBounds(278, 0, 45, 13);
		panel.add(lblNewLabel_13);
		
		BottoneCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cercaLibro(nuovocatalogo);
				for(Libro l : nuovocatalogo.getLista_libri())
					System.out.println(l.getTitolo());
				try {
					Ricarica(nuovocatalogo, panel,scrollPane,imagePanel);
					JOptionPane.showMessageDialog(null, "Catalogo ordinato in base al filtro");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				text_cerca.setText("");
			}
		});
			
	}
	
	////////////////////////////////////////// RICARICA ///////////////////////////////////////
	
	public void Ricarica(Catalogo nuovocatalogo,JPanel panel, JScrollPane scrollPane,JPanel imagePanel ) throws SQLException {
		imagePanel.removeAll();
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBounds(10, 10, 397, 250);
        panel.add(scrollPane);
        // Inizializza un pannello per contenere le etichette delle immagini
        scrollPane.setViewportView(imagePanel);
        imagePanel.setLayout(new GridLayout(0, 2, 10, 10));
        // Carica i libri nella lista
            for (Libro l : nuovocatalogo.lista_libri) {
            	ImageIcon original=new ImageIcon(l.getCopertina());
                int maxH=100;
                int w = original.getIconWidth()*maxH/original.getIconHeight();
                JButton imageButton = new JButton(new ImageIcon(original.getImage().getScaledInstance(w, maxH, java.awt.Image.SCALE_SMOOTH)));
                imageButton.setBounds(0, 0, w, maxH);
                imagePanel.add(imageButton);
                imageButton.setBackground(new Color(240,240,240));   
                JLabel lbTitolo = new JLabel(l.getTitolo(), SwingConstants.CENTER);
                imagePanel.add(lbTitolo, BorderLayout.SOUTH);
               
                
                
			}
	}
	
	public void Setta_button(Catalogo nuovocatalogo,JPanel panel, JScrollPane scrollPane,JPanel imagePanel) throws SQLException {
		SelezionaTabella sel=new SelezionaTabella();
		imagePanel.removeAll();
		 Object[][] obj2 = sel.fillTable();
		  for (int i = 0; i < obj2.length; i++) {
	            if (obj2[i][0] != null) {
	                Libro libro = new Libro(
	                        obj2[i][0].toString(),
	                        obj2[i][1].toString(),
	                        obj2[i][2].toString(),
	                        Integer.parseInt(obj2[i][3].toString()),
	                        obj2[i][4].toString(),
	                        Integer.parseInt(obj2[i][5].toString()),
	                        obj2[i][6].toString()
	                );
	                nuovocatalogo.aggiungi_libro(libro);
	        } 
	        }
	        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	        scrollPane.setBounds(10, 10, 397, 250);
	        panel.add(scrollPane);
	        // Inizializza un pannello per contenere le etichette delle immagini
	        scrollPane.setViewportView(imagePanel);
	        imagePanel.setLayout(new GridLayout(0, 2, 10, 10));
	        // Carica i libri nella lista
	            for (Libro l : nuovocatalogo.lista_libri) {
	            	ImageIcon original=new ImageIcon(l.getCopertina());
	                int maxH=100;
	                int w = original.getIconWidth()*maxH/original.getIconHeight();
	                JButton imageButton = new JButton(new ImageIcon(original.getImage().getScaledInstance(w, maxH, java.awt.Image.SCALE_SMOOTH)));
	                imageButton.setBounds(0, 0, w, maxH);
	                imagePanel.add(imageButton);
	                imageButton.setBackground(new Color(240,240,240));   
	                JLabel lbTitolo = new JLabel(l.getTitolo(), SwingConstants.CENTER);
	                imagePanel.add(lbTitolo, BorderLayout.SOUTH);
	                
	               
	                imageButton.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                    	nome_libro.setText(l.getTitolo());
	                    	System.out.println(nome_libro.getText());
	                    }
	                }) ;
				} 
	}
	////////////////////////////////////////// RICARICA ///////////////////////////////////////
	
	
	////////////////////////////////////////// CONTROLLO TEXT ///////////////////////////////////////
		public boolean Controllo_text(JTextField ISBN,JTextField au,JTextField anno,
				JTextField gen,JTextField qta,JTextField tit,JTextField img) {
			if(!(ISBN.getText().isEmpty()&&au.getText().isEmpty()&&anno.getText().isEmpty()&&
					gen.getText().isEmpty()&&qta.getText().isEmpty()&&tit.getText().isEmpty()&&
					img.getText().isEmpty()))
				return true;
			else return false;
		}
		
		
		public void cercaLibro(Catalogo c) {
	        // Assumi che txtTitoloCercato sia il JTextField in cui inserisci il titolo da cercare
	        String titoloCercato = text_cerca.getText();

	        // Ordina la lista con un Comparator personalizzato
	        Collections.sort(c.getLista_libri(), new Comparator<Libro>() {
	            @Override
	            public int compare(Libro libro1, Libro libro2) {
	                // Metti il libro cercato in cima
	                if (titoloCercato.equalsIgnoreCase(libro1.getTitolo())) {
	                    return -1; // libro1 viene prima di libro2
	                } else if (titoloCercato.equalsIgnoreCase(libro2.getTitolo())) {
	                    return 1; // libro2 viene prima di libro1
	                } else {
	                    // Ordina gli altri libri in modo normale
	                    return libro1.getTitolo().compareToIgnoreCase(libro2.getTitolo());
	                }
	            }
	        });
	        
	        // Ora la listaLibri � ordinata con il libro cercato in cima
	        // Puoi fare qualcos'altro con la lista ordinata, ad esempio, aggiornare la GUI
	    }
}

