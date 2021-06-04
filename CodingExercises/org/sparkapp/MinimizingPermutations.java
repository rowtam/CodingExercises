/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sparkapp;

/**
 *
 * @author rowtam
 */
import java.util.*;
// Add any extra import statements you may need here

class MinimizingPermutations {

    // Add any helper functions you may need here
    class Node implements Comparator<Node> {

        public ArrayList<Integer> node;
        public int weight;

        public Node() {
        } //empty constructor 

        public Node(ArrayList<Integer> node, int cost) {
            this.node = node;
            this.weight = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.weight < node2.weight) {
                return -1;
            }
            if (node1.weight > node2.weight) {
                return 1;
            }
            return 0;
        }
    }

    public class PermutationGraph {

        HashMap<ArrayList<Integer>, Integer> distance;
        Set<ArrayList<Integer>> visitedNodes;
        PriorityQueue<Node> minQueue;
        int numberOfConnectedNodes; // Number of connected nodes. 
        HashMap<ArrayList<Integer>, List<Node>> adjacencyList;

        public PermutationGraph(int V) {
            this.numberOfConnectedNodes = V;
            distance = new HashMap<>();
            visitedNodes = new HashSet<>();
            minQueue = new PriorityQueue<>(V, new Node());
        }

        public void dijkstra(HashMap<ArrayList<Integer>, List<Node>> adjacencyList, ArrayList<Integer> sourceNodeKey) {
            this.adjacencyList = adjacencyList;

            // Initialize distance array.
            // record distances as they get shorter.
            distance.entrySet().forEach((m) -> {
                m.setValue(Integer.MAX_VALUE);
            });

            // Add source vertex to Priority Queue 
            minQueue.add(new Node(sourceNodeKey, 0));

            // Set distance to itself to zero
            distance.put(sourceNodeKey, 0);

            while (visitedNodes.size() != numberOfConnectedNodes) {
                ArrayList<Integer> u = minQueue.remove().node;
                visitedNodes.add(u);
                processAdjacentNodes(u);
            }
        }

        // Process neighboring nodes.
        private void processAdjacentNodes(ArrayList<Integer> adjacentNode) {
            int edgeDistance = -1;
            int newDistance = -1;

            // Process all neighbouring nodes of adjacentNode 
            for (int i = 0; i < adjacencyList.get(adjacentNode).size(); i++) {
                Node v = adjacencyList.get(adjacentNode).get(i);

                // Verify that we haven't already traversed this node
                if (!visitedNodes.contains(v.node)) {
                    edgeDistance = v.weight;
                    newDistance = distance.get(adjacentNode) + edgeDistance;

                    // Update distance if it is shorter. 
                    if (!distance.containsKey(v.node) || newDistance < distance.get(v.node)) {
                        distance.put(v.node, newDistance);
                    }

                    // Add the current Node to the Priority Queue 
                    minQueue.add(new Node(v.node, distance.get(v.node)));
                }
            }
        }
    }

    /**
     * reverse the given array in place
     *
     * @param input
     */
    public void reverse(ArrayList<Integer> input, int begin, int end) {

        if (input == null) {
            return;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = end; i >= begin; i--) {
            temp.add(input.get(i));
        }

        for (int i = 0; i < temp.size(); i++) {
            input.set(begin + i, temp.get(i));
        }
    }

    public HashMap<ArrayList<Integer>, ArrayList<Integer>> generateReversePermutations(ArrayList<Integer> arr) {
        HashMap<ArrayList<Integer>, ArrayList<Integer>> hm = new HashMap<>();

        if (arr.size() < 2) {

            hm.put(arr, arr);
            return hm;
        }
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                ArrayList<Integer> arr2 = new ArrayList<>(arr);
                reverse(arr2, i, j);
                hm.put(arr2, arr2);
            }
        }
        return hm;
    }

    public void buildAdjacencyList(ArrayList<Integer> sourceArray, HashMap<ArrayList<Integer>, List<Node>> adjacencyList, HashMap<ArrayList<Integer>, Node> nodeTable) {
        HashMap<ArrayList<Integer>, ArrayList<Integer>> permutationList = generateReversePermutations(sourceArray);
        if (!adjacencyList.containsKey(sourceArray)) {
            if (!nodeTable.containsKey(sourceArray)) {
                Node n = new Node(sourceArray, 1);
                nodeTable.put(sourceArray, n);
            }
            adjacencyList.put(sourceArray, new ArrayList<>());
            adjacencyList.get(sourceArray).add(nodeTable.get(sourceArray));
        }
        for (Map.Entry<ArrayList<Integer>, ArrayList<Integer>> permutationItem : permutationList.entrySet()) {
            if (nodeTable.containsKey(permutationItem.getValue())) {
                adjacencyList.get(sourceArray).add(nodeTable.get(permutationItem.getValue()));
            } else {
                Node n = new Node(permutationItem.getValue(), 1);
                // map array to node
                nodeTable.put(permutationItem.getValue(), n);
                adjacencyList.get(sourceArray).add(n);
                buildAdjacencyList(permutationItem.getValue(), adjacencyList, nodeTable);
            }
        }
    }

    int minOperations(int[] arr) {
        // Write your code here
        ArrayList<Integer> source = new ArrayList<>();
        for (int value : arr) {
            source.add(value);
        }

        ArrayList<Integer> destination = new ArrayList<>(source);
        Collections.sort(destination);
        HashMap<ArrayList<Integer>, List<Node>> adjacencyList = new HashMap<>();
        HashMap<ArrayList<Integer>, Node> nodeTable = new HashMap<>();
        buildAdjacencyList(source, adjacencyList, nodeTable);

        PermutationGraph permutationGraph = new PermutationGraph(adjacencyList.size());
        permutationGraph.dijkstra(adjacencyList, source);

        // Print the shortest path from source node to all the nodes 
        int traversals = permutationGraph.distance.get(destination);
        return traversals;
        // System.out.println("Destination: " + traversals);

    }

    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        } else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {

        int n_1 = 5;
        int[] arr_1 = {1, 2, 5, 4, 3};
        int expected_1 = 1;
        int output_1 = minOperations(arr_1);
        check(expected_1, output_1);

        int n_2 = 3;
        int[] arr_2 = {3, 1, 2};
        int expected_2 = 2;
        int output_2 = minOperations(arr_2);
        check(expected_2, output_2);

        // Add your own test cases here
    }

    public static void main(String[] args) {
        new MinimizingPermutations().run();
    }
}
