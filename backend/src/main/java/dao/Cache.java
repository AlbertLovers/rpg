package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Aptitude;
import entities.Skill;

public class Cache {

	private Cache() {};

	private static SkillDao skillDao = new SkillDao();
	private static AptitudeDao aptitudeDao = new AptitudeDao();

	private static List<Aptitude> aptitudes = new ArrayList<>();
	private static List<Skill> skills = new ArrayList<>();

	static {
		try {
			// Aptitudes altijd als eerste, want later belangrijk!
			refreshAptitudes();
			refreshSkills();
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static Skill getSkill(Long id) {
		for(Skill skill : skills) {
			if(skill.getId() == id) return skill;
		}
		return null;
	}

	public static Aptitude getAptitude(Long id) {
		for(Aptitude aptitude : aptitudes) {
			if(aptitude.getId() == id) return aptitude;
		}
		return null;
	}

	public static Aptitude getAptitude(String naam) {
		for(Aptitude aptitude : aptitudes) {
			if(aptitude.getNaam().equals(naam)) return aptitude;
		}
		return null;
	}
	
	public static List<Aptitude> getAptitudes() {
		return aptitudes;
	}

	public static List<Skill> getSkills() {
		return skills;
	}

	public static void refreshAptitudes() throws SQLException {
		aptitudes = aptitudeDao.getAptitudes();
	}

	public static void refreshSkills() throws SQLException {
		skills = skillDao.getSkills();
	}
}
