import java.util.*;

public class Challenge {
    public record Key(int visited, byte onNode) {
        @Override
        public int hashCode() {
            return visited | onNode << 25;
        }
    }
    public record History(long history0, long history1, int historyLen) {
        public int get(int i) {
            if (i >= 12) {
                int i1 = i - 12;
                return (int) (this.history1 >> i1 * 5 & 31);
            } else {
                return (int) (this.history0 >> i * 5 & 31);
            }
        }

        public History add(int x) {
            if (this.historyLen >= 12) {
                int historyLen1 = this.historyLen - 12;
                return new History(
                    this.history0,
                    this.history1 | ((long) x << historyLen1 * 5),
                    this.historyLen + 1
                );
            } else {
                return new History(
                    this.history0 | ((long) x << this.historyLen * 5),
                    this.history1,
                    this.historyLen + 1
                );
            }
        }
    }
    public record BBInstance(int visited, int distTraveled, byte onNode, History history) {}

    public static void main(String[] args) {
        int[][] dist = {
            {0, 2, 0, 7, 0, 14, 0, 6, 0, 10, 0, 0, 0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {7, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {14, 0, 0, 6, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 12, 0, 8, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 12, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0},
            {13, 0, 0, 0, 0, 9, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 9, 0, 8, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 13, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 3},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 3, 0},
        };
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] > 0) continue;
                dist[i][j] = -1;
                dist[j][i] = -1;
            }
        }
        for (int i = 0; i < dist.length; i++) {
            dist[i][i] = 0;
        }
        List<List<List<Integer>>> pathing = new ArrayList<>();
        for (int i = 0; i < dist.length; i++) {
            List<List<Integer>> innerList = new ArrayList<>();
            for (int j = 0; j < dist.length; j++) {
                List<Integer> inside = new LinkedList<>();
                if (dist.length >= 0) {
                    inside.add(i);
                    inside.add(j);
                }
                innerList.add(inside);
            }
            pathing.add(innerList);
        }
        for (int k = 0; k < dist.length; k++) {
            for (int i = 0; i < dist.length; i++) {
                if (i == k) continue;
                for (int j = 0; j < dist.length; j++) {
                    if (j == k) continue;
                    int newDist = dist[i][k] + dist[k][j];
                    if ((dist[i][j] < 0 || newDist < dist[i][j]) && dist[i][k] >= 0 && dist[k][j] >= 0) {
                        dist[i][j] = newDist;
                        List<Integer> oldPathing = pathing.get(i).get(j);
                        if (oldPathing.size() >= 0) {
                            oldPathing.clear();
                        }
                        oldPathing.addAll(pathing.get(i).get(k).subList(0, pathing.get(i).get(k).size() - 1));
                        oldPathing.addAll(pathing.get(k).get(j));
                    }
                }
            }
        }
        for (int j = 0; j < dist.length; j++) {
            for (int i = 0; i < dist.length; i++) {
                if (dist[i][j] < 0) {
                    System.err.println("ABORT: graph is disconnected");
                    System.exit(1);
                }
            }
        }

        int bestSolution = Integer.MAX_VALUE;
        History bestHistory = new History(0, 0, 0);
        int bestStart = -1;
        for (int startNode = 0; startNode < dist.length; startNode++) {
            int bestGuess = 0;
            History bestGuessHist = new History(0, 0, 0);
            int bestVisited = 1 << startNode;
            int bestOn = startNode;
            int allVisited = (1 << dist.length) - 1;
            while (bestVisited != allVisited) {
                 int minDist = Integer.MAX_VALUE;
                 int minNode = -1;
                 for (int i = 0; i < dist.length; i++) {
                     int iMask = 1 << i;
                     if ((iMask & bestVisited) == 0) {
                         if (dist[bestOn][i] < minDist) {
                             minDist = dist[bestOn][i];
                             minNode = i;
                         }
                     }
                 }
                 bestVisited |= 1 << minNode;
                 bestGuess += minDist;
                 bestOn = minNode;
                 bestGuessHist = bestGuessHist.add(bestOn);
            }
            if (bestGuess < bestSolution) {
                bestSolution = bestGuess;
                bestHistory = bestGuessHist;
                bestStart = startNode;
            }

            // Queue<BBInstance> solutions = new ArrayDeque<>();
            Set<BBInstance> currentSolutions = new LinkedHashSet<>();
            currentSolutions.add(new BBInstance(0, 0, (byte) startNode, new History(0, 0, 0)));
            Iterator<BBInstance> currentIterator = currentSolutions.iterator();
            Set<BBInstance> nextGenSolutions = new LinkedHashSet<>();

            Map<Key, Integer> currentGen = new HashMap<>();
            Map<Key, Integer> nextGen = new HashMap<>();
            currentGen.put(new Key(0, (byte) startNode), 0);

            do {
                while (currentIterator.hasNext()) {
                    BBInstance currInstance = currentIterator.next();
                    int newVisited = currInstance.visited | 1 << currInstance.onNode;
                    if (newVisited == allVisited) {
                        if (currInstance.distTraveled < bestSolution) {
                            bestSolution = currInstance.distTraveled;
                            bestHistory = currInstance.history;
                            bestStart = startNode;
                        }
                    } else {
                        Key key = new Key(currInstance.visited, currInstance.onNode);
                        int lastDist = currentGen.get(key);
                        if (lastDist < currInstance.distTraveled) {
                            continue;
                        }

                        for (int i = 0; i < dist.length; i++) {
                            int iMask = 1 << i;
                            if ((iMask & newVisited) == 0) {
                                int newInstDist = currInstance.distTraveled + dist[currInstance.onNode][i];
                                int lowerBound = newInstDist;
                                int minIJ = Integer.MAX_VALUE;
                                for (int j = 0; j < dist.length; j++) {
                                    if (i != j && ((1 << j) & newVisited) == 0) {
                                        int minJK = Integer.MAX_VALUE;
                                        for (int k = 0; k < dist.length; k++) {
                                            if (i != k && k != j && ((1 << k) & newVisited) == 0) {
                                                minJK = Math.min(minJK, dist[j][k]);
                                            }
                                        }
                                        lowerBound += minJK == Integer.MAX_VALUE ? 0 : minJK;
                                        minIJ = Math.min(minIJ, dist[i][j]);
                                    }
                                }
                                if (lowerBound <= bestSolution) {
                                    Key newKey = new Key(newVisited, (byte) i);
                                    Integer newEntry = nextGen.get(newKey);
                                    if (newEntry == null || newInstDist < newEntry) {
                                        nextGen.put(newKey, newInstDist);
                                        nextGenSolutions.add(new BBInstance(newVisited, newInstDist, (byte) i, currInstance.history.add(i)));
                                    }
                                }
                            }
                        }
                    }
                }

                currentSolutions = nextGenSolutions;
                currentIterator = currentSolutions.iterator();
                nextGenSolutions = new LinkedHashSet<>();

                currentGen = nextGen;
                nextGen = new HashMap<>();
            } while (!nextGenSolutions.isEmpty() || currentIterator.hasNext());
        }
        StringBuilder path = new StringBuilder((char) (bestStart + 'a') );
        List<Integer> firstPath = pathing.get(bestStart).get(bestHistory.get(0));
        for (int node : firstPath.subList(0, firstPath.size() - 1)) {
            path.append((char) (node + 'a'));
            path.append(',');
        }
        List<Integer> nextPath = null;
        for (int i = 0; i < bestHistory.historyLen - 1; i++) {
            nextPath = pathing.get(bestHistory.get(i)).get(bestHistory.get(i + 1));
            for (int node : nextPath.subList(0, nextPath.size() - 1)) {
                path.append((char) (node + 'a'));
                path.append(',');
            }
        }
        path.append((char) (nextPath.get(nextPath.size() - 1) + 'a'));
        System.out.println(path);
        System.out.println(bestSolution);
    }
}
