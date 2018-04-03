package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activite")
public class Activite {
	
	/**
	 * L'identification de l'activite
	 */
	@Id
	@Column(name="identifiant")
	private int identifiant;
	/**
	 * La designation de l'activité
	 */
	@Column(name="designation")
	private String designation;
	
	/**
	 * 
	 */
	@Column(name="nbmax")
	private int nbmax;
	
	/**
	 * CONSTRUCTEUR de la classe Senior
	 * @param numSecu numéro de sécurité sociale du nouveau sénior
	 * @param nom nom du nouveau sénior
	 */
	public Activite(int identifiant, String designation, int nbmax) {
		super();
		this.identifiant = identifiant;
		this.designation = designation;
		this.nbmax = nbmax;
	}

	/**
	 * Accesseur en lecture sur le numéro de sécurité sociale du sénior
	 * @return le numéro de sécurité sociale du sénior
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * Accesseur en lecture sur le nom du sénior
	 * @return le nom du sénior
	 */
	public String getDesignation() {
		return designation;
	}
	
	public int getNbmax(){
		return nbmax;
	}
}
