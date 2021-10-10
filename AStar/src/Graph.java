import java.util.*;

public class Graph {
    public HashMap<Node, LinkedList<Edges>> adjacents;
    HashMap<String, Node> helpingMap;

    public Graph() {
        adjacents = new HashMap<>();
        helpingMap = new HashMap<>();
    }

    public Node getDestNode(){
        return helpingMap.get("destination");
    }

    public void insertNode(String source, double x, double y){
        if (!helpingMap.containsKey(source)){
            Node sourceNd = new Node(source,x,y);
            helpingMap.put(source,sourceNd);
            adjacents.put(sourceNd,null);
        }
    }

    public void insertEdge(String sourceStr, String destinationStr, int distance) {
        if (!helpingMap.containsKey(sourceStr) && !helpingMap.containsKey(destinationStr)) {

            Node source = new Node(sourceStr);
            helpingMap.put(sourceStr, source);
            adjacents.put(source, null);

            Node destination = new Node(destinationStr);
            helpingMap.put(destinationStr, destination);
            adjacents.put(destination, null);

            Edges destEdge = new Edges(distance,destination);

            source.getAdjacentNodesLL().add(destEdge);
            adjacents.put(source, source.getAdjacentNodesLL());

        } else if (helpingMap.containsKey(sourceStr) && !helpingMap.containsKey(destinationStr)) {

            Node destination = new Node(destinationStr);
            helpingMap.put(destinationStr, destination);
            adjacents.put(destination, null);

            Edges destEdge = new Edges(distance, destination);

            helpingMap.get(sourceStr).getAdjacentNodesLL().add(destEdge);
            adjacents.put(helpingMap.get(sourceStr),null);
            adjacents.put(helpingMap.get(sourceStr), helpingMap.get(sourceStr).getAdjacentNodesLL());



        } else if (!helpingMap.containsKey(sourceStr) && helpingMap.containsKey(destinationStr)) {

            Node source = new Node(sourceStr);
            helpingMap.put(sourceStr, source);
            adjacents.put(source, null);

            source.getAdjacentNodesLL().add(new Edges(distance, helpingMap.get(destinationStr)));

            adjacents.put(source, source.getAdjacentNodesLL());

        }
        else if(helpingMap.containsKey(sourceStr) && helpingMap.containsKey(destinationStr)){
            helpingMap.get(sourceStr).getAdjacentNodesLL().add(new Edges(distance,helpingMap.get(destinationStr)));
            adjacents.put(helpingMap.get(sourceStr),helpingMap.get(sourceStr).getAdjacentNodesLL());
        }

    /*if(!adjacents.containsKey(source)){
        adjacents.put(source,null);
        System.out.println("source doesnt exist, adding...");
    }
    if(!adjacents.containsKey(destination)) {
        adjacents.put(destination,null);
        System.out.println("destination doesnt exist, adding...");
    }*/
  /*  if(helpingMap.containsKey(source.name)){
        Node temp = helpingMap.get(source.name);
        temp.getAdjacentNodesLL().add(destination);
        adjacents.put(temp, temp.getAdjacentNodesLL());
    }
    source.getAdjacentNodesLL().add(destination);
    adjacents.put(source,source.getAdjacentNodesLL());
*/

    }

    public void printGraph() {
        for (Node root : adjacents.keySet()) {

            /* TWO WAYS TO PRINT ADJACENTS: 1) LOOP OVER LINKED LIST OR 2) LOOP OVER HASHMAP FOR CURRENT ROOT */
            // LOOP OVER LINKED LIST
            for (Edges adjacent : root.getAdjacentNodesLL()) {
                System.out.println(adjacent.getNode().name + " - " + adjacent.distance);
            }

            // LOOP OVER HASHMAP OF EACH ROOT.
                /*for(Node adjacent: adjacents.get(root)){
                    if(!adjacents.isEmpty())
                    System.out.println(adjacent.name);
                }*/

        }
    }

    public void getAdjacentsOfANodeUsingHashMap(String name) {
        for (Edges adjacent : adjacents.get(helpingMap.get(name))) {
            System.out.println(adjacent.getNode().name);
        }
    }


