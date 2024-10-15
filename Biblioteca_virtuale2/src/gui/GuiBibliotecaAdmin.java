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


public class GuiBibliotecaAdmin extends JFrame {

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
	private JTextField text_cerca;
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
	private JTextField txt_img2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBibliotecaAdmin frame = new GuiBibliotecaAdmin();
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

	public GuiBibliotecaAdmin() throws SQLException {

		Object[][] obj;
		
		Catalogo nuovocatalogo=new Catalogo();
		JScrollPane scrollPane = new JScrollPane();
		JPanel imagePanel = new JPanel();
		
		
		InserimentoDatabase ins = new InserimentoDatabase();
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
		
		
		Setta_button(nuovocatalogo, panel,scrollPane,imagePanel);
		
		
		
		
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
		
		
	
		Panel panel_2 = new Panel();
		panel_2.setBounds(429, 0, 343, 266);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton BottoneUpdate = new JButton("Update");
		BottoneUpdate.setBounds(78, 186, 89, 23);
		panel_2.add(BottoneUpdate);
		
		JButton BottoneCancella = new JButton("Cancella");
		BottoneCancella.setBounds(242, 186, 89, 23);
		panel_2.add(BottoneCancella);
		
		JButton BottoneCerca = new JButton("Cerca per Titolo");
		BottoneCerca.setBounds(197, 230, 131, 23);
		panel_2.add(BottoneCerca);
		
		//---------------------------INSERISCO IMMAGINI + UPDATE ---------------------------------

      //------------------------BOTTONE INSERISCI NEW LIBRO ------------------------------------
		
      		JButton btn_new_libro = new JButton("Inserisci");
      		
      		btn_new_libro.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
       		if(Controllo_text(txt_isbn, txt_autore, txt_anno_pub, txt_genere, txt_qt_disp, txt_titolo, txt_img)) {
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
                  Libro l=new Libro(txt_isbn.getText(),txt_titolo.getText(), txt_autore.getText(),Integer.parseInt(txt_qt_disp.getText()),txt_genere.getText(),Integer.parseInt(txt_anno_pub.getText()),
      					destinazione.toString());
                  ins.inserimento(txt_isbn.getText(),txt_titolo.getText(), txt_autore.getText(),Integer.parseInt(txt_qt_disp.getText()),txt_genere.getText(),Integer.parseInt(txt_anno_pub.getText()),
      					destinazione.toString());
                  nuovocatalogo.aggiungi_libro(l);
      			JOptionPane.showMessageDialog(null, "Libro inserito correttamente");
      			txt_isbn.setText("");
      			txt_anno_pub.setText("");
      			txt_autore.setText("");
      			txt_genere.setText("");
      			txt_img2.setText("");
      			txt_qt_disp.setText("");
      			txt_titolo.setText("");
      			lb_img.setText("");
//////////////////////////////RICARICO LA LISTA PER VEDERLA AGGIORNATA////////////////////////
				try {
					Ricarica(nuovocatalogo, panel,scrollPane,imagePanel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//////////////////////////////RICARICO LA LISTA PER VEDERLA AGGIORNATA////////////////////////
      				}
      			}
      		});
      		
      		
      		
      		
      		
      		
        BottoneUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!textFieldISBN.getText().equals("")) {
				String ISBN=textFieldISBN.getText();
				String autore=textFieldAutore.getText();
				int anno=Integer.parseInt(textFieldAnno.getText()); 				
				String titolo=textFieldTitolo.getText();
				int qta=Integer.parseInt(textFieldQtaDisp.getText());
				String genere=textFieldGenere.getText();
				String img=txt_img2.getText();
				upd.update(ISBN, titolo,autore, qta,genere, 
						anno,img);//faccio una update del libro tramite la classe UpdateDabase
				Libro nuovolibro=new Libro(ISBN,titolo,autore,qta,genere,anno,img);
//////////////////////////////RICARICO LA LISTA PER VEDERLA AGGIORNATA////////////////////////
					nuovocatalogo.ricarica(nuovolibro);
					try {
						nuovocatalogo.ordinaPerTitolo(nuovolibro);
						Ricarica(nuovocatalogo, panel,scrollPane,imagePanel);
					      JOptionPane.showMessageDialog(null, "Update riuscito correttamente");
					textFieldAnno.setText("");textFieldAutore.setText("");textFieldGenere.setText("");textFieldISBN.setText("");textFieldQtaDisp.setText("");textFieldTitolo.setText("");txt_img.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
		      JOptionPane.showMessageDialog(null, "Update non riuscito");
					}
				}
				else JOptionPane.showMessageDialog(null, "Update non riuscito, selezionare un libro");

