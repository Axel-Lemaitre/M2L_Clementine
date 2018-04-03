package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe d'objet métier SENIOR
 * 
 * @author xavier
 *
 */
@Entity
@Table(name="senior")
public class Senior {
	/**
	 * Le numéro de sécurité sociale du sénior
	 */
	@Id
	@Column(name="numsecu")
	private String numSecu;
	
	/**
	 * Le nom du sénior
	 */
	@Column(name="nom")
	private String nom;

	/**
	 * CONSTRUCTEUR de la classe Senior
	 * 
	 * @param numSecu
	 *            numéro de sécurité sociale du nouveau sénior
	 * @param nom
	 *            nom du nouveau sénior
	 */
	public Senior(String numSecu, String nom) {
		super();
		this.numSecu = numSecu;
		this.nom = nom;
	}

	/**
	 * Accesseur en lecture sur le numéro de sécurité sociale du sénior
	 * 
	 * @return le numéro de sécurité sociale du sénior
	 */
	public String getNumSecu() {
		return numSecu;
	}

	/**
	 * Accesseur en lecture sur le nom du sénior
	 * 
	 * @return le nom du sénior
	 */
	public String getNom() {
		return nom;
	}
}
