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

public class ChoixSeances extends JDialog implements MyView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JList<String> listSeances;
	private JButton btnValider;
	
	public static ArrayList<String> getSeanceDate(){
		ArrayList<String> retour = new ArrayList<String>();
		for(Object date : listSeances.getSelectedValues()){
			retour.add(date.toString());
			System.out.println(date.toString());
		}
		return retour;
	}
	
	public static void setListSeances(ArrayList<Seance> liste){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Seance s : liste){
			model.addElement(s.getDateSeance().toString());
		}
		listSeances.setModel(model);
	}
	
	public ChoixSeances(ArrayList<Seance> liste){
		setTitle("S\u00E9ances - Inscrire");
		setModal(true);
		setBounds(100,100,450,300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		listSeances = new JList<String>();
		listSeances.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSeances.setBounds(41,25,167,84);
		contentPanel.add(listSeances);
		setListSeances(liste);
		
		JLabel lblSeance = new JLabel("Etape 3 : choix des s\u00E9ances");
		lblSeance.setBounds(0,0,434,14);
		contentPanel.add(lblSeance);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnValider = new JButton("Valider");
			buttonPane.add(btnValider);
			getRootPane().setDefaultButton(btnValider);
		}
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	buttonPane.add(btnValider);
	buttonPane.add(btnFermer);
	
	}
	
	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnValider.setActionCommand("InscriptionSenior_valider");
		this.btnValider.addActionListener(ctrl);
		
	}

}
