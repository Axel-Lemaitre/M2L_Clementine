package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Ctrl;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Classe définissant la vue d'ajout d'un sénior
 * @author xavier
 *
 */
public class AjoutSenior extends JDialog implements MyView{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnValider;
	private JButton btnAnnuler;
	private static JTextField txtNom;
	private static JTextField txtNumSecu;

	/**
	 * Méthode statique permettant de réinitialiser les champs
	 */
	public static void init(){
		txtNumSecu.setText("");
		txtNom.setText("");
	}
	
	/**
	 * Méthode statique permettant d'obtenir le contenu du champ texte nom
	 * @return le contenu du champ texte nom
	 */
	public static String getTxtName(){
		return txtNom.getText();
	}
	
	/**
	 * Méthode statique permettant d'obtenir le contenu du champ texte numéro de sécurité sociale
	 * @return le contenu du champ texte num secu
	 */
	public static String getTxtNumSecu(){
		if(txtNumSecu.getText().equals(""))
			return null;
		return txtNumSecu.getText();
	}
	/**
	 * Méthode statique permettant de placer le curseur dans le champ texte nom
	 */
	public static void focusTxtName(){
		AjoutSenior.txtNom.requestFocus();
	}
	
	/**
	 * Create the dialog.
	 */
	public AjoutSenior() {
		setTitle("Sénior - Ajouter");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(123, 102, 50, 14);
		contentPanel.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBounds(183, 99, 192, 20);
		contentPanel.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblNumSecu = new JLabel("Numéro de sécurité sociale :");
		lblNumSecu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumSecu.setBounds(27, 63, 145, 14);
		contentPanel.add(lblNumSecu);
		
		txtNumSecu = new JTextField();
		txtNumSecu.setBounds(182, 60, 192, 20);
		contentPanel.add(txtNumSecu);
		txtNumSecu.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnValider = new JButton("Valider");
				buttonPane.add(btnValider);
				getRootPane().setDefaultButton(btnValider);
			}
			{
				btnAnnuler = new JButton("Annuler");
				buttonPane.add(btnAnnuler);
			}
			{
				JButton btnFermer = new JButton("Fermer");
				btnFermer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnFermer);
			}
		}
	}


	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnValider.setActionCommand("AjoutSenior_valider");
		this.btnValider.addActionListener(ctrl);
		this.btnAnnuler.setActionCommand("AjoutSenior_annuler");
		this.btnAnnuler.addActionListener(ctrl);
	}
}