    public String bfsSearch(String source, String destination) {
        int timeComplexity=0,spaceComplexity=0;
        boolean flag = false;
        String path = new String();
        List<Node> visitedNodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node sourceNode = new Node(source);
        queue.add(sourceNode);
        spaceComplexity++;
        sourceNode.parent = null;

        while (!queue.isEmpty()) {
            Node visistedNode = queue.remove();
            visitedNodes.add(visistedNode);
            if (visistedNode.name.equals(destination)) {
                flag = true;
                path = recNode(visistedNode,path);
                path += "\nTime Complexity (Number of loops/if statements): "+Integer.toString(timeComplexity)+"\nSpace complexity (Number of nodes in queue): "+spaceComplexity;
                break;
            }
            List<Edges> neighbours = adjacents.get(helpingMap.get(visistedNode.name));
            //System.out.println(neighbours.get(0).getNode().name);
            if (neighbours != null) {
                for (int i = 0; i < neighbours.size(); i++) {
                    Edges n = neighbours.get(i);
                    if (n != null && !visitedNodes.contains(n.getNode())) {
                        queue.add(n.getNode());
                        spaceComplexity++;
                        n.getNode().parent = visistedNode;
                        timeComplexity++;
                    }
                }
            }
        }
        if (flag == false) {
            return "No path.";
        }

        return path;
    }

    public String recNode(Node destination, String path){

        if(destination.parent == null){
            return destination.name + path;
        }

        return path += recNode(destination.parent,path) + "-" + destination.name  ;
        //return path += destination.name + "-" + recNode(destination.parent,path);



    }
    public String greedyBFS(String source, String destination, Graph graph){
        double cost=0,costFromStart=0;
        int timeComplexity=0, spaceComplexity=0;
        graph.helpingMap.put("destination",graph.helpingMap.get(destination));
        String path = new String();
        PriorityQueue<Edges> frontier = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Edges> frontier2 = new PriorityQueue<>(Collections.reverseOrder());
        System.out.println("created heap!");
        Edges edge = new Edges(0,new Node(source));
        edge.destination = graph.helpingMap.get("destination");
       // frontier.add(helpingMap.get(source).getEdge());
        frontier.add(edge);
        spaceComplexity++;
       // helpingMap.get(source).parent = null;
        edge.getNode().parent = null;
        List<Edges> explored = new ArrayList<>();
        while(!frontier.isEmpty()) {
            timeComplexity++;
            Edges state = frontier.remove();
            explored.add(state);
            //path += state.getNode().name + "-";
            //System.out.println("NAME : ---- " + state.getNode().name);
            if (state.getNode().name.equals(destination)) {

                path = recNode(state.getNode(),path);
                path += "\nTime Complexity (Number of loops/if statements): "+Integer.toString(timeComplexity)+"\nSpace complexity (Number of nodes in priority queue): "+spaceComplexity;
                return path;

            }
            List<Edges> neighbors = adjacents.get(helpingMap.get(state.getNode().name));
            if (neighbors != null) {
                for (Edges neighbor : neighbors) {
                    if (!frontier.contains(neighbor) && !explored.contains(neighbor)) {
                        timeComplexity++;
                        neighbor.getNode().parent = state.getNode();
                        neighbor.destination = graph.helpingMap.get("destination");
                        frontier2.add(neighbor);
                       frontier.add(neighbor);
                        spaceComplexity++;

                    }

                    if(neighbor.getNode().name.equals(destination)){
                        break;
                    }
                }




            }
        }
        return "No path.";
    }

    /*public int greedyBFS(String source, String destination, int distance, List<Node> path) {
        distance = 0;
        boolean flag = false;
        boolean isThere = false;
        int index = 0;
        Node sourceNode = helpingMap.get(source);
        System.out.println("got source node! " + sourceNode.name);

        while (flag == false) {
            List<Edges> neighbors = adjacents.get(sourceNode);
            System.out.println("got neighbors of " + sourceNode.name);

            if(neighbors != null) {

                for (int i = 0; i < neighbors.size(); i++) {
                    if (neighbors.get(i).getNode().name.equals(destination)) {
                        index = i;
                        isThere = true;
                    }
                }

            if (isThere) {
                path.add(helpingMap.get(destination));
                distance += neighbors.get(index).getDistance();
                flag = true;
                break;
            }
            int tempIndex = getShortestEdgeIndex(neighbors);
            path.add(neighbors.get(tempIndex).getNode());
            distance += neighbors.get(tempIndex).getDistance();
            sourceNode = helpingMap.get(neighbors.get(tempIndex).getNode().name);
            }
        }
            return distance;

        }*/

        public int getShortestEdgeIndex(List<Edges> neighbors){
        int min = 0;
            for (int i = 1; i < neighbors.size(); i++) {
                if(neighbors.get(i).distance < neighbors.get(min).distance){
                    min = i;
                }
            }
            return min;
        }

        public void tempPrint(String string){
            List<Edges> adj = adjacents.get(helpingMap.get(string));
            for (int i = 0; i < adj.size(); i++) {
                System.out.println(adj.get(i).getNode().name);
            }
        }
    }



