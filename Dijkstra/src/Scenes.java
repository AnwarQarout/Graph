import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import eu.hansolo.enzo.canvasled.Led;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Scenes {
	
	
	
	public static Scene mainScene() throws FileNotFoundException {
		
		File adjacents = new File("edges.txt");
		File cities = new File("verticies.txt");
		
		ComboBox sourceCB = new ComboBox();
		sourceCB.setLayoutX(247);
		sourceCB.setLayoutY(66.0);
		sourceCB.setPrefHeight(25);
		sourceCB.setPrefWidth(147.0);
		
		/* Dropdown content goes here */
		
		
		ComboBox destinationCB = new ComboBox();
		destinationCB.setLayoutX(477.0);
		destinationCB.setLayoutY(66.0);
		destinationCB.setPrefHeight(25);
		destinationCB.setPrefWidth(147.0);
		
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setLayoutX(16.0);
		scrollpane.setLayoutY(14.0);
		scrollpane.setPrefHeight(420.0);
		scrollpane.setPrefWidth(208.0);
		scrollpane.setPannable(true);
		InputStream stream = new FileInputStream("C:\\Users\\anwar\\Desktop\\Studies\\Textbooks\\Algorithms\\map.jpg");
		ImageView imageView = new ImageView();
		Image image = new Image(stream);
		imageView.setPickOnBounds(true);
		imageView.setPreserveRatio(true);
		imageView.setImage(image);
		
		Pane imagePane = new Pane();
		imagePane.getChildren().add(imageView);
		scrollpane.setContent(imagePane);
		
		Graph graph = Driver.readFile(cities, adjacents, sourceCB, destinationCB);
		
		for(String keys: graph.Vertices.keySet()) {
			City c = graph.Vertices.get(keys).getCity();
			Led led = new Led();
			led.setLayoutX(c.getX());
			led.setLayoutY(c.getY());
			Label label = new Label(c.getName());
			label.setLayoutX(c.getX());
			label.setLayoutY(c.getY());
			led.setPrefHeight(8);
			led.setPrefWidth(8);
			imagePane.getChildren().addAll(led,label);
			led.setId(c.getName());
			led.setOnMouseClicked(e->{
				if(sourceCB.getValue() == null) {
					sourceCB.setValue(led.getId());
				}
				else {
					destinationCB.setValue(led.getId());
				}
				
			}
			
			);
		}
		
		TextArea textarea = new TextArea();
		textarea.setLayoutX(22);
		textarea.setLayoutY(459.0);
		textarea.setPrefHeight(71.0);
		textarea.setPrefWidth(660.0);
		
		
		
		
		
		
		
		Button button = new Button("Calculate");
		button.setLayoutX(359.0);
		button.setLayoutY(218.0);
		button.setPrefHeight(78.0);
		button.setPrefWidth(147.0);
		button.setMnemonicParsing(false);
		button.setVisible(false);
		/* Dropdown content goes here */
		destinationCB.getItems().add("Jericho");
		Label label = new Label("To");
		label.setLayoutY(70);
		label.setLayoutX(426.0);
		
		
		
		
		button.setOnAction(e->
		getResultAndReset(button,textarea,sourceCB,destinationCB, graph,imagePane));
		
		
		
		sourceCB.setOnAction(e ->
		setVisibleForButton(button,sourceCB,destinationCB)
		);
		
		destinationCB.setOnAction(e ->
		setVisibleForButton(button,sourceCB,destinationCB)
		);
		
		button.setOnAction(e->
			getResultAndReset(button,textarea,sourceCB,destinationCB,graph,imagePane)
		);
		
		
		Pane pane = new Pane(textarea, scrollpane, sourceCB, destinationCB,button,label);
		Scene scene = new Scene(pane,704,553);
		
		return scene;
	}
	
	
	public static void setVisibleForButton(Button button, ComboBox sourceCB, ComboBox destinationCB) {
		if(sourceCB.getValue() != null && destinationCB.getValue() != null) {
			if(sourceCB.getValue() != destinationCB.getValue())
			button.setVisible(true);
		}
	}
	
	public static void getResultAndReset(Button button, TextArea textarea, ComboBox sourceCB, ComboBox destinationCB, Graph graph, Pane imagePane) {
		/* FUNCTIONALITIES GOES HERE */
		textarea.clear();
		
		String source = (String) sourceCB.getValue();
		String destination = (String) destinationCB.getValue();
		button.setVisible(false);
		Vertex v = graph.Vertices.get(destinationCB.getValue());
		Vertex v2 = graph.Vertices.get(sourceCB.getValue());
		if(v.getAdjacents().size()==0 || v2.getAdjacents().size()==0) {
			textarea.appendText("Cant calculate");
		}
		else {
		graph.findShortestPath(source,destination,textarea);
		recScenes(graph.Vertices.get(destinationCB.getValue()), imagePane);
		}
		sourceCB.setValue(null);
		destinationCB.setValue(null);
		
		
		
		
	}
	
	public static void recScenes(Vertex v, Pane myPane) {
		System.out.println(v.getCity().getName());
		while (v != null) {
			System.out.println("....");
			Vertex prev = v.getPrev();
			if (prev == null)
				return;

			Line line = new Line(v.getCity().getX(), v.getCity().getY(),
					prev.getCity().getX(), prev.getCity().getY());
					
			line.setStyle("-fx-stroke-width:5px");
			myPane.getChildren().add(line);
			line.getStyleClass().add("line");

			v = prev;

		}
	}

}
