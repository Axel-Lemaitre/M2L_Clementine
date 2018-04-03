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
	 * La designation de l'activit�
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
	 * @param numSecu num�ro de s�curit� sociale du nouveau s�nior
	 * @param nom nom du nouveau s�nior
	 */
	public Activite(int identifiant, String designation, int nbmax) {
		super();
		this.identifiant = identifiant;
		this.designation = designation;
		this.nbmax = nbmax;
	}

	/**
	 * Accesseur en lecture sur le num�ro de s�curit� sociale du s�nior
	 * @return le num�ro de s�curit� sociale du s�nior
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * Accesseur en lecture sur le nom du s�nior
	 * @return le nom du s�nior
	 */
	public String getDesignation() {
		return designation;
	}
	
	public int getNbmax(){
		return nbmax;
	}
}