//////////////////////////////RICARICO LA LISTA PER VEDERLA AGGIORNATA////////////////////////
			}
			
    	});
        
        
        
//---------------------------INSERISCO IMMAGINI + UPDATE---------------------------------
		text_cerca = new JTextField();
		text_cerca.setBounds(10, 232, 188, 20);
		panel_2.add(text_cerca);
		text_cerca.setColumns(10);
		
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
		
		txt_img2 = new JTextField();
		txt_img2.setEnabled(false);
		txt_img2.setEditable(false);
		txt_img2.setBounds(116, 128, 169, 19);
		panel_2.add(txt_img2);
		txt_img2.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Copertina");
		lblNewLabel_12.setBounds(29, 131, 45, 13);
		panel_2.add(lblNewLabel_12);
		
		JButton btn_img2 = new JButton("Sfoglia");
		btn_img2.setBounds(154, 155, 89, 21);
		btn_img2.addActionListener(new ActionListener() {
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
                    String destImg = "src/Immagine";
                    String nomeFile = new File(percorsoImmagine).getName();  // Usa il percorso corretto

                    // Crea la cartella se non esiste gi�
                    File cartella = new File(destImg);
                    if (!cartella.exists()) {
                        cartella.mkdirs();
                    }

                    // Copia l'immagine nella cartella di destinazione
                    Path destinazione = Paths.get(destImg, nomeFile);

                    txt_img2.setText(destinazione.toString());
                }
            }
        });
		
		panel_2.add(btn_img2);
		
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
		
		BottoneCancella.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Libro libro=new Libro();
				if(!textFieldISBN.getText().equals("")) {
					String ISBN = textFieldISBN.getText();
				for(Libro l:nuovocatalogo.getLista_libri()) {
					if(l.getISBN().equals(ISBN)) {
						 libro=l;
					}
				}
				try {
					del.delete(ISBN);
					nuovocatalogo.rimuovi_libro(libro);
					JOptionPane.showMessageDialog(null, "Libro con ISBN: "+ISBN+" eliminato con successo!");
					Ricarica(nuovocatalogo, panel,scrollPane,imagePanel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else JOptionPane.showMessageDialog(null,"Selezionare un libro da eliminare dal catalogo");	
			}
		});
	
		btn_new_libro.setBounds(215, 227, 96, 21);
		panel_1.add(btn_new_libro);
			
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
               
                
                imageButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    	textFieldISBN.setText(l.getISBN());
                    	textFieldAutore.setText(l.getAutore());
                    	textFieldAnno.setText(Integer.toString(l.getAnnoPubblicazione()));
                    	textFieldGenere.setText(l.getGenere());
                    	textFieldQtaDisp.setText(Integer.toString(l.getQuantitaDisponibile()));
                    	textFieldTitolo.setText(l.getTitolo());
                    	txt_img2.setText(l.getCopertina());
                    	textFieldAutore.setEditable(true);
                    	textFieldAnno.setEditable(true);
                    	textFieldGenere.setEditable(true);
                    	textFieldQtaDisp.setEditable(true);
                    	textFieldTitolo.setEditable(true);
                    }
                }); 
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
	                    	textFieldISBN.setText(l.getISBN());
	                    	textFieldAutore.setText(l.getAutore());
	                    	textFieldAnno.setText(Integer.toString(l.getAnnoPubblicazione()));
	                    	textFieldGenere.setText(l.getGenere());
	                    	textFieldQtaDisp.setText(Integer.toString(l.getQuantitaDisponibile()));
	                    	textFieldTitolo.setText(l.getTitolo());
	                    	txt_img2.setText(l.getCopertina());
	                    	textFieldAutore.setEditable(true);
	                    	textFieldAnno.setEditable(true);
	                    	textFieldGenere.setEditable(true);
	                    	textFieldQtaDisp.setEditable(true);
	                    	textFieldTitolo.setEditable(true);
	                    }
	                });
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

