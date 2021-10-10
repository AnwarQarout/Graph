import java.util.HashMap;
import java.util.PriorityQueue;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Graph {
    public HashMap<String,Vertex> Vertices = new HashMap<String,Vertex>();
    private int size = 0;
    public Graph() {

    }

    public void insertVertex(Vertex vertex) {
        Vertices.put(vertex.getCity().getName(),vertex);

    }

    public void addAdjacent(String source, String adjacent, double distance) {
        Vertices.get(source).getAdjacents().add(new Edge(Vertices.get(adjacent),distance));
    }
    public void findShortestPath(String source, String dis, TextArea tf) {
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        for (String i : Vertices.keySet()) {
            Vertices.get(i).setKnown(false);
          Vertices.get(i).setDis(Double.MAX_VALUE);
          Vertices.get(i).setPrev(null);
        }
        
        Vertices.get(source).setDis(0.0);
        queue.add(Vertices.get(source));
        while(!queue.isEmpty()) {
        	
            Vertex v = queue.poll(); // delete min
            if(v==Vertices.get(dis))
            	break;
            if (!v.isKnown()) {
                for (int i = 0; i < v.getAdjacents().size(); i++) {
                    if (!v.getAdjacents().get(i).getVertex().isKnown()) {
                        if (v.getAdjacents().get(i).getDistance() + v.getDis() < v.getAdjacents().get(i).getVertex().getDis()) {
                            v.getAdjacents().get(i).getVertex().setPrev(v);
                            v.getAdjacents().get(i).getVertex().setDis(v.getAdjacents().get(i).getDistance() + v.getDis());
                            queue.add(v.getAdjacents().get(i).getVertex());


                        }
                    }
                }
            }
            v.setKnown(true);

        }
        String s1 = new String();
      printPath(source, dis, tf);
       System.out.println(s1);
        Vertex v2 = Vertices.get(dis);
        print(v2,tf);
        
    }

    private void printPath(String source, String des, TextArea tf) {
        Vertex v = Vertices.get(des);
       tf.appendText("Distance is "+v.getDis()+"km\n");
       
       
    }

    private void print(Vertex  v, TextArea tf) {
        if (v==null){
            return;
        }
        print(v.getPrev(),tf);
        tf.appendText(v.getCity().getName()+" -> ");
         
    }
}
