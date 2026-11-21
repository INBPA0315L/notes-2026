package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a movie.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Movie implements Comparable<Movie> {

    @EqualsAndHashCode.Include
    protected final String title;

    protected final String genre;

    protected final Studio studio;

    @EqualsAndHashCode.Include
    protected final int year;

    @ToString.Exclude
    protected final Actor[] actors;

    @Override
    public int compareTo(@NonNull Movie other) {
        int result = Objects.compare(this.getGenre(), other.getGenre(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(this.getYear(), other.getYear(), Comparator.reverseOrder());
    }

    /**
     * Compares movies.
     */
    public static class MovieComparator implements Comparator<Movie> {

        @Override
        public int compare(@NonNull Movie first, @NonNull Movie second) {
            int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
        }
    }

    /**
     * Represents an actor.
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Actor {

        @EqualsAndHashCode.Include
        final String name;

        final String nationality;

        final int age;
    }

    /**
     * Represents the possible studios.
     */
    public enum Studio {
        @JsonProperty("Paramount Pictures") PARAMOUNT_PICTURES,
        @JsonProperty("Universal Pictures") UNIVERSAL_PICTURES,
        @JsonProperty("Columbia Pictures") COLUMBIA_PICTURES,
        @JsonProperty("Warner Bros.") WARNER_BROS
    }
}
