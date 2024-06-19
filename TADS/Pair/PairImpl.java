package TADS.Pair;

import java.util.Objects;

public class PairImpl<K, V> {
    private final K first;
    private final V second;

    public PairImpl(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}
