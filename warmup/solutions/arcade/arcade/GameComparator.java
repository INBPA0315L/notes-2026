package arcade;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compares games.
 */
public class GameComparator implements Comparator<Game> {

    @Override
    public int compare(@NonNull Game first, @NonNull Game second) {
        int result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
    }
}