package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import library.Persistance;
import model.Activite;
import model.Seance;
import model.Senior;
import view.AjoutSenior;
import view.ChoixSeances;
import view.ChoixSeniorList;
/**
 * Classe CONTROLEUR
 * @author xavier
 *
 */
public class Ctrl implements ActionListener{

	/**
	 * CONSTRUCTEUR priv� de la classe Ctrl
	 */
	private Ctrl(){
	}
	/**
	 * Instance unique pr�-initialis�e
	 */
	private static Ctrl CTRL = new Ctrl();
	/**
	 * Point d'acc�s pour l'instance unique du singleton
	 * @return le singleton Ctrl
	 */
	public static Ctrl getCtrl(){
		return CTRL;
	}

	/**
	 * M�thode d�clanch�e lors de clics sur les boutons de l'application
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		//R�cup�ration de l'action
		String action = evt.getActionCommand();
		//D�coupage et analyse de celle-ci
		String details[] = action.split("_");
		//who : QUI ? Quelle vue a effectu� l'action ?
		String who = details[0];
		//what : QUOI ? Qu'est-ce-que cette vue souhaite faire ?
		String what = details[1];
		//switch-case de traitement des diff�rents cas
		switch(who){
		case "MainView":
			switch(what){
			case "senior":
				//Cr�ation de la vue d'ajout d'un s�nior
				AjoutSenior frame = new AjoutSenior();
				//Assignation d'un observateur sur cette vue
				frame.assignListener(this);
				//Affichage de la vue
				frame.setVisible(true);
				break;
			case "inscription":
				//Cr�ation des listes qui vont contenir les seniors et activit�s � afficher
				ArrayList<Senior> liste=new ArrayList<Senior>();
				ArrayList<Activite> listeA = new ArrayList<Activite>();
				try {
					//Affectation des valeurs de la base de donn�e dans les listes associ�s
					liste = Persistance.selectSenior();
					listeA = Persistance.selectActivite();
				} catch (SQLException e) {
					String message = "Erreur lors de l'echange avec la base de donn�es. L'application a rencontr�e l'erreur : "+e.getMessage();
					JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
				}
				//Cr�ation de la vue de s�lection du s�nior pour lequel on souhaite effectuer des inscriptions
				ChoixSeniorList frame1 = new ChoixSeniorList(liste, listeA);
				//Assignation d'un observateur sur cette vue
				frame1.assignListener(this);
				//Affichage de la vue
				frame1.setVisible(true);
				break;
			}
			break;
		case "AjoutSenior":
			switch(what){
			case "valider":
				//R�cup�ration des informations saisies par l'utilisateur
				String nom = AjoutSenior.getTxtName();
				if(nom.equals("")){
					JOptionPane.showMessageDialog(null,"Le nom du s�nior � �t� omis. Veillez � le saisir correctement.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
					AjoutSenior.focusTxtName();
				}
				else{
					String nomS = AjoutSenior.getTxtName();
					String numS = AjoutSenior.getTxtNumSecu();
					//Cr�ation du nouvel objet Senior
					Senior senior = new Senior(numS,nomS);
					//INSERT dans la BD
					try {
						Persistance.insertSenior(senior.getNom(),senior.getNumSecu());
						//Message de confirmation pour l'utilisateur
						JOptionPane.showMessageDialog(null,"Le s�nior a bien �t� ajout�","Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);
						//R�initialisation des champs
						AjoutSenior.init();
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de donn�es. L'application a rencontr�e l'erreur : "+e.getMessage();
						JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
					}
				}
				break;
			case "annuler":
				//R�initialisation des champs
				AjoutSenior.init();	
				break;
			}
			break;
		case "InscriptionSenior":
			switch(what){
			case "valider":
				//R�cup�ration des donn�es s�l�ctionn�s par l'utilisateur
				String act = ChoixSeniorList.getActiviteName();
				String senior = ChoixSeniorList.getSeniorName();
				//Cr�ation de la liste qui va contenir les dates des s�ances de l'activit� s�lectionn�e
				ArrayList<String> date = ChoixSeances.getSeanceDate();
				if(act.equals("")){
					JOptionPane.showMessageDialog(null,"Aucune activit� n'� �t� s�l�ctionn�e.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
					//ChoixSenoirList.focusTxtName();
				}else if(senior.equals("")){
					JOptionPane.showMessageDialog(null, "Aucun senior n'a �t� reseign�.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
				}else{
					//INSERT dans la BD
					try {
						//Affectation des donn�es de la BDD dans des listes
						ArrayList<Senior> liste=  Persistance.selectSenior();
						ArrayList<Activite> listeA = Persistance.selectActivite();
						ArrayList<Seance> listeS = Persistance.selectSeance(ChoixSeniorList.getActiviteName());
						
						//R�cup�ration des object complet � partir du seul String s�lectionn�
						Activite laActivite = null;
						Senior leSenior = null;
						ArrayList<Seance> lesSeances = new ArrayList<Seance>();
						
						for(Activite elle : listeA){
							if(elle.getDesignation().equals(act))
								laActivite = elle;
						}
						
						for(Senior lui : liste){
							if(lui.getNom().equals(senior))
								leSenior = lui;
						}
						
						
						for(Seance it : listeS){
							for(String laDate : date){
								if(it.getDateSeance().toString().equals(laDate) == true){
									lesSeances.add(it);
								}
							}
						}
						
						//Ajout de la participation du s�nior � une s�ance dans la base de donn�e
						Persistance.associeSeniorActivite(lesSeances, leSenior.getNumSecu());
						//Message de confirmation pour l'utilisateur
						JOptionPane.showMessageDialog(null,"Le s�nior a bien �t� ajout� au(x) cour(s) de l'activit�"+laActivite.getDesignation(),"Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);
						
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de donn�es. L'application a rencontr�e l'erreur : "+e.getMessage();
						JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
					}

				}
				break;
				case "voirSeances":
					String activite = ChoixSeniorList.getActiviteName();
					ArrayList<Seance> liste = new ArrayList<Seance>();
					try {
						liste = Persistance.selectSeance(activite);
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de donn�es. L'application a rencontr�e l'erreur : "+e.getMessage();
						JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
					}
					ChoixSeances frame2 = new ChoixSeances(liste);
					//Assignation d'un observateur sur cette vue
					frame2.assignListener(this);
					//Affichage de la vue
					frame2.setVisible(true);
				break;
			}
			break;	
		}
	}
}
