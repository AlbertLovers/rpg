package view.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;

public class SceneUtil {

	public static Scene sizeScene(Parent node) {
		return new Scene(node, node.maxWidth(Region.USE_COMPUTED_SIZE), node.maxHeight(Region.USE_COMPUTED_SIZE));
	}
}