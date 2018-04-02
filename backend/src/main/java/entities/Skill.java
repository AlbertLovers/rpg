package entities;

import dao.Cache;

public class Skill {

	private Long id;

	private String naam;

	private String omschrijving;

	private int level;

	private Aptitude aptitude1;

	private Aptitude aptitude2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setAptitude1ById(Long aptitude1Id) {
		this.aptitude1 = Cache.getAptitude(aptitude1Id);
	}

	public void setAptitude2ById(Long aptitude2Id) {
		this.aptitude2 = Cache.getAptitude(aptitude2Id);
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
