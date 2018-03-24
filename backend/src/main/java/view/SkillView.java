package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Start;
import domein.Aptitude;
import domein.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SkillView implements View {

	private Scene scene;
	private GridPane gridPane;

	private final Button opslaanButton = new Button("Opslaan");

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
			if(newValue == null) {
				opslaanButton.setText("Opslaan");
			} else {
				opslaanButton.setText("Updaten");
				naamField.setText(newValue.getNaam());
				omschrijvingArea.setText(newValue.getOmschrijving());

				aptitudeBoxesList.forEach(box -> {
					if(box.getText().equals(newValue.getAptitude1().getNaam()) || box.getText().equals(newValue.getAptitude2().getNaam())) {
						box.setSelected(true);
					} else {
						box.setSelected(false);
					}
				});
			}
		});
	}

	private void populateGridPane() {
		addHeaders();
		addOtherNodes();
	}

	private void addOtherNodes() {
		HBox buttonBox = new HBox();
		Button hoofdMenuButton = new Button("Hoofdmenu");
		hoofdMenuButton.setOnAction(e -> Start.mainMenuController.setScene());

		Button clearSelectie = new Button("Nieuw");

		clearSelectie.setOnAction(e -> {
			skillListView.getSelectionModel().clearSelection();
			naamField.setText("");
			omschrijvingArea.setText("");
			aptitudeBoxesList.forEach(box -> box.setSelected(false));
		});

		buttonBox.getChildren().add(clearSelectie);
		buttonBox.getChildren().add(opslaanButton);
		buttonBox.getChildren().add(hoofdMenuButton);

		gridPane.add(skillListView, 0, 1);
		gridPane.add(naamField, 1, 1);
		gridPane.add(omschrijvingArea, 2, 1);

		gridPane.add(buttonBox, 3, 2);

		GridPane.setValignment(naamField, VPos.TOP);
	}

	private void addHeaders() {
		gridPane.add(new Label("Skills"), 0, 0);
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
		Node toDelete = null;
		for(Node node : gridPane.getChildren()) {
			if(GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 3) {
				toDelete = node;
			}
		}
		
		if(toDelete != null) gridPane.getChildren().remove(toDelete);

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5));
		vBox.setSpacing(5);
		ObservableList<Node> children = vBox.getChildren();

		aptitudes.forEach(a -> {
			CheckBox c = new CheckBox(a.getNaam());
			aptitudeBoxesList.add(c);
			children.add(c);
		});

		gridPane.add(vBox, 3, 1);
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

	public void setOpslaanButtonListener(EventHandler<ActionEvent> handler) {
		opslaanButton.setOnAction(handler);
	}

	public Map<String, Object> getValues() {
		Map<String, Object> map = new HashMap<>();

		Skill skill = skillListView.getSelectionModel().getSelectedItem();

		if(skill != null) {
			map.put("id", skill.getId());
		}

		map.put("naam", naamField.getText());
		map.put("omschrijving", omschrijvingArea.getText());
		int count = 0;
		for(CheckBox box : aptitudeBoxesList) {
			if(box.isSelected()) {
				count++;
				map.put("aptitude" + count, box.getText());
			}

			if(count == 2) break;
		}

		return map;
	}
}
