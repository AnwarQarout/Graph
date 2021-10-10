import java.util.LinkedList;

public class Node {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    Node parent;
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }




    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }






    private LinkedList<Edges> adjacentNodesLL = new LinkedList<>();
    public Node(String name){

        this.name = name;
        this.x = 0.0;
        this.y = 0.0;
    }

    public Node(){
        this.name = null;
        this.x = 0.0;
        this.y=0.0;
    }

    public Node(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Node getNode(){
        return this;
    }


    public LinkedList<Edges> getAdjacentNodesLL() {
        return adjacentNodesLL;
    }

    public void setAdjacentNodesLL(LinkedList<Edges> adjacentNodesLL) {
        this.adjacentNodesLL = adjacentNodesLL;
    }

   public Edges getEdge(){
        return new Edges(0,this);
   }
}
