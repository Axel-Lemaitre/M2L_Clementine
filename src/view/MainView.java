package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Ctrl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe d�finissant la vue g�n�rale de l'application
 * @author xavier
 *
 */
public class MainView extends JFrame implements MyView{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSenior;
	private JButton btnInscription;

	/**
	 * Launch the application.
	 * @param args arguments du main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Cr�ation du contr�leur
					Ctrl ctrl = Ctrl.getCtrl();
					//Cr�ation de la vue g�n�rale
					MainView frame = new MainView();
					//Assignation d'un observateur sur cette vue
					frame.assignListener(ctrl);
					//Affichage de la vue
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		
		//d�finition des param�tres de la fen�tre
		setTitle("Cl�mentine - Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//Cr�ation du panneau qui va contenir les �l�ments
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Cr�ation et ajout du bouton qui nous dirige vers la fen�tre d'ajout d'un s�nior
		btnSenior = new JButton("Ajout d'un nouveau s�nior");
		btnSenior.setBounds(54, 56, 309, 23);
		contentPane.add(btnSenior);
		
		//Cr�ation et ajout du bouton qui nous dirige vers la fen�tre d'inscription des s�niors aux activit�s
		btnInscription = new JButton("Inscription d'un s�nior � des activit�s");
		btnInscription.setBounds(54, 104, 309, 23);
		contentPane.add(btnInscription);
		
		//Cr�ation du bouton pour quitter l'application
		JButton btnFermer = new JButton("Quitter");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnFermer.setBounds(335, 227, 89, 23);
		contentPane.add(btnFermer);
	}

	//Permet d'envoyer des informations lors de clicks sur les boutons au Controlleur
	@Override
	public void assignListener(Ctrl ctrl) {
		this.btnSenior.setActionCommand("MainView_senior");
		this.btnSenior.addActionListener(ctrl);
		this.btnInscription.setActionCommand("MainView_inscription");
		this.btnInscription.addActionListener(ctrl);	
	}
}
