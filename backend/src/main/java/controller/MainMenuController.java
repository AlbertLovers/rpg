package controller;

import app.Start;
import view.MainView;

public class MainMenuController implements Controller {

	private MainView mainView;

	public MainMenuController() {
		mainView = new MainView();
		mainView.setTalentButtonListener(e -> Start.talentController.setScene());
		mainView.setSkillButtonListener(e -> Start.skillController.setScene());
	}
	
	@Override
	public void setScene() {
		mainView.setScene();
	}
}