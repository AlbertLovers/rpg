package entities;

import java.util.List;

import dao.Cache;

public class Talent {

	private Long id;

	private String naam;

	private String omschrijving;

	private int tier;

	private List<Skill> benodigdeSkills;

	private List<Talent> benodigdeTalents;

	private Characteristics characteristics;

	private Long aptitude1Id;

	private Long aptitude2Id;

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

	public Long getAptitude1Id() {
		return aptitude1Id;
	}

	public void setAptitude1Id(Long aptitude1Id) {
		this.aptitude1 = Cache.getAptitude(aptitude1Id);
		this.aptitude1Id = aptitude1Id;
	}

	public Long getAptitude2Id() {
		return aptitude2Id;
	}

	public void setAptitude2Id(Long aptitude2Id) {
		this.aptitude2 = Cache.getAptitude(aptitude2Id);
		this.aptitude2Id = aptitude2Id;
	}

	public Aptitude getAptitude1() {
		return aptitude1;
	}

	public Aptitude getAptitude2() {
		return aptitude2;
	}

	public int getTier() {
		return tier;
	}

	public List<Skill> getBenodigdeSkills() {
		return benodigdeSkills;
	}

	public List<Talent> getBenodigdeTalents() {
		return benodigdeTalents;
	}

	public Characteristics getCharacteristics() {
		return characteristics;
	}

	public void setTier(int tier) {
		this.tier = tier;
	}

	public void setBenodigdeSkills(List<Skill> benodigdeSkills) {
		this.benodigdeSkills = benodigdeSkills;
	}

	public void setBenodigdeTalents(List<Talent> benodigdeTalents) {
		this.benodigdeTalents = benodigdeTalents;
	}

	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}

	@Override
	public String toString() {
		return naam;
	}
}
