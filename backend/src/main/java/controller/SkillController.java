package controller;

import view.SkillView;

public class SkillController implements Controller {

	private SkillView skillView;
	
	public SkillController() {
		skillView = new SkillView();
	}
	
	@Override
	public void setScene() {
		skillView.setScene();
	}

}
