import java.util.*;
import java.lang.*;
import java.io.*;

public class MST {
    // Number of vertices in the graph 
    private int numVertices;
    private float sum = 0;

    // A utility function to find the vertex with minimum key 
    // value, from the set of vertices not yet included in MST 
    private int minKey(float key[], Boolean visitedSet[]) {
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
        for (int v = 1; v <= numVertices; v++) {

            // graph[u][v] is non  zero only for adjacent vertices of m
            // mstSet[v] is false for vertices not yet included in MST
            // Update the key only if graph[u][v] is smaller than key[v]
            float edge = g.getEdge(node, v);

            //float edge = matrix[node][v];

            if (edge != 2.0 && visited[v - 1] == false && edge < distance[v - 1]) {
                parent[v - 1] = node;
                distance[v - 1] = edge;
            }
        }
    }


    public float runMST(Graph g, int vertices) {
        numVertices = vertices;
        int parent[] = new int[numVertices];
        float key[] = new float[numVertices];
        Boolean visitedSet[] = new Boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            key[i] = Float.MAX_VALUE;
            visitedSet[i] = false;
        }

        // Always include first 1st vertex in MST. 
        key[0] = 0; // Make key 0 so that this vertex is 
        // picked as first vertex 
        parent[0] = -1;

        for (int count = 1; count <= numVertices; count++) {
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, visitedSet);

            // Add the picked vertex to the MST Set 
            visitedSet[u] = true;
            setNeighbors(g, u + 1, visitedSet, key, parent);
        }

        return sum;

    }
} 