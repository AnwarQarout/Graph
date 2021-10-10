public class Edge implements Comparable{
    private Vertex vertex;
    private double distance;

    public Edge(Vertex vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }


    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Object e) {
        if (this.distance>((Edge)e).getDistance())
            return 1;
        else if (this.distance==((Edge)e).getDistance())
            return 0;
        else
            return -1;


    }

    public double getDistance() {
        return distance;
    }


}
