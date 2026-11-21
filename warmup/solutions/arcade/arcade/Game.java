package arcade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a game.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Game implements Comparable<Game> {

    @EqualsAndHashCode.Include
    private final String name;

    private final String description;

    private final Platform platform;

    private final int released;

    @ToString.Exclude
    private final Character[] characters;

    @Override
    public int compareTo(@NonNull Game other) {
        int result = Objects.compare(this.getReleased(), other.getReleased(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(this.getDescription(), other.getDescription(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(this.getName(), other.getName(), Comparator.reverseOrder());
    }

    /**
     * Compares games.
     */
    public static class GameComparator implements Comparator<Game> {

        @Override
        public int compare(@NonNull Game first, @NonNull Game second) {
            int result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
        }
    }

    /**
     * Represents a character.
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Character {

        @EqualsAndHashCode.Include
        private final String name;

        private final String description;

        private final int games;
    }

    /**
     * Represents the possible platforms.
     */
    public enum Platform {
        @JsonProperty("Neo Geo") NEO_GEO,
        @JsonProperty("Capcom Play System") CAPCOM_PLAY_SYSTEM,
        @JsonProperty("Sega Model Series") SEGA_MODEL_SERIES,
        @JsonProperty("Nintendo") NINTENDO
    }
}
