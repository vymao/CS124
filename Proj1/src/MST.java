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
    private int minKey(Boolean visitedSet[], float[] distance, int graph_size) {
        // Initialize min value 
        float min = Float.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < graph_size; v++)
            if (visitedSet[v] == false && distance[v] < min) {
                min = distance[v];
                min_index = v;
            }
        sum += min;
        return min_index;
    }

    private void setNeighbors(Graph g, int node, Boolean[] visited, float[] distance) {
        float[] start_coordinates = g.getCoordinate(node);
        for (int v = 0; v < numVertices; v++) {
            if (visited[v] == false) {
                float[] end_coordinates = g.getCoordinate(v);
                for (int dim = 0; dim < end_coordinates.length; dim++) {
                    float length = GraphGenerator.euclideanDistance(start_coordinates, end_coordinates);
                    //¸System.out.println("Start: " + Integer.toString(node) + ", End: " + Integer.toString(v) + ", Distance: " + Float.toString(length));
                    if (length != 2.0 && length < distance[v]) {
                        distance[v] = length;
                    }
                }
            }
        }

    }


    private float cutOff() {
        return 0.6f;
    }


    public Boolean[] runMST(Graph g, int vertices, float[] distances, Boolean visited[]) {
        for (int i = 0; i < vertices; i++) {
            distances[i] = Float.MAX_VALUE;
            visited[i] = false;
        }

        distances[0] = 0;

        for (int count = 0; count < vertices; count++) {
            int u = minKey(visited,distances, vertices);

            visited[u] = true;
            setNeighbors(g, u, visited, distances);
        }

        return visited;
    }

    public float findMST(Graph g, int vertices) {
        numVertices = vertices;
        key = new float[numVertices];
        Boolean visitedSet[] = new Boolean[numVertices];

        Boolean[] visited = runMST(g, vertices, key, visitedSet);
        int missing = 0;
        for (int j = 0; j < visited.length; j++) {
            if (visited[j] == false) {
                missing += 1;
            }
        }
        if (missing != 0) {
            float[] keyUnvisited = new float[missing];
            Boolean visitedSetUnvisited[] = new Boolean[missing];

            runMST(g, missing, keyUnvisited, visitedSetUnvisited);
        }

        return sum;

    }
} 