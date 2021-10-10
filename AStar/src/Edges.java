public class Edges extends Graph implements Comparable<Edges>{
    Node node;
    int distance;
    Node destination;

    public double getTotalCostFromStart() {
        return totalCostFromStart;
    }

    public void setTotalCostFromStart(double totalCostFromStart) {
        this.totalCostFromStart = totalCostFromStart;
    }

    double totalCostFromStart;
    public Edges(){

    }

    public Edges(int distance){
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Edges(int distance, Node enteringNode){
        node = new Node(enteringNode.name);
        this.distance = distance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public double getHeuristic(){
        System.out.println("------------------BLALBALBALB---------");
        //return Math.max(Math.abs(this.getNode().getX() - destination.getX()), Math.abs(this.getNode().getY() - destination.getY()));
        return Math.sqrt((Math.pow(this.getNode().getX() - destination.getX(),2)) / (Math.pow(this.getNode().getY() - destination.getY(),2))) * 5;
        //return (Math.pow(this.getNode().getX() - destination.getX(),2)) / (Math.pow(this.getNode().getY() - destination.getY(),2));
    }

    @Override
    /*public int compareTo(Edges o) {
        System.out.println("entered...");
        if((this.distance + this.getHeuristic() == o.distance + o.getHeuristic())){
            return 0;
        }
        else if((this.distance + this.getHeuristic() < o.distance + o.getHeuristic())){
            return 1;
        }
        else{
            return -1;
        }
    }*/

    public int compareTo(Edges o) {
        System.out.println("entered...");
        if((this.getHeuristic() == o.getHeuristic())){
            return 0;
        }
        else if((this.getHeuristic() < o.getHeuristic())){
            return 1;
        }
        else{
            return -1;
        }
    }
}
