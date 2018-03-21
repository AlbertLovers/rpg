package view;

import java.util.ArrayList;
import java.util.List;

import app.Start;
import domein.Aptitude;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SkillView implements View {

	private Scene scene;
	private GridPane gridPane;

	private final TextField naamField = new TextField();
	private final TextArea omschrijvingArea = new TextArea();
	private final List<CheckBox> aptitudeBoxesList = new ArrayList<>();
	
	public SkillView() {
		buildGridPane();
		populateGridPane();
		gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		scene = new Scene(gridPane, gridPane.getMaxWidth(), gridPane.getMaxHeight());
	}

	private void populateGridPane() {
		addHeaders();
		addOtherNodes();
	}

	private void addOtherNodes() {		
		Button hoofdMenuButton = new Button("Hoofdmenu");
		hoofdMenuButton.setOnAction(e -> Start.mainMenuController.setScene());
		
		gridPane.add(naamField, 0, 1);
		gridPane.add(omschrijvingArea, 1, 1);
		
		gridPane.add(hoofdMenuButton, 2, 2);
		
		GridPane.setValignment(naamField, VPos.TOP);
	}

	private void addHeaders() {
		gridPane.add(new Label("Naam"), 0, 0);
		gridPane.add(new Label("Omschrijving"), 1, 0);
		gridPane.add(new Label("Aptitudes"), 2, 0);
	}

	private void buildGridPane() {
		gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(10));
	}

	public void addAptitudes(final List<Aptitude> aptitudes) {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5));
		vBox.setSpacing(5);
		ObservableList<Node> children = vBox.getChildren();

		aptitudes.forEach(a -> {
			CheckBox c = new CheckBox(a.getNaam());
			aptitudeBoxesList.add(c);
			children.add(c);
		});

		gridPane.add(vBox, 2, 1);
	}

	@Override
	public void setScene() {
		Start.setScene(scene);
	}
}
