import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(Scenes.mainScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        /*Graph graph = new Graph();
        graph.insertEdge("Ramallah", "Jerusalem", 3);
        graph.insertEdge("Ramallah", "Hebron", 4);
        graph.insertEdge("Jerusalem", "Jenin", 3);
        graph.insertEdge("Ramallah", "Tulkarm", 4);
        graph.insertEdge("Jenin", "Akka", 4);

        graph.insertEdge("City1", "City2", 2);
        graph.insertEdge("City1", "City3", 4);
        graph.insertEdge("City1", "City4", 4);
        graph.insertEdge("City2", "City5", 1);
        graph.insertEdge("City3", "City5", 1);
        graph.insertEdge("City4", "City5", 1);
        graph.insertEdge("City5","City6",3);
        graph.insertEdge("City5","City7",2);
        graph.insertEdge("City6","City8",7);
        graph.insertEdge("City7","City8",8);
        graph.insertEdge("City1","City9",5);
        graph.insertEdge("City9","City8",5);*/

      /*String path = graph.bfsSearch("City1","City8");
        System.out.println("Path = "+path);*/

        //int distance = 0;
        /*List<Edges> solution = new ArrayList<>();

       String path = graph.greedyBFS("City1", "City8");
        System.out.println("PATH IS " +path);*/
       /* for (int i = 0; i < solution.size(); i++) {
            System.out.print(solution.get(i).getNode().name + "-");
        }*/


            //graph.getAdjacentsOfANodeUsingHashMap("Ramallah");
        /*Node Ramallah = new Node("Ramallah");
        Node Jericho = new Node("Jericho");
        Node Jerusalem = new Node("Jerusalem");
        Node Nablus = new Node("Nablus");
        graph.insertEdge(Ramallah, Jericho);
        graph.insertEdge(Ramallah, Jerusalem);
        graph.insertEdge(Nablus, Ramallah);
        graph.insertEdge(Jericho, Nablus);



        for(Node adjacent : graph.adjacents.get(Ramallah)){
            System.out.println(adjacent.name);
        }*/


            // adding(graph);


/*
    public static void adding (Graph graph){
        Node Ramallah = new Node("Ramallah");
        Node Jericho = new Node("Jericho");
        graph.insertEdge(Ramallah,Jericho);
    }*/
    }

    public static Graph readFile(File file, File file2, ComboBox source, ComboBox destination) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Graph graph = new Graph();

        String line;
        String[] tokens;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            tokens = line.split("-");
            graph.insertNode(tokens[0],Double.parseDouble(tokens[1]),Double.parseDouble(tokens[2]));
            source.getItems().add(tokens[0]);
            destination.getItems().add(tokens[0]);
        }

        scanner = new Scanner(file2);

        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            tokens = line.split("-");
            graph.insertEdge(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            graph.insertEdge(tokens[1], tokens[0], Integer.parseInt(tokens[2]));
        }

        return graph;
    }


}
