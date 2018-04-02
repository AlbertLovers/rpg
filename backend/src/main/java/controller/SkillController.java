package controller;

import java.util.Map;

import dao.Cache;
import dao.SkillDao;
import entities.Skill;
import entities.mapper.SkillMapper;
import view.SkillView;

public class SkillController implements Controller {

	private SkillView skillView;
	private final SkillDao skillDao = new SkillDao();

	public SkillController() {
		skillView = new SkillView();
		skillView.addAptitudes(Cache.getAptitudes());
		skillView.setSkillsInListView(Cache.getSkills());
		skillView.setOpslaanButtonListener(e -> {
			Map<String, Object> skillMap = skillView.getValues();
			Skill skill = SkillMapper.map(skillMap);

			if(skill.getId() == 0) {
				skillDao.insertObject(skill);
			} else {
				skillDao.updateObject(skill);
			}
		});
	}

	@Override
	public void setScene() {
		skillView.setScene();
	}

}
