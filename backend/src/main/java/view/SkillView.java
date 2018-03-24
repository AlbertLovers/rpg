package view;

import java.util.ArrayList;
import java.util.List;

import app.Start;
import domein.Aptitude;
import domein.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	
	private final ObservableList<Skill> skillList = FXCollections.observableArrayList();
	private final ListView<Skill> skillListView = new ListView<>();
	
	public SkillView() {
		buildGridPane();
		buildSkillListView();
		populateGridPane();
		gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		scene = new Scene(gridPane, gridPane.getMaxWidth(), gridPane.getMaxHeight());
	}

	private void buildSkillListView() {
		skillListView.setItems(skillList);
		skillListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			naamField.setText(newValue.getNaam());
			omschrijvingArea.setText(newValue.getOmschrijving());

			aptitudeBoxesList.forEach(box -> {
				if(box.getText().equals(newValue.getAptitude1().getNaam()) || box.getText().equals(newValue.getAptitude2().getNaam())) {
					box.setSelected(true);
				} else {
					box.setSelected(false);
				}
			});
		});
	}

	private void populateGridPane() {
		addHeaders();
		addOtherNodes();
	}

	private void addOtherNodes() {		
		Button hoofdMenuButton = new Button("Hoofdmenu");
		hoofdMenuButton.setOnAction(e -> Start.mainMenuController.setScene());
		
		gridPane.add(naamField, 1, 1);
		gridPane.add(naamField, 1, 1);
		gridPane.add(omschrijvingArea, 2, 1);
		
		gridPane.add(hoofdMenuButton, 3, 2);
		
		GridPane.setValignment(naamField, VPos.TOP);
	}

	private void addHeaders() {
		gridPane.add(new Label("Skills"), 1, 0);
		gridPane.add(new Label("Naam"), 1, 0);
		gridPane.add(new Label("Omschrijving"), 2, 0);
		gridPane.add(new Label("Aptitudes"), 3, 0);
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

	public void setSkillsInListView(List<Skill> newSkillList) {
		newSkillList.sort((a, b) -> a.getNaam().compareTo(b.getNaam()));
		
		this.skillList.clear();
		this.skillList.addAll(newSkillList);
	}
	
	public Skill getSelectedSkill() {
		return skillListView.selectionModelProperty().getValue().getSelectedItem();
	}
	
	@Override
	public void setScene() {
		Start.setScene(scene);
	}
}
