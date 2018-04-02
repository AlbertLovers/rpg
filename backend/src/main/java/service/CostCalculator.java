package service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Aptitude;
import entities.Karakter;
import entities.Skill;
import entities.Talent;

public class CostCalculator {

	public int calculateTalentCost(Karakter karakter, Talent gewensteTalent) {
		int kosten = 0;

		Set<Talent> teKopenTalents = new HashSet<>();
		teKopenTalents.add(gewensteTalent);
		teKopenTalents.addAll(getAllTalents(karakter.getTalents(), gewensteTalent));		
		for(Talent talent : teKopenTalents) {
			kosten += talentCost(talent, karakter.getAptitudes());
		}

		Set<Skill> teKopenSkills = new HashSet<>();
		teKopenSkills.addAll(vindTeKopenSkills(teKopenTalents, karakter.getSkills()));
		for(Skill skill : teKopenSkills) {
			kosten += skillCost(skill, karakter.getAptitudes(), karakter.getSkills());
		}

		return kosten;
	}

	private int skillCost(Skill teKopenSkill, List<Aptitude> aptitudes, List<Skill> karakterSkills) {
		int aantalAptitudes = 0;
		int kosten = 0;
		int level;

		if(aptitudes.contains(teKopenSkill.getAptitude1())) aantalAptitudes++;
		if(aptitudes.contains(teKopenSkill.getAptitude2())) aantalAptitudes++;

		int index = karakterSkills.indexOf(teKopenSkill);
		if(index > -1 ) {
			Skill alBezetenSkill = karakterSkills.get(index);
			level = alBezetenSkill.getLevel();
		} else {
			level = -1;
		}

		for(int i = teKopenSkill.getLevel(); i > level; i--) {
			kosten += 100 * (3 - aantalAptitudes) * i;
		}

		return kosten;
	}

	private int talentCost(Talent talent, List<Aptitude> karakterAptitudes) {
		int aantalAptitudes = 0;

		if(karakterAptitudes.contains(talent.getAptitude1())) aantalAptitudes++;
		if(karakterAptitudes.contains(talent.getAptitude2())) aantalAptitudes++;

		return (int)(600 / (1.0 + aantalAptitudes) * (0.5 + 0.5 * talent.getTier()));
	}

	private Collection<Skill> vindTeKopenSkills(Set<Talent> teKopenTalents, List<Skill> skills) {
		Set<Skill> teKopenSkills = new HashSet<>();

		teKopenTalents.forEach(talent -> talent.getBenodigdeSkills().forEach(skill -> {
			for(Skill karakterSkill : skills) {
				if(skill.equals(karakterSkill)) {
					if(skill.getLevel() > karakterSkill.getLevel()) {
						teKopenSkills.add(skill);
					}

					break;
				}
			}
		}));

		return teKopenSkills;
	}

	private Set<Talent> getAllTalents(List<Talent> bezetenTalents, Talent talent) {
		Set<Talent> teKopenTalents = new HashSet<>();

		talent.getBenodigdeTalents().forEach(subTalent -> {
			if(!bezetenTalents.contains(talent)) {
				teKopenTalents.add(talent);
				teKopenTalents.addAll(getAllTalents(bezetenTalents, subTalent));
			}
		});

		return teKopenTalents;
	}

}
