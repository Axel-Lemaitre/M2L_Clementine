package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.Ctrl;
import model.Seance;

public class VoirSeances extends JDialog implements MyView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JList<String> listSeances;

	public static void setListSeances(ArrayList<Seance> liste){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Seance s : liste){
			model.addElement(s.getDateSeance().toString());
		}
		listSeances.setModel(model);
	}
	
	public VoirSeances(ArrayList<Seance> liste){
		setTitle("S\u00E9ances - Voir");
		setModal(true);
		setBounds(100,100,450,300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		listSeances = new JList<String>();
		listSeances.setBounds(41,25,267,84);
		contentPanel.add(listSeances);
		setListSeances(liste);
		
		JLabel lblSeance = new JLabel(" Les s\u00E9ances du s\u00E9nior");
		lblSeance.setBounds(0,0,434,14);
		contentPanel.add(lblSeance);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	buttonPane.add(btnFermer);
	
	}
	
	@Override
	public void assignListener(Ctrl ctrl) {
		// TODO Auto-generated method stub
		
	}

}
