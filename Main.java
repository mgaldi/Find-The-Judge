public class Main {

    public static void main(String[] args) {
        int[][] trustVector = { {1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3} };
        System.out.println(findJudge(trustVector));
    }

    public static int findJudge(int[][] trustVector) {
        // finds the number of citizens
        int citizensNumber = Integer.MIN_VALUE;
        for (int[] trust : trustVector) {
            int pairMax = Math.max(trust[0], trust[1]);
            if (pairMax > citizensNumber)
                citizensNumber = pairMax;
        }
        // creates the adjacency matrix
        boolean[][] graph = new boolean[citizensNumber][citizensNumber];
        // fills the adjacency matrix
        for (int[] trust : trustVector)
            graph[trust[0] - 1][trust[1] - 1] = true;
        // looks for the judge
        outerLoop:
        for (int i = 0; i < citizensNumber; i++) {
            for (int j = 0; j < citizensNumber; j++) {
                if (i == j)
                    continue;
                if (graph[i][j])
                    continue outerLoop;
            }
            for (int k = 0; k < citizensNumber; k++) {
                if (k == i)
                    continue;
                if (!graph[k][i])
                    break outerLoop;    // if a citizen who doesn't trust anyone is not trusted by any other citizen
                                        // then there is no judge since the first citizen would have to trust the judge
            }
            return i + 1;
        }
        return -1;
    }

}
