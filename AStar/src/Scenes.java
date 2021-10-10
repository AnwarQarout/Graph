import eu.hansolo.enzo.canvasled.Led;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class Scenes {
    public Scenes() {
    }

    public static Scene mainScene() throws FileNotFoundException {
        File adjacents = new File("src/Adjacents.txt");
        File cities = new File("src/Cities.txt");
        ComboBox sourceCB = new ComboBox();
        sourceCB.setLayoutX(359);
        sourceCB.setLayoutY(66.0D);
        sourceCB.setPrefHeight(25.0D);
        sourceCB.setPrefWidth(147.0D);

        RadioButton bfsRB = new RadioButton("BFS");
        bfsRB.setLayoutX(419);
        bfsRB.setLayoutY(216);
        bfsRB.setSelected(true);
        RadioButton gbfsRB = new RadioButton("Greedy BFS");
        gbfsRB.setLayoutX(559);
        gbfsRB.setLayoutY(216);

        ToggleGroup radioGroup = new ToggleGroup();
        bfsRB.setToggleGroup(radioGroup);
        gbfsRB.setToggleGroup(radioGroup);


        ComboBox destinationCB = new ComboBox();
        destinationCB.setLayoutX(543);
        destinationCB.setLayoutY(66.0D);
        destinationCB.setPrefHeight(25.0D);
        destinationCB.setPrefWidth(147.0D);
        ScrollPane scrollpane = new ScrollPane();
        scrollpane.setLayoutX(16.0D);
        scrollpane.setLayoutY(14.0D);
        scrollpane.setPrefHeight(420.0D);
        scrollpane.setPrefWidth(328);
        scrollpane.setPannable(true);
        InputStream stream = new FileInputStream("C:\\Users\\Alqarout\\Desktop\\Studies\\palestine.png");
        ImageView imageView = new ImageView();
        Image image = new Image(stream);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(image);
        Pane imagePane = new Pane();
        imagePane.getChildren().add(imageView);
        scrollpane.setContent(imagePane);
        Graph graph = Driver.readFile(cities, adjacents, sourceCB, destinationCB);
        Iterator var11 = graph.helpingMap.keySet().iterator();

        while(var11.hasNext()) {
            String keys = (String)var11.next();
            Node c = graph.helpingMap.get(keys).getNode();
            Led led = new Led();
            led.setLayoutX(c.getX());
            led.setLayoutY(c.getY());
            led.setPrefHeight(16);
            led.setPrefWidth(16);
            Label label = new Label(c.getName());
            label.setLayoutX(c.getX());
            label.setLayoutY(c.getY());
            imagePane.getChildren().addAll(led);
            led.setId(c.getName());
            led.setOnMouseClicked((e) -> {
                if (sourceCB.getValue() == null) {
                    sourceCB.setValue(led.getId());
                } else {
                    destinationCB.setValue(led.getId());
                }

            });
        }

        TextArea textarea = new TextArea();
        textarea.setLayoutX(22.0D);
        textarea.setLayoutY(459.0D);
        textarea.setPrefHeight(71.0D);
        textarea.setPrefWidth(660.0D);
        Button button = new Button("Calculate");
        button.setLayoutX(443);
        button.setLayoutY(300);
        button.setPrefHeight(78.0D);
        button.setPrefWidth(147.0D);
        button.setMnemonicParsing(false);
        button.setVisible(false);
        Label label = new Label("To");
        label.setLayoutY(70.0D);
        label.setLayoutX(516);
        button.setOnAction((e) -> {
            getResultAndReset(button, textarea, sourceCB, destinationCB, graph, imagePane,bfsRB,gbfsRB);
        });
        sourceCB.setOnAction((e) -> {
            setVisibleForButton(button, sourceCB, destinationCB,bfsRB,gbfsRB);
        });
        destinationCB.setOnAction((e) -> {
            setVisibleForButton(button, sourceCB, destinationCB,bfsRB,gbfsRB);
        });
        button.setOnAction((e) -> {
            getResultAndReset(button, textarea, sourceCB, destinationCB, graph, imagePane,bfsRB,gbfsRB);
        });
        Pane pane = new Pane(textarea, scrollpane, sourceCB, destinationCB, button, label,bfsRB,gbfsRB);

        Scene scene = new Scene(pane, 704.0D, 553.0D);

        return scene;
    }

    public static void setVisibleForButton(Button button, ComboBox sourceCB, ComboBox destinationCB, RadioButton bfsRB, RadioButton gbfsRB) {
        if (sourceCB.getValue() != null && destinationCB.getValue() != null && sourceCB.getValue() != destinationCB.getValue()) {
            button.setVisible(true);
        }

    }

    public static void getResultAndReset(Button button, TextArea textarea, ComboBox sourceCB, ComboBox destinationCB, Graph graph, Pane imagePane, RadioButton bfsRB, RadioButton gbfsRB) {
        textarea.clear();

        String source = (String)sourceCB.getValue();
        String destination = (String)destinationCB.getValue();
        button.setVisible(false);
        Node v = graph.helpingMap.get(sourceCB.getValue());
        Node v2 = graph.helpingMap.get(destinationCB.getValue());
        /*Vertex v = (Vertex)graph.Vertices.get(destinationCB.getValue());
        Vertex v2 = (Vertex)graph.Vertices.get(sourceCB.getValue());*/
        if (v.getAdjacentNodesLL().size() != 0 && v2.getAdjacentNodesLL().size() != 0) {
            String path = new String();
            if(bfsRB.isSelected()) {
                path = graph.bfsSearch(source, destination);
            }
            else if(gbfsRB.isSelected()){
                path = graph.greedyBFS(source, destination,graph);
            }
           textarea.setText(path);
        } else {
            textarea.appendText("Cant calculate");
        }

        sourceCB.setValue((Object)null);
        destinationCB.setValue((Object)null);
    }

}