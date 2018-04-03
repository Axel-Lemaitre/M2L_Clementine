package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seance")
public class Seance {

	@Id
	@Column(name="code")
	private int code;
	
	@Column(name="dateseance")
	private Date dateSeance;

	public Seance(int code, Date dateSeance) {
		super();
		this.code = code;
		this.dateSeance = dateSeance;
	}

	public Seance() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Date getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}
	
	
	
}
