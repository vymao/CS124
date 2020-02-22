import java.util.*;
import java.lang.*;
import java.io.*;

public class MST {
    // Number of vertices in the graph 
    private int numVertices;
    private float sum = 0;
    private float[] key;

    // A utility function to find the vertex with minimum key 
    // value, from the set of vertices not yet included in MST

    public float maxEdge() {
        float max = 0;
        for (int v = 0; v < numVertices; v++)
            if (key[v] > max) {
                max = key[v];
            }
        return max;
    }
    private int minKey(Boolean visitedSet[]) {
        // Initialize min value 
        float min = Float.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < numVertices; v++)
            if (visitedSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        sum += min;
        return min_index;
    }

    private void setNeighbors(Graph g, int node, Boolean[] visited, float[] distance, int[] parent) {
        float[] start_coordinates = g.getCoordinate(node);
        for (int v = 0; v < numVertices; v++) {
            if (visited[v] == false) {
                float[] end_coordinates = g.getCoordinate(v);
                for (int dim = 0; dim < end_coordinates.length; dim++) {
                    float length = GraphGenerator.euclideanDistance(start_coordinates, end_coordinates);
                    //¸System.out.println("Start: " + Integer.toString(node) + ", End: " + Integer.toString(v) + ", Distance: " + Float.toString(length));
                    if (length != 2.0 && length < distance[v]) {
                        parent[v] = node;
                        distance[v] = length;
                    }
                }
            }
        }

    }


    public float runMST(Graph g, int vertices) {
        numVertices = vertices;
        int parent[] = new int[numVertices];
        key = new float[numVertices];
        Boolean visitedSet[] = new Boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            key[i] = Float.MAX_VALUE;
            visitedSet[i] = false;
        }

        // Always include first 1st vertex in MST. 
        key[0] = 0; // Make key 0 so that this vertex is 
        // picked as first vertex 
        parent[0] = -1;

        for (int count = 0; count < numVertices; count++) {
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(visitedSet);

            // Add the picked vertex to the MST Set 
            visitedSet[u] = true;
            setNeighbors(g, u, visitedSet, key, parent);
        }

        return sum;

    }
} 