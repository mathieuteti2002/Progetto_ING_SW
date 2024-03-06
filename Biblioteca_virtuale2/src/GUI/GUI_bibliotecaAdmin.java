package GUI;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import Classi.Catalogo;
import Classi.Libro;
import DatabaseLibri.CancellaDatabase;
import DatabaseLibri.Database;
import DatabaseLibri.InserimentoDatabase;
import DatabaseLibri.SelezionaTabella;
import DatabaseLibri.UpdateDatabase;

import java.time.zone.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class GUI_bibliotecaAdmin extends JFrame {

	private JPanel contentPane;
	
	private JTextField txt_isbn;
	private JTextField txt_titolo;
	private JTextField txt_autore;
	private JTextField txt_qt_disp;
	private JTextField txt_genere;
	private JTextField txt_anno_pub;
	//private JTextField txt_img;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField;
	private JTextField textFieldISBN;
	private JTextField textFieldTitolo;
	private JTextField textFieldAutore;
	private JTextField textFieldQtaDisp;
	private JTextField textFieldGenere;
	private JTextField textFieldAnno;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField txt_img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_bibliotecaAdmin frame = new GUI_bibliotecaAdmin();
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
	public GUI_bibliotecaAdmin() throws SQLException {

		Object[][] obj;
		Catalogo nuovocatalogo=new Catalogo();
		InserimentoDatabase ins = new InserimentoDatabase();
		SelezionaTabella sel=new SelezionaTabella();
		UpdateDatabase upd = new UpdateDatabase();
		CancellaDatabase del = new CancellaDatabase();
		
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
		tabbedPane.addTab("Inserisci nuovo Libro", null, panel_1, null);
		panel_1.setLayout(null);
		
		txt_isbn = new JTextField();
		txt_isbn.setBounds(215, 9, 96, 19);
		panel_1.add(txt_isbn);
		txt_isbn.setColumns(10);
		
		txt_titolo = new JTextField();
		txt_titolo.setBounds(215, 42, 96, 19);
		panel_1.add(txt_titolo);
		txt_titolo.setColumns(10);
		
		txt_autore = new JTextField();
		txt_autore.setBounds(215, 74, 96, 19);
		panel_1.add(txt_autore);
		txt_autore.setColumns(10);
		
		txt_qt_disp = new JTextField();
		txt_qt_disp.setBounds(215, 105, 96, 19);
		panel_1.add(txt_qt_disp);
		txt_qt_disp.setColumns(10);
		
		txt_genere = new JTextField();
		txt_genere.setBounds(215, 134, 96, 19);
		panel_1.add(txt_genere);
		txt_genere.setColumns(10);
		
		txt_anno_pub = new JTextField();
		txt_anno_pub.setBounds(215, 163, 96, 19);
		panel_1.add(txt_anno_pub);
		txt_anno_pub.setColumns(10);
		
		JLabel lb_img = new JLabel("");
		lb_img.setBounds(384, 42, 262, 185);
		panel_1.add(lb_img);
		/*txt_img.setBounds(215, 197, 96, 19);
		panel_1.add(txt_img);
		txt_img.setColumns(10);
		txt_img.setEditable(false);*/
//---------------------------------CARICO IMMAGINE-----------------------	
		//JButton btnSfoglia = new JButton("Sfoglia...");
		JButton btnSfoglia = new JButton("Inserisci Copertina");
		btnSfoglia.setBounds(441, 8, 124, 21);
		panel_1.add(btnSfoglia);
        btnSfoglia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crea un JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Imposta il tipo di file selezionabile (immagini in questo caso)
                fileChooser.setFileFilter(new FileNameExtensionFilter("Immagini", "jpg", "jpeg", "png", "gif"));

                // Mostra il JFileChooser
                int result = fileChooser.showOpenDialog(null);

                // Verifica se l'utente ha selezionato un file
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Ottieni il percorso del file selezionato
                   String percorsoImmagine = fileChooser.getSelectedFile().getPath();
                   txt_img.setText(percorsoImmagine);
           		lb_img.setIcon(new ImageIcon(txt_img.getText()));
                //lb_img.setSize(384, 185); // Specifica le dimensioni desiderate
                }
            }
        });

        // Aggiungi il pulsante al tuo layout
       // getContentPane().add(btnSfoglia);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(844, 363);
        setLocationRelativeTo(null);
        setVisible(true);
		
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(81, 13, 45, 13);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titolo");
		lblNewLabel_1.setBounds(81, 43, 45, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Autore");
		lblNewLabel_2.setBounds(81, 77, 45, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantit\u00E0 disponibile");
		lblNewLabel_3.setBounds(81, 108, 107, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Genere");
		lblNewLabel_4.setBounds(81, 137, 45, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Anno pubblicazione");
		lblNewLabel_5.setBounds(81, 164, 107, 13);
		panel_1.add(lblNewLabel_5);
		
		
		
		table = new JTable();
		table.setColumnSelectionAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
			if(tblModel.getValueAt(table.getSelectedRow(), 0)!=null) {
				String tblISBN=tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				String tblTitolo=tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String tblAutore=tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String tblQta=tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				String tblGenere=tblModel.getValueAt(table.getSelectedRow(), 4).toString();
				String tblAnno=tblModel.getValueAt(table.getSelectedRow(), 5).toString();
				
				textFieldISBN.setText(tblISBN);
				textFieldTitolo.setText(tblTitolo);
				textFieldAutore.setText(tblAutore);
				textFieldQtaDisp.setText(tblQta);
				textFieldGenere.setText(tblGenere);
				textFieldAnno.setText(tblAnno);
			}
				textFieldISBN.setEnabled(false);
				textFieldTitolo.setEditable(true);
				textFieldAutore.setEditable(true);
				textFieldQtaDisp.setEditable(true);
				textFieldGenere.setEditable(true);
				textFieldAnno.setEditable(true);

				
				
			}
		});
		obj= sel.fillTable();
		
		table.setModel(new DefaultTableModel(
			obj,
			new String[] {
				"ISBN", "Titolo", "Autore", "Quantit\u00E0 disponibile", "Genere", "Anno Pubblicazione"
			}
		));
		
		
//------------------------BOTTONE INSERISCI NEW LIBRO ------------------------------------
		
		JButton btn_new_libro = new JButton("Inserisci ");
		//PREMI IL BOTTONE INSERISCI NUOVO LIBRO
		btn_new_libro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Object[][] obj;	
			//Inserimento Dati in Tabella
			ins.inserimento(txt_isbn.getText(),txt_titolo.getText(), txt_autore.getText(),Integer.parseInt(txt_qt_disp.getText()),txt_genere.getText(),Integer.parseInt(txt_anno_pub.getText()),
					txt_img.getText());
			JOptionPane.showMessageDialog(null, "Libro inserito correttamente");
			        
			try {
				obj=sel.fillTable();
				
				table.setModel(new DefaultTableModel(
					obj,
					new String[] {
						"ISBN", "Titolo", "Autore", "Quantita disponibile", "Genere", "Anno Pubblicazione"
					}
				));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String sourceImg=txt_img.getText();
			String destImg="src/Immagine";
            String nomeFile = new File(sourceImg).getName();
			 // Crea la cartella se non esiste gi�
            File cartella = new File(sourceImg);
            if (!cartella.exists()) {
                cartella.mkdirs();
            }
            // Copia l'immagine nella cartella di destinazione
            Path origine = Path.of(sourceImg);
            Path destinazione = Path.of(destImg, nomeFile);
            try {
				Files.copy(origine, destinazione, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			
			
			Libro nuovolibro= new Libro(txt_isbn.getText(),txt_titolo.getText(),txt_autore.getText()
					,Integer.parseInt(txt_qt_disp.getText()),
					txt_genere.getText(),Integer.parseInt(txt_anno_pub.getText()),txt_img.getText());
			nuovocatalogo.aggiungi_libro(nuovolibro);
//VERIFICARE----------------------------------------
			
			
			Object[] obj1 = { nuovolibro.getISBN(), nuovolibro.getTitolo(), nuovolibro.getAutore(),
			        nuovolibro.getQuantitaDisponibile(), nuovolibro.getGenere(), nuovolibro.getAnnoPubblicazione(),
			        nuovolibro.getCopertina()};
			tableModel.addRow(obj1);
			
			try {
				obj= sel.fillTable();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
//VERIFICARE---------------------------------------- + VEDI RIGA 49 E 50

				}
			
			
			//JList<Libro> list = new JList<>(nuovocatalogo.getLista_libri().toArray(new Libro[0]));
			//frame.getContentPane().add(list);
		
		});
		

		
		
	/*	table.setBounds(10, 10, 397, 250);
		panel.add(table);*/
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(429, 0, 343, 266);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton BottoneUpdate = new JButton("Update");
		BottoneUpdate.setBounds(75, 141, 89, 23);
		panel_2.add(BottoneUpdate);
		
		JButton BottoneCancella = new JButton("Cancella");
		BottoneCancella.setBounds(239, 141, 89, 23);
		panel_2.add(BottoneCancella);
		
		JButton BottoneCerca = new JButton("Cerca");
		BottoneCerca.setBounds(239, 230, 89, 23);
		panel_2.add(BottoneCerca);
		
		//---------------------------INSERISCO IMMAGINI ---------------------------------

        // Inizializza un pannello scorrevole per contenere le etichette delle immagini
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 397, 250);
        panel.add(scrollPane);

        // Inizializza un pannello per contenere le etichette delle immagini
        JPanel imagePanel = new JPanel();
        scrollPane.setViewportView(imagePanel);
        imagePanel.setLayout(new GridLayout(0, 2, 10, 10));

        // Carica i libri nella lista
        Object[][] obj2 = sel.fillTable();
        for (int i = 0; i < obj2.length; i++) {
            if (obj[i][0] != null) {
                Libro libro = new Libro(
                        obj2[i][0].toString(),
                        obj2[i][1].toString(),
                        obj2[i][2].toString(),
                        Integer.parseInt(obj2[i][3].toString()),
                        obj2[i][4].toString(),
                        Integer.parseInt(obj2[i][5].toString()),
                        obj2[i][6].toString()
                );

                // Creare un'etichetta per ogni immagine di copertina
                //new ImageIcon(new ImageIcon(libro.getCopertina()).getImage().getScaledInstance(50, 90, java.awt.Image.SCALE_DEFAULT))
                ImageIcon original=new ImageIcon(libro.getCopertina());
                int maxH=100;
                int w = original.getIconWidth()*maxH/original.getIconHeight();
                
                
                JButton imageLabel = new JButton(new ImageIcon(original.getImage().getScaledInstance(w, maxH, java.awt.Image.SCALE_SMOOTH)));
                imageLabel.setBounds(0, 0, w, maxH);
                //imageLabel.setText(libro.getTitolo());
                imagePanel.add(imageLabel);
                imageLabel.setBackground(new Color(240,240,240));
                
                JLabel lbTitolo = new JLabel(libro.getTitolo(), SwingConstants.CENTER);
                imagePanel.add(lbTitolo, BorderLayout.SOUTH);
                
                imageLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Aggiungi qui il codice per visualizzare i dettagli del libro o l'immagine di copertina
                        JOptionPane.showMessageDialog(null, "Dettagli del libro:\n" + libro.toString());
                        //creo un nuovo panel per caricare sopra le info del libro selezionato
                        //... qui
                    }
                });
            }
            
        }
