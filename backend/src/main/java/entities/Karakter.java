package entities;

import java.util.List;

public class Karakter {

	private List<Aptitude> aptitudes;

	private List<Talent> talents;

	private List<Skill> skills;

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
