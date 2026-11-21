package cinema;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compares movies.
 */
public class MovieComparator implements Comparator<Movie> {

    @Override
    public int compare(@NonNull Movie first, @NonNull Movie second) {
        int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getYear(), second.getYear(), Comparator.reverseOrder());
    }
}