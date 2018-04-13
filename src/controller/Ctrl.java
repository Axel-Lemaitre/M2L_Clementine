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
	 * CONSTRUCTEUR privé de la classe Ctrl
	 */
	private Ctrl(){
	}
	/**
	 * Instance unique pré-initialisée
	 */
	private static Ctrl CTRL = new Ctrl();
	/**
	 * Point d'accès pour l'instance unique du singleton
	 * @return le singleton Ctrl
	 */
	public static Ctrl getCtrl(){
		return CTRL;
	}

	/**
	 * Méthode déclanchée lors de clics sur les boutons de l'application
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		//Récupération de l'action
		String action = evt.getActionCommand();
		//Découpage et analyse de celle-ci
		String details[] = action.split("_");
		//who : QUI ? Quelle vue a effectué l'action ?
		String who = details[0];
		//what : QUOI ? Qu'est-ce-que cette vue souhaite faire ?
		String what = details[1];
		//switch-case de traitement des différents cas
		switch(who){
		case "MainView":
			switch(what){
			case "senior":
				//Création de la vue d'ajout d'un sénior
				AjoutSenior frame = new AjoutSenior();
				//Assignation d'un observateur sur cette vue
				frame.assignListener(this);
				//Affichage de la vue
				frame.setVisible(true);
				break;
			case "inscription":
				//Création des listes qui vont contenir les seniors et activités à afficher
				ArrayList<Senior> liste=new ArrayList<Senior>();
				ArrayList<Activite> listeA = new ArrayList<Activite>();
				try {
					//Affectation des valeurs de la base de donnée dans les listes associés
					liste = Persistance.selectSenior();
					listeA = Persistance.selectActivite();
				} catch (SQLException e) {
					String message = "Erreur lors de l'echange avec la base de données. L'application a rencontrée l'erreur : "+e.getMessage();
					JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
				}
				//Création de la vue de sélection du sénior pour lequel on souhaite effectuer des inscriptions
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
				//Récupération des informations saisies par l'utilisateur
				String nom = AjoutSenior.getTxtName();
				if(nom.equals("")){
					JOptionPane.showMessageDialog(null,"Le nom du sénior à été omis. Veillez à le saisir correctement.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
					AjoutSenior.focusTxtName();
				}
				else{
					String nomS = AjoutSenior.getTxtName();
					String numS = AjoutSenior.getTxtNumSecu();
					//Création du nouvel objet Senior
					Senior senior = new Senior(numS,nomS);
					//INSERT dans la BD
					try {
						Persistance.insertSenior(senior.getNom(),senior.getNumSecu());
						//Message de confirmation pour l'utilisateur
						JOptionPane.showMessageDialog(null,"Le sénior a bien été ajouté","Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);
						//Réinitialisation des champs
						AjoutSenior.init();
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de données. L'application a rencontrée l'erreur : "+e.getMessage();
						JOptionPane.showMessageDialog(null,message,"Erreur SQL",JOptionPane.ERROR_MESSAGE);
					}
				}
				break;
			case "annuler":
				//Réinitialisation des champs
				AjoutSenior.init();	
				break;
			}
			break;
		case "InscriptionSenior":
			switch(what){
			case "valider":
				//Récupération des données séléctionnés par l'utilisateur
				String act = ChoixSeniorList.getActiviteName();
				String senior = ChoixSeniorList.getSeniorName();
				//Création de la liste qui va contenir les dates des séances de l'activité sélectionnée
				ArrayList<String> date = ChoixSeances.getSeanceDate();
				if(act.equals("")){
					JOptionPane.showMessageDialog(null,"Aucune activité n'à été séléctionnée.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
					//ChoixSenoirList.focusTxtName();
				}else if(senior.equals("")){
					JOptionPane.showMessageDialog(null, "Aucun senior n'a été reseigné.","Erreur Saisie",JOptionPane.WARNING_MESSAGE);
				}else{
					//INSERT dans la BD
					try {
						//Affectation des données de la BDD dans des listes
						ArrayList<Senior> liste=  Persistance.selectSenior();
						ArrayList<Activite> listeA = Persistance.selectActivite();
						ArrayList<Seance> listeS = Persistance.selectSeance(ChoixSeniorList.getActiviteName());
						
						//Récupération des object complet à partir du seul String sélectionné
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
						
						//Ajout de la participation du sénior à une séance dans la base de donnée
						Persistance.associeSeniorActivite(lesSeances, leSenior.getNumSecu());
						//Message de confirmation pour l'utilisateur
						JOptionPane.showMessageDialog(null,"Le sénior a bien été ajouté au(x) cour(s) de l'activité"+laActivite.getDesignation(),"Confirmation Enregistrement",JOptionPane.INFORMATION_MESSAGE);
						
					} catch (SQLException e) {
						String message = "Erreur lors de l'echange avec la base de données. L'application a rencontrée l'erreur : "+e.getMessage();
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
						String message = "Erreur lors de l'echange avec la base de données. L'application a rencontrée l'erreur : "+e.getMessage();
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
