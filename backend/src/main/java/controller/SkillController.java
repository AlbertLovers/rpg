package controller;

import dao.Cache;
import view.SkillView;

public class SkillController implements Controller {

	private SkillView skillView;

	public SkillController() {
		skillView = new SkillView();
		skillView.addAptitudes(Cache.getAptitudes());
		skillView.setSkillsInListView(Cache.getSkills());
	}

	@Override
	public void setScene() {
		skillView.setScene();
	}

}
