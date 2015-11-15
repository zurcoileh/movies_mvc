package br.com.app.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class View implements ActionListener{
	
	Controller cMovies;
	
	JTable table;
	JFrame frame;
	JPanel panelDados;
	JPanel panelSearch;
	JLabel labelSearch;
	JButton buscar;
	JTextField textBox;	
	
	
	public View(Controller co) {
		this.cMovies = co;
	}	
		
	public void createView(){
		
		//Tabela
		table = new JTable();
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);
		table.setFont(new Font("Arial", Font.BOLD, 14));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scroll = new JScrollPane(); 
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table); 
		scroll.setSize(500,350);
		//--------------------------------------------------------//
		
		
		frame = new JFrame("Movie Library");
		
		panelDados = new JPanel();
		panelSearch = new JPanel();
		panelSearch.setSize(400,100);
		//panelDados.setSize(600,400);

		
		buscar = new JButton("go");
		labelSearch = new JLabel();	
		textBox = new JTextField();
		
		labelSearch.setText("Busca: ");
		textBox.setText("DIGITE A BUSCA...");	
		textBox.setSize(400, 100);
				
		buscar.addActionListener(this);	
				
		panelDados.add(scroll);
		
		panelSearch.add(labelSearch);
		panelSearch.add(textBox);
		panelSearch.add(buscar);		
		
	    frame.getContentPane().add(BorderLayout.CENTER, panelDados);		
		frame.getContentPane().add(BorderLayout.NORTH, panelSearch);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		//frame.setSize(600,600);
		frame.pack();
		frame.setVisible(true);
		
	}	
	
	public void populateTable(MovieTableModel mtm){
		
		table.setModel(mtm);		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == buscar){			
			cMovies.sendRequest(textBox.getText());			
		}			
	}	

	public void windowClosing(WindowEvent e) {
        System.exit(0);
     }

}
