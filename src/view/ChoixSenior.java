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
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.Ctrl;
import model.Activite;
import model.Senior;

public class ChoixSenior extends JDialog implements MyView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JList<String> listSeniors;
	private JButton btnVoirSeances;

	public static void setListSeniors(ArrayList<Senior> liste){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Senior s : liste){
			model.addElement(s.getNom());
		}
		listSeniors.setModel(model);
	}
	
	public ChoixSenior(ArrayList<Senior> liste) {
		setTitle("S\u00E9nior - Inscrire");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		listSeniors = new JList<String>();
		listSeniors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSeniors.setBounds(41, 25, 167, 84);
		JScrollPane scrollPane = new JScrollPane(listSeniors);
		scrollPane.setBounds(41,25,167,84);
		contentPanel.add(scrollPane);
		setListSeniors(liste);
		
		JLabel lblSenior = new JLabel("Etape 1 : choix du s\u00E9nior");
		lblSenior.setBounds(0, 0, 434, 14);
		contentPanel.add(lblSenior);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnVoirSeances = new JButton("Voir les S\u00E9ances");
			buttonPane.add(btnVoirSeances);
		}
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(btnVoirSeances);
		buttonPane.add(btnFermer);
		
	}
	
	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnVoirSeances.setActionCommand("Voir_voirSeances");
		this.btnVoirSeances.addActionListener(ctrl);
	}

	public static String getSeniorName(){
		return listSeniors.getSelectedValue();
	}

}
