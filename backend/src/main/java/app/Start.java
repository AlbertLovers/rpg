package app;

import controller.Controller;
import controller.MainMenuController;
import controller.TalentController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application{

	private static final Stage mainStage = new Stage();

	public static final Controller mainMenuController = new MainMenuController();
	public static final Controller talentController = new TalentController();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage mainStage) {
		Start.mainStage.show();
		Start.mainStage.setResizable(false);
		mainMenuController.setScene();
	}

	public static void setScene(Scene scene) {
		mainStage.setScene(scene);
		mainStage.sizeToScene();
	}

}