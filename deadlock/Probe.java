import java.io.Serializable;
import java.lang.Comparable;

public class Probe implements Serializable, Comparable< Probe > {
    public int compareTo( Probe other ) {
        return this == other ? 1 : 0;
    }
}
