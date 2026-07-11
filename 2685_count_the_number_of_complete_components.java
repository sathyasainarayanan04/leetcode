class Solution {
    public int countCompleteComponents(int numberOfVertices, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for(int vertex = 0; vertex < numberOfVertices; vertex++){
            adjacencyList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int source = edge[0];
            int destination = edge[1];

            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
        }
        
        boolean[] visited = new boolean[numberOfVertices];
        int completeComponentCount = 0;

        for(int vertex = 0; vertex < numberOfVertices; vertex++){

            if(!visited[vertex]){
                int vertexCount = 0;
                int totalDegreeSum = 0;

                Queue<Integer> queue = new LinkedList<>();
                queue.offer(vertex);
                visited[vertex] = true;

                while(!queue.isEmpty()){
                    int currentVertex = queue.poll();
                    vertexCount++;
                    totalDegreeSum += adjacencyList.get(currentVertex).size();

                    for(int neighbor : adjacencyList.get(currentVertex)){
                        if(!visited[neighbor]){
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }

                int expectedDegreeSum = vertexCount * (vertexCount-1);

                if(expectedDegreeSum == totalDegreeSum){
                    completeComponentCount++;
                }
            }
        }
    return completeComponentCount;
    }
}