//---------------------------INSERISCO IMMAGINI ---------------------------------

		
		
		textField = new JTextField();
		textField.setBounds(29, 231, 188, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textFieldTitolo = new JTextField();
		textFieldTitolo.setEditable(false);
		textFieldTitolo.setBounds(242, 24, 86, 20);
		panel_2.add(textFieldTitolo);
		textFieldTitolo.setColumns(10);
		
		textFieldAutore = new JTextField();
		textFieldAutore.setEditable(false);
		textFieldAutore.setBounds(78, 55, 86, 20);
		panel_2.add(textFieldAutore);
		textFieldAutore.setColumns(10);
		
		textFieldQtaDisp = new JTextField();
		textFieldQtaDisp.setEditable(false);
		textFieldQtaDisp.setBounds(242, 55, 86, 20);
		panel_2.add(textFieldQtaDisp);
		textFieldQtaDisp.setColumns(10);
		
		textFieldGenere = new JTextField();
		textFieldGenere.setEditable(false);
		textFieldGenere.setBounds(78, 86, 86, 20);
		panel_2.add(textFieldGenere);
		textFieldGenere.setColumns(10);
		
		textFieldAnno = new JTextField();
		textFieldAnno.setEditable(false);
		textFieldAnno.setBounds(242, 86, 86, 20);
		panel_2.add(textFieldAnno);
		textFieldAnno.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Autore");
		lblNewLabel_7.setBounds(29, 58, 46, 14);
		panel_2.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Genere");
		lblNewLabel_8.setBounds(29, 89, 46, 14);
		panel_2.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Titolo");
		lblNewLabel_9.setBounds(185, 27, 46, 14);
		panel_2.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Qta. Disp");
		lblNewLabel_10.setBounds(186, 58, 46, 14);
		panel_2.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Anno di Pubblicazione");
		lblNewLabel_11.setBounds(185, 89, 58, 14);
		panel_2.add(lblNewLabel_11);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setEditable(false);
		textFieldISBN.setBounds(78, 24, 86, 20);
		panel_2.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		txt_img = new JTextField();
		txt_img.setEditable(false);
		txt_img.setEnabled(false);
		txt_img.setVisible(false);
		txt_img.setBounds(215, 192, 96, 19);
		panel_1.add(txt_img);
		txt_img.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("ISBN");
		lblNewLabel_6.setBounds(29, 26, 46, 17);
		panel_2.add(lblNewLabel_6);
		BottoneCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(tblModel);
				table.setRowSorter(obj);
				obj.setRowFilter(RowFilter.regexFilter(textField.getText()));
			}
		});
		BottoneCancella.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				String ISBN = textFieldISBN.getText();
				if(table.getSelectedRowCount()==1)
				{
					tblModel.removeRow(table.getSelectedRow());
					del.delete(ISBN);
					JOptionPane.showMessageDialog(null, "Delete andato con successo!");
					
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
		BottoneUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				if(table.getSelectedRowCount()==1)
				{
					//Se una singola riga � selezionata allora fa update
					String txtISBN=textFieldISBN.getText();
					String txtTitolo=textFieldTitolo.getText();
					String txtAutore=textFieldAutore.getText();
					int txtQta=Integer.parseInt(textFieldQtaDisp.getText());
					String txtGenere=textFieldGenere.getText();
					int txtAnno=Integer.parseInt(textFieldAnno.getText());
					String txtimg=txt_img.getText();
					//Settiamo i valori aggiornati nella riga della tabella
					upd.update(txtISBN, txtTitolo, txtAutore, txtQta, txtGenere, txtAnno,txtimg);
					tblModel.setValueAt(txtISBN, table.getSelectedRow(),0);
					tblModel.setValueAt(txtTitolo, table.getSelectedRow(),1);
					tblModel.setValueAt(txtAutore, table.getSelectedRow(),2);
					tblModel.setValueAt(txtQta, table.getSelectedRow(),3);
					tblModel.setValueAt(txtGenere, table.getSelectedRow(),4);
					tblModel.setValueAt(txtAnno, table.getSelectedRow(),5);
					
					//Messaggio di Update
					JOptionPane.showMessageDialog(null, "Update andato con successo!");
					
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
		tableModel = (DefaultTableModel) table.getModel();
		
		
		
		
		btn_new_libro.setBounds(215, 227, 96, 21);
		panel_1.add(btn_new_libro);
		
		
		
		
		
	
	
		
	}
}

