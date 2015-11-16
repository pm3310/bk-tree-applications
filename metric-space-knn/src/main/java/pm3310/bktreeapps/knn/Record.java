package pm3310.bktreeapps.knn;

import java.util.List;

public class Record {

  private final int label;
  private final List<Double> attributes;

  public Record (int label, List<Double> data) {
    this.label = label;
    attributes = data;
  }

  public int getLabel() {
    return label;
  }

  public List<Double> getAttributes() {
    return attributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Record record = (Record) o;

    if (label != record.label) {
      return false;
    }
    if (attributes != null ? !attributes.equals(record.attributes) : record.attributes != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = label;
    result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Record{" +
        "label=" + label +
        ", attributes=" + attributes +
        '}';
  }

}