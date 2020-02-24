import java.util.*;
import java.lang.*;
import java.io.*;

public class MST {
    // Number of vertices in the graph 
    private int numVertices;
    private float sum = 0;
    private float[] key;
    private int[] vertixCores;

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

    private int [] splitCores(int vertices) {
        int[] work = new int[4];
        int[] regions = new int[5];
        regions[0] = 0;
        int work_per_core = vertices / 4;
        for (int i = 0; i < work.length; i++) {
            work[i] = work_per_core;
        }

        int remaining_work = vertices % 4;

        for (int i = 0; i < remaining_work; i++) {
            work[i] += 1;
        }

        for (int i = 1; i < regions.length; i++) {
            int total_work = regions[i - 1] + work[i - 1];
            regions[i] = total_work;
        }

        return regions;
    }

    private void setNeighbors(Graph g, int node, Boolean[] visited, float[] distance) {
        float[] start_coordinates = g.getCoordinate(node);

        NeighborThread[] threads = new NeighborThread[4];
        for (int i = 0; i < vertixCores.length - 1; i++) {

            threads[i] = new NeighborThread(g, node, visited, distance, start_coordinates, vertixCores[i], vertixCores[i + 1]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }
        }
        /*

        for (int v = 0; v < numVertices; v++) {
            if (visited[v] == false) {
                float[] end_coordinates = g.getCoordinate(v);
                for (int dim = 0; dim < end_coordinates.length; dim++) {
                    float length = GraphGenerator.euclideanDistance(start_coordinates, end_coordinates);
                    //¸System.out.println("Start: " + Integer.toString(node) + ", End: " + Integer.toString(v) + ", Distance: " + Float.toString(length));
                    if (length < distance[v]) {
                        distance[v] = length;
                    }
                }
            }
        }

         */

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
        vertixCores = splitCores(numVertices);

        Boolean[] visited = runMST(g, vertices, key, visitedSet);
        /*
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

         */

        return sum;

    }
} 