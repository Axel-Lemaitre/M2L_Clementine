package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe d'objet m�tier SENIOR
 * 
 * @author xavier
 *
 */
@Entity
@Table(name="senior")
public class Senior {
	/**
	 * Le num�ro de s�curit� sociale du s�nior
	 */
	@Id
	@Column(name="numsecu")
	private String numSecu;
	
	/**
	 * Le nom du s�nior
	 */
	@Column(name="nom")
	private String nom;

	/**
	 * CONSTRUCTEUR de la classe Senior
	 * 
	 * @param numSecu
	 *            num�ro de s�curit� sociale du nouveau s�nior
	 * @param nom
	 *            nom du nouveau s�nior
	 */
	public Senior(String numSecu, String nom) {
		super();
		this.numSecu = numSecu;
		this.nom = nom;
	}

	/**
	 * Accesseur en lecture sur le num�ro de s�curit� sociale du s�nior
	 * 
	 * @return le num�ro de s�curit� sociale du s�nior
	 */
	public String getNumSecu() {
		return numSecu;
	}

	/**
	 * Accesseur en lecture sur le nom du s�nior
	 * 
	 * @return le nom du s�nior
	 */
	public String getNom() {
		return nom;
	}
}
