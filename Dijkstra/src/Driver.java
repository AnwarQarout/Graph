import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Driver extends Application {
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(Scenes.mainScene());
		primaryStage.show();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		launch(args);
	}
	
	
	
	public static Graph readFile(File file, File file2, ComboBox source, ComboBox destination) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		Graph graph = new Graph();

		String line;
		String tokens[];

		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			tokens = line.split("-");
			
			graph.insertVertex(new Vertex(new City(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]))));
			source.getItems().add(tokens[0]);
			destination.getItems().add(tokens[0]);
		}

		scanner = new Scanner(file2);
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			tokens = line.split("-");
			
			graph.addAdjacent(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
			graph.addAdjacent(tokens[1], tokens[0], Double.parseDouble(tokens[2]));
			
		}



		return graph;
	}
}
