package conference;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compares talks.
 */
public class TalkComparator implements Comparator<Talk> {

    @Override
    public int compare(@NonNull Talk first, @NonNull Talk second) {
        int result = Objects.compare(first.getDuration(), second.getDuration(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
    }
}