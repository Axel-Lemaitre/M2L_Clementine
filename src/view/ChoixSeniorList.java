package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.Date;

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
//import model.Seance;
import model.Senior;
/**
 * Classe définissant la vue de choix d'un sénior pour effectuer une inscription
 * @author xavier
 *
 */
public class ChoixSeniorList extends JDialog implements MyView{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JList<String> listSeniors;
	private static JList<String> listActivites;
//	private JButton btnValider;
	private JButton btnVoirSeances;
	
//	public static void init(){
//		listActivites.setSelectedValue(null);;
//		listSeniors.setSelectedIndex(null);
//	}

	public static String getActiviteName(){
		return listActivites.getSelectedValue();
	}
	
	public static String getSeniorName(){
		return listSeniors.getSelectedValue();
	}
	
	/**
	 * Méthode permettant de mettre à jour le contenu de la liste des séniors
	 * @param liste Un objet ArrayList contenant des objets Senior à intégrer dans l'ihm
	 */
	public static void setListSeniors(ArrayList<Senior> liste){
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(Senior s : liste){
			model.addElement(s.getNom());
		}
		listSeniors.setModel(model);
	}
	
	/**
	 * Méthode permettant de mettre à jour le contenu de la liste des activite
	 * @param liste Un objet ArrayList contenant des objets Activite à intégrer dans l'ihm
	 */
	public static void setListActivites(ArrayList<Activite> liste){
		DefaultListModel<String> modelAct = new DefaultListModel<String>();
		for(Activite a : liste){
			modelAct.addElement(a.getDesignation());
		}
		listActivites.setModel(modelAct);
	}
	

	/**
	 * Create the dialog.
	 * @param liste Un objet ArrayList contenant des objets Senior à intégrer dans l'ihm
	 */
	public ChoixSeniorList(ArrayList<Senior> liste, ArrayList<Activite> listeA) {
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
		//contentPanel.add(listSeniors);
		setListSeniors(liste);
		

//		scrollPane.setViewportView(listSeniors);
		
		listActivites = new JList<String>();
		listActivites.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listActivites.setBounds(41,166,167,150);
		contentPanel.add(listActivites);
		setListActivites(listeA);
		
//		listDateSeances = new JList<String>();
//		listDateSeances.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		listDateSeances.setBounds(180,166,167,150);
//		contentPanel.add(listDateSeances);
//		setListSeances(listD);
		
		JLabel lblSenior = new JLabel("Etape 1 : choix du s\u00E9nior");
		lblSenior.setBounds(0, 0, 434, 14);
		contentPanel.add(lblSenior);
		
		JLabel lblActivite = new JLabel("Etape 2 : choix des activit\u00E9s");
		lblActivite.setBounds(0, 131, 434, 14);
		contentPanel.add(lblActivite);
		
//		JLabel lblSeance = new JLabel("Etape 3 : choix des s\u00E9sances");
//		lblSeance.setBounds(150,131,434,14);
//		contentPanel.add(lblSeance);
	

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
//		{
//			btnValider = new JButton("Valider");
//			buttonPane.add(btnValider);
//			getRootPane().setDefaultButton(btnValider);
//		}
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
//		buttonPane.add(btnValider);
		buttonPane.add(btnVoirSeances);
		buttonPane.add(btnFermer);
		
	}
	

	@Override
	public void assignListener(Ctrl ctrl) {
//		this.btnValider.setActionCommand("InscriptionSenior_valider");
//		this.btnValider.addActionListener(ctrl);
		this.btnVoirSeances.setActionCommand("InscriptionSenior_voirSeances");
		this.btnVoirSeances.addActionListener(ctrl);
	}
}
