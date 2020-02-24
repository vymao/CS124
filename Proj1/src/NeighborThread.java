public class NeighborThread extends Thread {
    private int node, start, end;
    private float[] distance, startCoordinates;
    private Boolean[] visited;
    private Graph graph;

    public NeighborThread(Graph g, int n, Boolean[] v, float[] d, float[] s, int begin, int finish)
    {
        this.node = n;
        this.start = begin;
        this.end = finish;
        this.visited = v;
        this.distance = d;
        this.startCoordinates = s;
        this.graph = g;

    }

    @Override
    public void run() {
        for (int v = start; v < end; v++) {
            if (visited[v] == false) {
                float[] end_coordinates = graph.getCoordinate(v);
                for (int dim = 0; dim < end_coordinates.length; dim++) {
                    float length = GraphGenerator.euclideanDistance(startCoordinates, end_coordinates);
                    //System.out.println("Start: " + Integer.toString(node) + ", End: " + Integer.toString(v) + ", Distance: " + Float.toString(length));
                    if (length < distance[v]) {
                        distance[v] = length;
                    }
                }
            }
        }
    }
}

