package pm3310.bktreeapps.knn;

import com.google.common.collect.Lists;

import edu.gatech.gtri.bktree.BkTreeSearcher;
import edu.gatech.gtri.bktree.Metric;
import edu.gatech.gtri.bktree.MutableBkTree;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;

public class KNNTest {

  @Test
  public void typicalTwoClassLabelTwoDimensionalProblem() {
    List<Record> records = Lists.newArrayList();

    Record record1 = new Record(0, Lists.newArrayList(0.0, 0.0));
    Record record2 = new Record(0, Lists.newArrayList(0.0, 0.3));
    Record record3 = new Record(0, Lists.newArrayList(0.2, 0.3));
    Record record4 = new Record(0, Lists.newArrayList(0.1, 0.1));
    Record record5 = new Record(1, Lists.newArrayList(1.0, 1.0));
    Record record6 = new Record(1, Lists.newArrayList(1.2, 1.0));
    Record record7 = new Record(1, Lists.newArrayList(0.8, 1.8));
    Record record8 = new Record(1, Lists.newArrayList(2.0, 0.7));

    records.add(record1);
    records.add(record2);
    records.add(record3);
    records.add(record4);
    records.add(record5);
    records.add(record6);
    records.add(record7);
    records.add(record8);

    Metric<Record> manhattanDistance = (x, y) -> {
      List<Double> attributesX = x.getAttributes();
      List<Double> attributesY = y.getAttributes();

      double distance = 0;

      for (int i = 0; i < attributesX.size(); i++) {
        double fValueX = attributesX.get(i);
        double fValueY = attributesY.get(i);

        distance += Math.abs(fValueX - fValueY);
      }

      return (int) distance;
    };

    MutableBkTree<Record> bkTree = new MutableBkTree<>(manhattanDistance);
    bkTree.add(record1);
    bkTree.add(record2);
    bkTree.add(record3);
    bkTree.add(record4);
    bkTree.add(record5);
    bkTree.add(record6);
    bkTree.add(record7);
    bkTree.add(record1);

    BkTreeSearcher<Record> searcher = new BkTreeSearcher<>(bkTree);

    Record queryRecord = new Record(1, Lists.newArrayList(0.4, 0.3));

    KNN knn = new KNN(searcher);

    int actualLabel = knn.classify(queryRecord, 3, 2);

    assertThat(actualLabel, is(0));
  }

  @Test
  public void typicalThreeClassLabelTwoDimensionalProblem() {
    List<Record> records = Lists.newArrayList();

    Record record1 = new Record(0, Lists.newArrayList(0.0, 0.0));
    Record record2 = new Record(0, Lists.newArrayList(0.0, 0.3));
    Record record3 = new Record(0, Lists.newArrayList(0.2, 0.3));
    Record record4 = new Record(1, Lists.newArrayList(0.1, 0.1));
    Record record5 = new Record(1, Lists.newArrayList(1.0, 1.0));
    Record record6 = new Record(2, Lists.newArrayList(1.2, 1.0));
    Record record7 = new Record(2, Lists.newArrayList(0.8, 1.8));
    Record record8 = new Record(2, Lists.newArrayList(2.0, 0.7));

    records.add(record1);
    records.add(record2);
    records.add(record3);
    records.add(record4);
    records.add(record5);
    records.add(record6);
    records.add(record7);
    records.add(record8);

    Metric<Record> manhattanDistance = (x, y) -> {
      List<Double> attributesX = x.getAttributes();
      List<Double> attributesY = y.getAttributes();

      double distance = 0;

      for (int i = 0; i < attributesX.size(); i++) {
        double fValueX = attributesX.get(i);
        double fValueY = attributesY.get(i);

        distance += Math.abs(fValueX - fValueY);
      }

      return (int) distance;
    };

    MutableBkTree<Record> bkTree = new MutableBkTree<>(manhattanDistance);
    bkTree.add(record1);
    bkTree.add(record2);
    bkTree.add(record3);
    bkTree.add(record4);
    bkTree.add(record5);
    bkTree.add(record6);
    bkTree.add(record7);
    bkTree.add(record1);

    BkTreeSearcher<Record> searcher = new BkTreeSearcher<>(bkTree);

    Record queryRecord = new Record(1, Lists.newArrayList(2.0, 2.0));

    KNN knn = new KNN(searcher);

    int actualLabel = knn.classify(queryRecord, 3, 2);

    assertThat(actualLabel, is(2));
  }

  @Test
  public void typicalThreeClassLabelFourDimensionalProblem() {
    List<Record> records = Lists.newArrayList();

    Record record1 = new Record(0, Lists.newArrayList(0.0, 0.0, 0.1, 0.2));
    Record record2 = new Record(0, Lists.newArrayList(0.0, 0.3, 1.1, 0.2));
    Record record3 = new Record(0, Lists.newArrayList(0.2, 0.3, 1.1, 1.2));
    Record record4 = new Record(1, Lists.newArrayList(0.1, 0.1, 0.8, 0.9));
    Record record5 = new Record(1, Lists.newArrayList(1.0, 1.0, 0.1, 2.2));
    Record record6 = new Record(2, Lists.newArrayList(1.2, 1.0, 2.0, 3.0));
    Record record7 = new Record(2, Lists.newArrayList(0.8, 1.8, 1.1, 1.6));
    Record record8 = new Record(2, Lists.newArrayList(2.0, 0.7, 4.1, 4.2));

    records.add(record1);
    records.add(record2);
    records.add(record3);
    records.add(record4);
    records.add(record5);
    records.add(record6);
    records.add(record7);
    records.add(record8);

    Metric<Record> manhattanDistance = (x, y) -> {
      List<Double> attributesX = x.getAttributes();
      List<Double> attributesY = y.getAttributes();

      double distance = 0;

      for (int i = 0; i < attributesX.size(); i++) {
        double fValueX = attributesX.get(i);
        double fValueY = attributesY.get(i);

        distance += Math.abs(fValueX - fValueY);
      }

      return (int) distance;
    };

    MutableBkTree<Record> bkTree = new MutableBkTree<>(manhattanDistance);
    bkTree.add(record1);
    bkTree.add(record2);
    bkTree.add(record3);
    bkTree.add(record4);
    bkTree.add(record5);
    bkTree.add(record6);
    bkTree.add(record7);
    bkTree.add(record1);

    BkTreeSearcher<Record> searcher = new BkTreeSearcher<>(bkTree);

    Record queryRecord = new Record(1, Lists.newArrayList(2.0, 2.0, 1.0, 1.4));

    KNN knn = new KNN(searcher);

    int actualLabel = knn.classify(queryRecord, 3, 2);

    assertThat(actualLabel, is(2));
  }

}
