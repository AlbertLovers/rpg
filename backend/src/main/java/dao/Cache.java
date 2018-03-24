package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Aptitude;
import domein.Skill;

public class Cache {

	private Cache() {};

	private static SkillDao skillDao = new SkillDao();
	private static AptitudeDao aptitudeDao = new AptitudeDao();

	private static List<Aptitude> aptitudes = new ArrayList<>();
	private static List<Skill> skills = new ArrayList<>();

	static {
		try {
			refreshAptitudes();
			refreshSkills();
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static Skill getSkill(int id) {
		for(Skill skill : skills) {
			if(skill.getId() == id) return skill;
		}
		return null;
	}

	public static Aptitude getAptitude(int id) {
		for(Aptitude aptitude : aptitudes) {
			if(aptitude.getId() == id) return aptitude;
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
