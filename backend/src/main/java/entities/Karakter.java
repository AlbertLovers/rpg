package entities;

import java.util.List;

public class Karakter {

	private Long id;

	private String naam;

	private String omschrijving;

	private String persoonlijkheid;

	private Characteristics characteristics;

	private List<Aptitude> aptitudes;

	private List<Talent> talents;

	private List<Skill> skills;

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

	public String getPersoonlijkheid() {
		return persoonlijkheid;
	}

	public void setPersoonlijkheid(String persoonlijkheid) {
		this.persoonlijkheid = persoonlijkheid;
	}

	public Characteristics getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}

	public List<Aptitude> getAptitudes() {
		return aptitudes;
	}

	public void setAptitudes(List<Aptitude> aptitudes) {
		this.aptitudes = aptitudes;
	}

	public List<Talent> getTalents() {
		return talents;
	}

	public void setTalents(List<Talent> talents) {
		this.talents = talents;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
}
