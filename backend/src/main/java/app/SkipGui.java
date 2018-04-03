package app;

import java.util.ArrayList;
import java.util.List;

import dao.Cache;
import dao.KarakterDao;
import entities.Aptitude;
import entities.Characteristics;
import entities.Karakter;
import entities.Skill;

public class SkipGui {

	static KarakterDao dao = new KarakterDao();
	
	public static void main(String[] args) {
		Karakter kar = new Karakter();
		kar.setNaam("Solar");
		
		List<Aptitude> aptitudes = new ArrayList<>();
		aptitudes.add(Cache.getAptitude(1L));
		aptitudes.add(Cache.getAptitude(3L));
		aptitudes.add(Cache.getAptitude(4L));
		aptitudes.add(Cache.getAptitude(7L));
		aptitudes.add(Cache.getAptitude(9L));
		
		Characteristics chars = new Characteristics();
		chars.setWeaponSkill(41);
		chars.setBallisticSkill(35);
		chars.setStrength(46);
		chars.setToughness(51);
		chars.setAgility(36);
		chars.setIntelligence(31);
		chars.setPerception(37);
		chars.setWillpower(36);
		chars.setFellowship(49);
		
		List<Skill> skills = new ArrayList<>();
		skills.add(Cache.getSkill(1L));
		skills.add(Cache.getSkill(3L));
		skills.add(Cache.getSkill(6L));
		skills.add(Cache.getSkill(10L));

		kar.setAptitudes(aptitudes);
		kar.setCharacteristics(chars);
		kar.setSkills(skills);
		
		dao.createCharacter(kar);
	}

}
