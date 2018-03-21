package view;

import app.Start;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class MainView implements View {

	private Scene scene;
	
	private final Button naarTalentsButton = new Button("Talents");
	private final Button naarSkillsButton = new Button("Skills");
	private final Button naarAptitudesButton = new Button("Aptitudes");
	private final Button naarHoofdmenuButton = new Button("Hoofdmenu");

	public MainView() {
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(5));
		hBox.setSpacing(5);
		hBox.getChildren().addAll(new Node[] { naarAptitudesButton, naarSkillsButton, naarTalentsButton });

		hBox.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		scene = new Scene(hBox, hBox.getMaxWidth(), hBox.getMaxHeight());
	}

	@Override
	public void setScene() {
		Start.setScene(scene);
	}
	
	public void setAptitudeButtonListener(EventHandler<ActionEvent> aptitudesEvent) {
		naarAptitudesButton.setOnAction(aptitudesEvent);
	}

	public void setSkillButtonListener(EventHandler<ActionEvent> skillsEvent) {
		naarSkillsButton.setOnAction(skillsEvent);
	}

	public void setTalentButtonListener(EventHandler<ActionEvent> talentsEvent) {
		naarTalentsButton.setOnAction(talentsEvent);
	}

	public void setHoofdmenuButtonListener(EventHandler<ActionEvent> hoofdmenuEvent) {
		naarHoofdmenuButton.setOnAction(hoofdmenuEvent);
	}
}