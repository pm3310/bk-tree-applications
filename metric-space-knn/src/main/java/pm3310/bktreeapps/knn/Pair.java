package pm3310.bktreeapps.knn;

public class Pair<K, V extends Comparable> implements Comparable< Pair<K, V>> {

  private final K key;
  private final V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @SuppressWarnings("unchecked")
  @Override
  public int compareTo(Pair<K, V> o) {
    return value.compareTo(o.value);
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pair pair = (Pair) o;

    if (key != null ? !key.equals(pair.key) : pair.key != null) {
      return false;
    }
    if (value != null ? !value.equals(pair.value) : pair.value != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Pair{" +
        "key=" + key +
        ", value=" + value +
        '}';
  }

}

