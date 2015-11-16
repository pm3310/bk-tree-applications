package pm3310.bktreeapps.knn;

import com.google.common.collect.Maps;

import edu.gatech.gtri.bktree.BkTreeSearcher;
import edu.gatech.gtri.bktree.Metric;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class KNN {

  private final BkTreeSearcher<Record> bkTreeSearcher;
  private final Metric<? super Record> metric;

  public KNN(BkTreeSearcher<Record> bkTreeSearcher) {
    checkArgument(bkTreeSearcher != null, "bkTreeSearcher cannot be null");

    this.bkTreeSearcher = bkTreeSearcher;
    this.metric = bkTreeSearcher.getTree().getMetric();
  }

  public List<Record> findKNearestNeighbors(Record queryPoint, int k, int absoluteThreshold) {
    checkArgument(queryPoint != null, "queryPoint cannot be null");
    checkArgument(k > 0, "k must be greater than 0");
    checkArgument(absoluteThreshold > 0, "absoluteThreshold must be greater than 0");

    Set<BkTreeSearcher.Match<? extends Record>> matches = this.bkTreeSearcher.search(queryPoint, absoluteThreshold);

    List<Record> neighborsToInvestigate = matches.stream().map(match -> (Record) match.getMatch()).collect(Collectors.toList());

    List<Pair<Record, Double>> neighborsWithSimilarityScores = neighborsToInvestigate.stream()
        .map(record -> new Pair<> (record, (double) metric.distance(record, queryPoint)))
        .filter(pair -> pair.getValue() <= absoluteThreshold)
        .collect(Collectors.toList());

    Collections.sort(neighborsWithSimilarityScores);

    int sizeOfNN = neighborsWithSimilarityScores.size() < k ? neighborsWithSimilarityScores.size() : k;

    return neighborsWithSimilarityScores.subList(0, sizeOfNN).stream()
        .map(pair -> pair.getKey())
        .collect(Collectors.toList());
  }

  public int classify(Record queryPoint, int k, int absoluteThreshold) {
    checkArgument(queryPoint != null, "queryPoint cannot be null");
    checkArgument(k > 0, "k must be greater than 0");
    checkArgument(absoluteThreshold > 0, "absoluteThreshold must be greater than 0");

    List<Record> nearestNeighbors = findKNearestNeighbors(queryPoint, k, absoluteThreshold);

    Map<Integer, Integer> countsByLabel = Maps.newHashMap();

    for (Record neighbor : nearestNeighbors) {
      int currentCount = countsByLabel.getOrDefault(neighbor.getLabel(), 0);
      countsByLabel.put(neighbor.getLabel(), currentCount + 1);
    }

    int max = 0;
    int label = 0;
    for (Map.Entry<Integer, Integer> candidateEntry : countsByLabel.entrySet()) {
      int counter = candidateEntry.getValue();
      if (counter > max) {
        max = counter;
        label = candidateEntry.getKey();
      }
    }

    return label;
  }

}
