package domein;

import annotation.Id;
import annotation.Transient;
import dao.Cache;

public class Skill {

	@Id
	private int id;
	
	private String naam;

	private String omschrijving;
	
	private int aptitude1Id;
	
	private int aptitude2Id;

	@Transient
	private Aptitude aptitude1;
	
	@Transient
	private Aptitude aptitude2;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public int getAptitude1Id() {
		return aptitude1Id;
	}

	public void setAptitude1Id(int aptitude1Id) {
		this.aptitude1 = Cache.getAptitude(aptitude1Id);
		this.aptitude1Id = aptitude1Id;
	}

	public int getAptitude2Id() {
		return aptitude2Id;
	}

	public void setAptitude2Id(int aptitude2Id) {
		this.aptitude2 = Cache.getAptitude(aptitude2Id);
		this.aptitude2Id = aptitude2Id;
	}

	public Aptitude getAptitude1() {
		return aptitude1;
	}

	public Aptitude getAptitude2() {
		return aptitude2;
	}
	
	@Override
	public String toString() {
		return naam;
	}
}
