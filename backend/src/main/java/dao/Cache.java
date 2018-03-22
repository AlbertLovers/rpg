package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domein.Aptitude;
import domein.Skill;

public class Cache {

	private static SkillDao skillDao = new SkillDao();
	private static AptitudeDao aptitudeDao = new AptitudeDao();
	
	private static Map<Integer, Aptitude> aptitudes = new HashMap<>();
	private static Map<Integer, Skill> skills = new HashMap<>();

	static {
		try {
			setAptitudes(aptitudeDao.getAptitudes());
			setSkills(skillDao.getSkills());
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static void setAptitudes(List<Aptitude> aptitudes) {
		for(Aptitude apt : aptitudes) {
			Cache.aptitudes.put(apt.getId(), apt);
		}
	}
	
	private static void setSkills(List<Skill> skills) {
		for(Skill skill : skills) {
			Cache.skills.put(skill.getId(), skill);
		}
	}

	public static Skill getSkill(int id) {
		return skills.get(id);
	}
	
	public static Aptitude getAptitude(int id) {
		return aptitudes.get(id);
	}
	
}
