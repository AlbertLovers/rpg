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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TalentView implements View {

	private Scene scene;

	private final TextField naamField = new TextField();
	private final TextField tierField = new TextField();
	private final List<CheckBox> aptitudeBoxes = new ArrayList<>();
	private final GridPane gridPane = new GridPane();
	private final TextArea omschrijvingArea = new TextArea();

	private final TextField ws = new TextField("0");
	private final TextField bs = new TextField("0");
	private final TextField st = new TextField("0");
	private final TextField to = new TextField("0");
	private final TextField ag = new TextField("0");
	private final TextField in = new TextField("0");
	private final TextField pe = new TextField("0");
	private final TextField wp = new TextField("0");
	private final TextField fe = new TextField("0");

	public TalentView() {
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(10));

		populateGridPane();
		
		gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		scene = new Scene(gridPane, gridPane.getMaxWidth(), gridPane.getMaxHeight());
	}

	private void populateGridPane() {
		addHeaders();
		gridPane.add(naamField, 0, 1);	
		gridPane.add(omschrijvingArea, 1, 1);
		addCharacteristicFields();
		gridPane.add(tierField, 4, 1);

		GridPane.setValignment(naamField, VPos.TOP);
		GridPane.setValignment(tierField, VPos.TOP);

	}

	private void addCharacteristicFields() {
		VBox labels = new VBox();
		labels.setPadding(new Insets(5));
		labels.getChildren().addAll(new Node[]{new Label("ws"), new Label("bs"), new Label("st"), 
				new Label("to"), new Label("ag"), new Label("in"), new Label("pe"), 
				new Label("wp"), new Label("fe")});

		VBox fields = new VBox();
		fields.setPadding(new Insets(5));
		fields.getChildren().addAll(new Node[]{ws, bs, st, to, ag, in, pe, wp, fe});

		HBox combi = new HBox();
		combi.getChildren().add(labels);
		combi.getChildren().add(fields);
		gridPane.add(combi, 3, 1);
	}

	private void addHeaders() {
		gridPane.add(new Label("Naam"), 0, 0);
		gridPane.add(new Label("Omschrijving"), 1, 0);
		gridPane.add(new Label("Aptitudes"), 2, 0);
		gridPane.add(new Label("Characteristics"), 3, 0);
		gridPane.add(new Label("Tier"), 4, 0);
	}

	public void addAptitudes(final List<Aptitude> aptitudes) {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5));
		vBox.setSpacing(5);
		ObservableList<Node> children = vBox.getChildren();

		aptitudes.forEach(a -> {
			CheckBox c = new CheckBox(a.getNaam());
			aptitudeBoxes.add(c);
			children.add(c);
		});

		gridPane.add(vBox, 2, 1);
	}

	@Override
	public void setScene() {
		Start.setScene(scene);
	}
}
