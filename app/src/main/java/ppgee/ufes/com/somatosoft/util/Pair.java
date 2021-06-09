package ppgee.ufes.com.somatosoft.util;

public class Pair<F, S> {
    private F first = null;
    private S second = null;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Object o) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<>(a, b);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
