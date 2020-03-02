import java.util.*;
import java.lang.*;
import java.io.*;

public class MST_Edge {
    private int numVertices;
    private float sum = 0;
    private float[] key;
    private Boolean[] visitedSet;

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

    private void setNeighbors(int node, Boolean[] visited, float[] distance) {
        Random generator = new Random();
        for (int v = 0; v < numVertices; v++) {
            if (visited[v] == false) {
                float length = generator.nextFloat();
                //System.out.println("Start: " + Integer.toString(node) + ", End: " + Integer.toString(v) + ", Distance: " + Float.toString(length));
                if (length < distance[v]) {
                    distance[v] = length;
                }
            }
        }

    }

    public float cutOff() {
        return 0.6f;
    }

    public float runMST(int vertices) {
        numVertices = vertices;
        key = new float[numVertices];
        visitedSet = new Boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            key[i] = Float.MAX_VALUE;
            visitedSet[i] = false;
        }

        key[0] = 0;
        float cutoff = cutOff();

        for (int count = 0; count < numVertices; count++) {
            int u = minKey(visitedSet);

            visitedSet[u] = true;
            setNeighbors(u, visitedSet, key);
        }

        return sum;

    }
} 