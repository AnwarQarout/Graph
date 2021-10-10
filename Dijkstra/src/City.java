public class City {
    private String Name;
    private double x; // for its position on the map
    private double y; // for its position on the map
    public City() {
        Name = null;
        x = 0;
        y = 0;
    }
    public City(String Name) {
        this.Name = Name;
    }

    public City(String Name, double x, double y) {
        this.Name = Name;
        this.x = x;
        this.y = y;
    }

    public String getName() {return Name;}
    public double getX(){return x;}
    public double getY(){return y;}

    public void setName(String Name){this.Name=Name;}
    public void setXY(double x,double y){this.x = x; this.y = y;}

}
